package ru.borsk.common.parenthesis;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class OpeningParenthesis implements Parenthesis {
  @Override
  @Contract(pure = true)
  public @NotNull String getPresentation() {
    return "(";
  }

  private OpeningParenthesis() {
  }

  public static final Parenthesis Instance = new OpeningParenthesis();
}
