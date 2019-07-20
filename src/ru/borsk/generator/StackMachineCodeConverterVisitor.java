package ru.borsk.generator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCodeVisitor;
import ru.borsk.analyzer.code.impl.OperateStackTopAnnotated;
import ru.borsk.analyzer.code.impl.PushConstantAnnotated;

public final class StackMachineCodeConverterVisitor extends AnnotatedStackMachineCodeVisitor {
  private final BinaryOperatorConverterVisitor visitor = new BinaryOperatorConverterVisitor();
  private final @NotNull StringBuilder builder;

  public StackMachineCodeConverterVisitor(final @NotNull StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void visitPushConstant(final @NotNull PushConstantAnnotated pushConstant) {
    builder.append("  movl $");
    builder.append(pushConstant.getConstant());
    builder.append(", ");
    appendEspOffset(pushConstant.getOffset());
    builder.append("\n");
  }

  @Override
  public void visitOperateStackTop(final @NotNull OperateStackTopAnnotated operateStackTop) {
    operateStackTop.getOperator().visit(visitor);
    builder.append("  movl ");
    appendEspOffset(operateStackTop.getStackTop());
    builder.append(", %ebx\n");
    builder.append("  movl ");
    appendEspOffset(operateStackTop.getStackTop() - 1);
    builder.append(", %eax\n");
    builder.append("  ");
    builder.append(visitor.getInstruction());
    builder.append(" %ebx, %eax\n");
    builder.append("  movl %eax, ");
    appendEspOffset(operateStackTop.getStackTop() - 1);
    builder.append("\n");
  }

  private void appendEspOffset(final int offset) {
    if (offset != 0) builder.append((offset) * 4);
    builder.append("(%esp)");
  }
}
