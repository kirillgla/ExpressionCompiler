package ru.borsk.analyzer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;
import ru.borsk.analyzer.code.impl.OperateStackTopAnnotated;
import ru.borsk.analyzer.code.impl.PushConstantAnnotated;
import ru.borsk.translator.code.StackMachineCodeVisitor;
import ru.borsk.translator.code.impl.OperateStackTop;
import ru.borsk.translator.code.impl.PushConstant;

public final class StackMachineCodeAnalyzerVisitor extends StackMachineCodeVisitor {
  private int maxDepth = 1;
  private int currentDepth = StackMachineCodeAnalyzer.ADDITIONAL_STACK_ITEMS;
  private AnnotatedStackMachineCode currentCode;

  @Override
  public void visitPushConstant(final @NotNull PushConstant pushConstant) {
    currentCode = new PushConstantAnnotated(pushConstant.getConstant(), currentDepth);
    currentDepth += 1;
    if (maxDepth < currentDepth) maxDepth = currentDepth;
  }

  @Override
  public void visitOperateStackTop(final @NotNull OperateStackTop operateStackTop) {
    currentDepth -= 1;
    currentCode = new OperateStackTopAnnotated(operateStackTop.getOperator(), currentDepth);
  }

  public final AnnotatedStackMachineCode getCurrentCode() {
    return currentCode;
  }

  public final int getMaxDepth() {
    return maxDepth;
  }
}
