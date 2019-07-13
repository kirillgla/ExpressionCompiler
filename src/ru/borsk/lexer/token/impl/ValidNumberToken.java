package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.ValidTokenVisitor;

public final class ValidNumberToken implements ValidToken {
  private final int number;

  public ValidNumberToken(final int number) {
    this.number = number;
  }

  private int getNumber() {
    return number;
  }

  @Override
  public @NotNull String getPresentation() {
    return Integer.toString(number);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof ValidNumberToken)) return false;
    final ValidNumberToken other = (ValidNumberToken)obj;
    return number == other.number;
  }

  @Override
  public void visitValid(final @NotNull ValidTokenVisitor visitor) {
    visitor.visitValidNumber(this);
  }
}
