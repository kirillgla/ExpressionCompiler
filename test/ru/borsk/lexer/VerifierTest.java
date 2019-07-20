package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;
import ru.borsk.lexer.token.impl.NumberToken;
import ru.borsk.lexer.token.impl.OperatorToken;

import java.util.Arrays;

public final class VerifierTest {
  private Verifier verifier = new Verifier();

  private void assertSuccess(final @NotNull Token... tokens) {
    Assertions.assertDoesNotThrow(() -> verifier.verify(Arrays.asList(tokens)));
  }

  private void assertFailure(final @NotNull Token... tokens) {
    Assertions.assertThrows(BadTokenException.class, () -> verifier.verify(Arrays.asList(tokens)));
  }

  @Test
  public void testSimpleExpression() {
    assertSuccess(
      new NumberToken("2"),
      new OperatorToken(AdditionOperator.Instance),
      new NumberToken("4")
    );
  }

  @Test
  public void testExpressionWithErrorTokens() {
    assertFailure(
      new NumberToken("4"),
      new ErrorToken('#'),
      new OperatorToken(AdditionOperator.Instance)
    );
  }

  @Test
  public void testExpressionWithTooLongLiterals() {
    assertFailure(new NumberToken("99999999999999999999999999999"));
  }
}
