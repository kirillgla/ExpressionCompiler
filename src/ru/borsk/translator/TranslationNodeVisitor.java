package ru.borsk.translator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.parser.node.RecursiveNodeVisitor;
import ru.borsk.parser.node.impl.BinaryOperatorNode;
import ru.borsk.parser.node.impl.NumberNode;
import ru.borsk.translator.code.StackMachineCode;
import ru.borsk.translator.code.impl.OperateStackTop;
import ru.borsk.translator.code.impl.PushConstant;

import java.util.ArrayList;
import java.util.List;

public final class TranslationNodeVisitor extends RecursiveNodeVisitor {
  private final @NotNull List<StackMachineCode> codes;

  public TranslationNodeVisitor() {
    codes = new ArrayList<>();
  }

  @NotNull
  public List<StackMachineCode> getCodes() {
    return codes;
  }

  @Override
  protected void processAfterInteriorBinaryOperator(final @NotNull BinaryOperatorNode binaryOperator) {
    codes.add(new OperateStackTop(binaryOperator.getOperator()));
  }

  @Override
  public void visitNumber(final @NotNull NumberNode number) {
    codes.add(new PushConstant(number.getToken().getNumber()));
  }
}
