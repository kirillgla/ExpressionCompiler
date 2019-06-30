package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.TokenVisitorBase;
import ru.borsk.lexer.token.TrustedToken;

public final class NumberToken extends TrustedToken {
  private final @NotNull String number;

  @Contract(pure = true)
  public final @NotNull String getNumber() {
    return number;
  }

  public NumberToken(@NotNull final String number) {
    this.number = number;
  }

  @Override
  public void visit(final @NotNull TokenVisitorBase visitor) {
    visitor.visitNumber(this);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof NumberToken)) return false;
    final NumberToken other = (NumberToken) obj;
    return number.equals(other.number);
  }
}
