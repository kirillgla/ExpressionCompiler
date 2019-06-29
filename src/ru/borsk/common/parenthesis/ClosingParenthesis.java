package ru.borsk.common.parenthesis;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class ClosingParenthesis implements Parenthesis {
  @Override
  @Contract(pure = true)
  public @NotNull String getPresentation() {
    return ")";
  }

  private ClosingParenthesis() {
  }

  public static final Parenthesis Instance = new ClosingParenthesis();
}
