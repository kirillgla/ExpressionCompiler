package ru.borsk.generator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.translator.code.StackMachineCodeVisitor;
import ru.borsk.translator.code.impl.OperateStackTop;
import ru.borsk.translator.code.impl.PushConstant;

public final class StackMachineCodeConverterVisitor extends StackMachineCodeVisitor {
  private final BinaryOperatorConverterVisitor visitor = new BinaryOperatorConverterVisitor();
  private final @NotNull StringBuilder builder;

  public StackMachineCodeConverterVisitor(final @NotNull StringBuilder builder) {
    this.builder = builder;
  }

  @Override
  public void visitPushConstant(final @NotNull PushConstant pushConstant) {
    builder.append("  pushl $").append(pushConstant.getConstant()).append("\n");
  }

  @Override
  public void visitOperateStackTop(final @NotNull OperateStackTop operateStackTop) {
    operateStackTop.getOperator().visit(visitor);
    builder
      .append("  popl %ebx\n")
      .append("  popl %eax\n")
      .append("  ").append(visitor.getInstruction()).append(" %ebx, %eax\n")
      .append("  pushl %eax\n");
  }
}
