package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.*;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.ParseFailureException;
import ru.borsk.parser.Parser;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.impl.BinaryOperatorNode;
import ru.borsk.parser.node.impl.NumberNode;

import java.util.Arrays;

public final class ParserTest {
  private void testFailParse(final @NotNull ValidToken... tokens) {
    Assertions.assertThrows(ParseFailureException.class, () -> new Parser(Arrays.asList(tokens)).parse());
  }

  private void testParse(final Node<? extends ValidToken> template, final @NotNull ValidToken... tokens) {
    try {
      final Node<? extends ValidToken> node = new Parser(Arrays.asList(tokens)).parse();
      Assertions.assertEquals(template, node);
    } catch (ParseFailureException e) {
      Assertions.fail("Could not parse");
    }
  }

  private void assertLeftAssociative(final @NotNull BinaryOperator operator) {
    final BinaryOperatorNode expected = new BinaryOperatorNode(
      new OperatorToken(operator),
      new BinaryOperatorNode(
        new OperatorToken(operator),
        new NumberNode(new ValidNumberToken(2)),
        new NumberNode(new ValidNumberToken(3))
      ),
      new NumberNode(new ValidNumberToken(4))
    );
    testParse(
      expected,
      new ValidNumberToken(2),
      new OperatorToken(operator),
      new ValidNumberToken(3),
      new OperatorToken(operator),
      new ValidNumberToken(4)
    );
  }

  @Test
  public void testSimpleExpression() {
    // 2 + 2
    final BinaryOperatorNode expected = new BinaryOperatorNode(
      new OperatorToken(AdditionOperator.Instance),
      new NumberNode(new ValidNumberToken(2)),
      new NumberNode(new ValidNumberToken(3))
    );
    testParse(
      expected,
      new ValidNumberToken(2),
      new OperatorToken(AdditionOperator.Instance),
      new ValidNumberToken(3)
    );
  }

  @Test
  public void testOperatorPrecedence() {
    // 2 * 3 + 4 * 5
    final BinaryOperatorNode expected = new BinaryOperatorNode(
      new OperatorToken(AdditionOperator.Instance),
      new BinaryOperatorNode(
        new OperatorToken(MultiplicationOperator.Instance),
        new NumberNode(new ValidNumberToken(2)),
        new NumberNode(new ValidNumberToken(3))
      ),
      new BinaryOperatorNode(
        new OperatorToken(MultiplicationOperator.Instance),
        new NumberNode(new ValidNumberToken(4)),
        new NumberNode(new ValidNumberToken(5))
      )
    );
    testParse(
      expected,
      new ValidNumberToken(2),
      new OperatorToken(MultiplicationOperator.Instance),
      new ValidNumberToken(3),
      new OperatorToken(AdditionOperator.Instance),
      new ValidNumberToken(4),
      new OperatorToken(MultiplicationOperator.Instance),
      new ValidNumberToken(5)
    );
  }

  @Test
  public void testOperatorsAssociativity() {
    assertLeftAssociative(AdditionOperator.Instance);
    assertLeftAssociative(SubtractionOperator.Instance);
    assertLeftAssociative(MultiplicationOperator.Instance);
    assertLeftAssociative(DivisionOperator.Instance);
  }

  @Test
  public void testUnmatchedInput() {
    testFailParse(
      new ValidNumberToken(2),
      new ValidNumberToken(3)
    );
  }
}
