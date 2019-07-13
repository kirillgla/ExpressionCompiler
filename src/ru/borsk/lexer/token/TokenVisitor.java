package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.impl.*;

public abstract class TokenVisitor {
  public void visitError(final @NotNull ErrorToken error) {
  }

  public void visitNumber(final @NotNull NumberToken number) {
  }

  public void visitOperator(final @NotNull OperatorToken operator) {
  }

  public void visitParenthesis(final @NotNull ParenthesisToken parenthesis) {
  }

  public void visitWhitespace(final @NotNull WhitespaceToken whitespace) {
  }
}
