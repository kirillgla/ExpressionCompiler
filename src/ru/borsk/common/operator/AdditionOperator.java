package ru.borsk.common.operator;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class AdditionOperator implements BinaryOperator {
  @Override
  @Contract(pure = true)
  public @NotNull String getPresentation() {
    return "+";
  }

  private AdditionOperator() {
  }

  public static final BinaryOperator Instance = new AdditionOperator();
}
