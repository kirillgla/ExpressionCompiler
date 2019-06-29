package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;

public final class NumberToken implements Token {
  private final @NotNull String number;

  @Contract(pure = true)
  public final @NotNull String getNumber() {
    return number;
  }

  public NumberToken(@NotNull final String number) {
    this.number = number;
  }
}
