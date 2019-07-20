package ru.borsk.common.operator.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.common.operator.BinaryOperatorVisitor;

public final class MultiplicationOperator implements BinaryOperator {
  public static final BinaryOperator Instance = new MultiplicationOperator();

  private MultiplicationOperator() {
  }

  @Override
  @Contract(pure = true)
  public @NotNull String getPresentation() {
    return "*";
  }

  @Override
  public void visit(final @NotNull BinaryOperatorVisitor visitor) {
    visitor.visitMultiplication(this);
  }
}
