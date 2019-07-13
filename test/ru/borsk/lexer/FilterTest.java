package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.common.operator.DivisionOperator;
import ru.borsk.common.operator.SubtractionOperator;
import ru.borsk.lexer.impl.FilteringLexer;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;
import ru.borsk.lexer.token.impl.NumberToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.WhitespaceToken;

import java.util.Arrays;
import java.util.List;

class FilterTest {
  private @NotNull List<@NotNull Token> filter(final @NotNull List<@NotNull Token> tokens) {
    return new FilteringLexer(new MockLexer(tokens)).scan();
  }

  private void assertNothingFiltered(final @NotNull List<@NotNull Token> tokens) {
    final @NotNull List<@NotNull Token> actual = filter(tokens);
    Assertions.assertEquals(tokens, actual);
  }

  @Test
  public void testSimpleExpression() {
    assertNothingFiltered(Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2")
    ));
  }

  @Test
  public void testThatWhitespacesAreFiltered() {
    final @NotNull List<@NotNull Token> actual = filter(Arrays.asList(
      WhitespaceToken.Instance,
      new NumberToken("2"),
      WhitespaceToken.Instance,
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2"),
      WhitespaceToken.Instance
    ));
    final @NotNull List<@NotNull Token> expected = Arrays.asList(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("2")
    );
    Assertions.assertEquals(expected, actual);
  }

  @Test
  public void testThatErrorsAreNotFiltered() {
    assertNothingFiltered(Arrays.asList(
      new ErrorToken('ъ'),
      new OperatorToken(DivisionOperator.Instance),
      new ErrorToken('E'),
      new OperatorToken(SubtractionOperator.Instance),
      new NumberToken("42")
    ));
  }
}
