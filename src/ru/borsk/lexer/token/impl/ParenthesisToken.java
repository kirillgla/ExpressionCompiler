package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.parenthesis.Parenthesis;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.ValidTokenVisitor;

public final class ParenthesisToken implements Token, ValidToken {
  private @NotNull Parenthesis parenthesis;

  @Contract(pure = true)
  public @NotNull Parenthesis getParenthesis() {
    return parenthesis;
  }

  public ParenthesisToken(final @NotNull Parenthesis parenthesis) {
    this.parenthesis = parenthesis;
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public @NotNull ValidToken toValidToken() {
    return this;
  }

  @Override
  public @NotNull String getPresentation() {
    return parenthesis.getPresentation();
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof ParenthesisToken)) return false;
    final ParenthesisToken other = (ParenthesisToken)obj;
    // Reference comparison will do because parentheses are singletons
    return parenthesis == other.parenthesis;
  }

  @Override
  public void visit(final @NotNull TokenVisitor visitor) {
    visitor.visitParenthesis(this);
  }

  @Override
  public void visitValid(final @NotNull ValidTokenVisitor visitor) {
    visitor.visitParenthesis(this);
  }
}
