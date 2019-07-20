package ru.borsk.analyzer.code;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.impl.OperateStackTopAnnotated;
import ru.borsk.analyzer.code.impl.PushConstantAnnotated;

public abstract class AnnotatedStackMachineCodeVisitor {
  public void visitPushConstant(final @NotNull PushConstantAnnotated code) {
  }

  public void visitOperateStackTop(final @NotNull OperateStackTopAnnotated code) {
  }
}
