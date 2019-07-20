package ru.borsk.translator.code;

import org.jetbrains.annotations.NotNull;
import ru.borsk.translator.code.impl.OperateStackTop;
import ru.borsk.translator.code.impl.PushConstant;

public abstract class StackMachineCodeVisitor {
  public void visitPushConstant(final @NotNull PushConstant pushConstant) {
  }

  public void visitOperateStackTop(final @NotNull OperateStackTop operateStackTop) {
  }
}
