package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.parenthesis.Parenthesis;
import ru.borsk.lexer.token.Token;

public final class ParenthesisToken implements Token {
  private @NotNull Parenthesis parenthesis;

  @Contract(pure = true)
  public @NotNull Parenthesis getParenthesis() {
    return parenthesis;
  }

  public ParenthesisToken(final @NotNull Parenthesis parenthesis) {
    this.parenthesis = parenthesis;
  }
}
