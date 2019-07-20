package ru.borsk.translator.code.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.translator.code.StackMachineCode;
import ru.borsk.translator.code.StackMachineCodeVisitor;

import java.util.Objects;

public final class OperateStackTop implements StackMachineCode {
  private final @NotNull BinaryOperator operator;

  public OperateStackTop(final @NotNull BinaryOperator operator) {
    this.operator = operator;
  }

  public final @NotNull BinaryOperator getOperator() {
    return operator;
  }

  @Override
  public void accept(final @NotNull StackMachineCodeVisitor visitor) {
    visitor.visitOperateStackTop(this);
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof OperateStackTop)) return false;
    final OperateStackTop that = (OperateStackTop)other;
    return operator.equals(that.operator);
  }

  @Override
  public int hashCode() {
    return Objects.hash(operator);
  }
}
