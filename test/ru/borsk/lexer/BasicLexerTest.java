package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.common.operator.DivisionOperator;
import ru.borsk.common.operator.MultiplicationOperator;
import ru.borsk.common.parenthesis.ClosingParenthesis;
import ru.borsk.common.parenthesis.OpeningParenthesis;
import ru.borsk.lexer.impl.BasicLexer;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.*;

import java.util.Arrays;
import java.util.List;

public class BasicLexerTest {
  private @NotNull List<@NotNull Token> scan(final @NotNull String input) {
    return new BasicLexer(input).scan();
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
      WhitespaceToken.Instance,
      new OperatorToken(AdditionOperator.Instance),
      WhitespaceToken.Instance,
      new NumberToken("2"),
      new OperatorToken(MultiplicationOperator.Instance),
      WhitespaceToken.Instance,
      new NumberToken("4"),
      WhitespaceToken.Instance,
      new OperatorToken(DivisionOperator.Instance),
      WhitespaceToken.Instance,
      new NumberToken("2"));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testBadTokens() {
    final @NotNull List<@NotNull Token> actual = scan("2 + {2} = (4)");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      WhitespaceToken.Instance,
      new OperatorToken(AdditionOperator.Instance),
      WhitespaceToken.Instance,
      new ErrorToken('{'),
      new NumberToken("2"),
      new ErrorToken('}'),
      WhitespaceToken.Instance,
      new ErrorToken('='),
      WhitespaceToken.Instance,
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
      WhitespaceToken.Instance,
      new OperatorToken(AdditionOperator.Instance),
      WhitespaceToken.Instance,
      new NumberToken("2"),
      WhitespaceToken.Instance,
      new NumberToken("2")
    );
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testTooBigNumber() {
    final @NotNull List<@NotNull Token> actual = scan("2 +9999999999999999999999999");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      WhitespaceToken.Instance,
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("9999999999999999999999999")
    );
    Assertions.assertEquals(expected, actual);
  }
}
