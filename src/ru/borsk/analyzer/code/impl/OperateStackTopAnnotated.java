package ru.borsk.analyzer.code.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;
import ru.borsk.analyzer.code.AnnotatedStackMachineCodeVisitor;
import ru.borsk.common.operator.BinaryOperator;

public final class OperateStackTopAnnotated implements AnnotatedStackMachineCode {
  private final BinaryOperator operator;
  private final int stackTop;

  public OperateStackTopAnnotated(final BinaryOperator operator, final int stackTop) {
    this.operator = operator;
    this.stackTop = stackTop;
  }

  public BinaryOperator getOperator() {
    return operator;
  }

  public int getStackTop() {
    return stackTop;
  }

  @Override
  public void accept(final @NotNull AnnotatedStackMachineCodeVisitor visitor) {
    visitor.visitOperateStackTop(this);
  }
}
