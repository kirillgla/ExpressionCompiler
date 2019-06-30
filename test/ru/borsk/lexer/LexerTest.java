package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.common.operator.DivisionOperator;
import ru.borsk.common.operator.MultiplicationOperator;
import ru.borsk.common.parenthesis.ClosingParenthesis;
import ru.borsk.common.parenthesis.OpeningParenthesis;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;
import ru.borsk.lexer.token.impl.NumberToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ParenthesisToken;

import java.util.Arrays;
import java.util.List;

class LexerTest {
  private @NotNull List<@NotNull Token> scan(final @NotNull String input) {
    return new Lexer(input).scan();
  }

  @Test
  public void TestSimpleExpression() {
    final @NotNull List<@NotNull Token> actual = scan("2+2");
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2"));
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void TestWhitespaces() {
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
  public void TestBadTokens() {
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
}
