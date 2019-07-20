package ru.borsk.translator.code.impl;

import ru.borsk.translator.code.StackMachineCode;

import java.util.Objects;

public final class PushConstant implements StackMachineCode {
  private final int constant;

  public PushConstant(final int constant) {
    this.constant = constant;
  }

  public int getConstant() {
    return constant;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof PushConstant)) return false;
    final PushConstant that = (PushConstant)other;
    return constant == that.constant;
  }

  @Override
  public int hashCode() {
    return Objects.hash(constant);
  }
}
