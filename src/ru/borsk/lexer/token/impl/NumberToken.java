package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.ValidToken;

public final class NumberToken implements Token {
  private final @NotNull String number;

  @Contract(pure = true)
  public final @NotNull String getNumber() {
    return number;
  }

  public NumberToken(@NotNull final String number) {
    this.number = number;
  }

  @Override
  public boolean isValid() {
    try {
      Integer.parseInt(number);
      return false;
    } catch (NumberFormatException e) {
      return true;
    }
  }

  @Override
  public @NotNull ValidToken toValidToken() {
    return new ValidNumberToken(Integer.parseInt(number));
  }

  @Override
  public @NotNull String getPresentation() {
    return number;
  }

  @Override
  public final boolean equals(final Object obj) {
    if (!(obj instanceof NumberToken)) return false;
    final NumberToken other = (NumberToken)obj;
    return number.equals(other.number);
  }

  @Override
  public void visit(final @NotNull TokenVisitor visitor) {
    visitor.visitNumber(this);
  }
}
