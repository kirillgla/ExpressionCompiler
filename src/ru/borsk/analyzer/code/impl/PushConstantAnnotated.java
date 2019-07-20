package ru.borsk.analyzer.code.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;
import ru.borsk.analyzer.code.AnnotatedStackMachineCodeVisitor;

public final class PushConstantAnnotated implements AnnotatedStackMachineCode {
  private final int constant;
  private final int offset;

  public PushConstantAnnotated(final int constant, final int offset) {
    this.constant = constant;
    this.offset = offset;
  }

  public int getConstant() {
    return constant;
  }

  public int getOffset() {
    return offset;
  }

  @Override
  public void accept(final @NotNull AnnotatedStackMachineCodeVisitor visitor) {
    visitor.visitPushConstant(this);
  }
}
