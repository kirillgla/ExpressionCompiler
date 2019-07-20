package ru.borsk.translator.code;

import org.jetbrains.annotations.NotNull;

public interface StackMachineCode {
  void accept(final @NotNull StackMachineCodeVisitor visitor);
}
