package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.ValidToken;

public final class WhitespaceToken implements Token {
  public static final WhitespaceToken Instance = new WhitespaceToken();

  private WhitespaceToken() {
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public @Nullable ValidToken toValidToken() {
    return null;
  }

  @Override
  public @NotNull String getPresentation() {
    return " ";
  }

  @Override
  public boolean equals(final Object obj) {
    // Reference comparison will do because whitespaces are singletons
    return this == obj;
  }

  @Override
  public void visit(final @NotNull TokenVisitor visitor) {
    visitor.visitWhitespace(this);
  }
}
