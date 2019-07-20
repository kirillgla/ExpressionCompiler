package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.common.operator.DivisionOperator;
import ru.borsk.common.operator.MultiplicationOperator;
import ru.borsk.common.parenthesis.ClosingParenthesis;
import ru.borsk.common.parenthesis.OpeningParenthesis;
import ru.borsk.lexer.impl.FilteringLexer;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;
import ru.borsk.lexer.token.impl.NumberToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ParenthesisToken;

import java.util.Arrays;
import java.util.List;

public final class FilteringLexerTest {
  private @NotNull List<@NotNull Token> scan(final @NotNull String input) {
    return new FilteringLexer(input).scan();
  }

  @Test
  public void testSimpleExpression() {
    final @NotNull List<@NotNull Token> actual = scan("2+2");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2"));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testWhitespaces() {
    final @NotNull List<@NotNull Token> actual = scan("2 +\t\t\t2*  \f4\r\n/\n2");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2"),
      new OperatorToken(MultiplicationOperator.Instance),
      new NumberToken("4"),
      new OperatorToken(DivisionOperator.Instance),
      new NumberToken("2"));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testBadTokens() {
    final @NotNull List<@NotNull Token> actual = scan("2 + {2} = (4)");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new ErrorToken('{'),
      new NumberToken("2"),
      new ErrorToken('}'),
      new ErrorToken('='),
      new ParenthesisToken(OpeningParenthesis.Instance),
      new NumberToken("4"),
      new ParenthesisToken(ClosingParenthesis.Instance)
    );
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testNumbersSeparatedWithWhitespace() {
    final @NotNull List<@NotNull Token> actual = scan("2 + 2 2");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2"),
      new NumberToken("2")
    );
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testTooBigNumber() {
    final @NotNull List<@NotNull Token> actual = scan("2 +9999999999999999999999999");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("9999999999999999999999999")
    );
    Assertions.assertEquals(expected, actual);
  }
}
