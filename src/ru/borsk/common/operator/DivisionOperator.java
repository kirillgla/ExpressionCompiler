package ru.borsk.common.operator;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class DivisionOperator implements BinaryOperator {
  @Override
  @Contract(pure = true)
  public @NotNull String getPresentation() {
    return "/";
  }

  private DivisionOperator() {
  }

  public static final BinaryOperator Instance = new DivisionOperator();
}
