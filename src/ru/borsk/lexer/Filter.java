package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.impl.*;

public final class Filter {
  private final WhitespaceTokenVisitor visitor = new WhitespaceTokenVisitor();

  public boolean shouldUse(Token token) {
    token.visit(visitor);
    return !visitor.isWhitespace();
  }

  private static final class WhitespaceTokenVisitor extends TokenVisitor {
    private boolean isWhitespace;

    private boolean isWhitespace() {
      return isWhitespace;
    }

    @Override
    public void visitError(final @NotNull ErrorToken error) {
      isWhitespace = false;
    }

    @Override
    public void visitNumber(final @NotNull NumberToken number) {
      isWhitespace = false;
    }

    @Override
    public void visitOperator(final @NotNull OperatorToken operator) {
      isWhitespace = false;
    }

    @Override
    public void visitParenthesis(final @NotNull ParenthesisToken parenthesis) {
      isWhitespace = false;
    }

    @Override
    public void visitWhitespace(final @NotNull WhitespaceToken whitespace) {
      isWhitespace = true;
    }
  }
}
