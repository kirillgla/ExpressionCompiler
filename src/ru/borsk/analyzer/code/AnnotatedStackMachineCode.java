package ru.borsk.analyzer.code;

import org.jetbrains.annotations.NotNull;

public interface AnnotatedStackMachineCode {
  void accept(final @NotNull AnnotatedStackMachineCodeVisitor visitor);
}
