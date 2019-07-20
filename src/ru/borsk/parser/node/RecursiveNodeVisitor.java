package ru.borsk.parser.node;

import org.jetbrains.annotations.NotNull;
import ru.borsk.parser.node.impl.BinaryOperatorNode;

public abstract class RecursiveNodeVisitor extends NodeVisitor {
  @Override
  public final void visitBinaryOperator(final @NotNull BinaryOperatorNode binaryOperator) {
    processBeforeInteriorBinaryOperator(binaryOperator);
    binaryOperator.getLeftOperand().visit(this);
    processBetweenInteriorBinaryOperator(binaryOperator);
    binaryOperator.getRightOperand().visit(this);
    processAfterInteriorBinaryOperator(binaryOperator);
  }

  protected void processBeforeInteriorBinaryOperator(final @NotNull BinaryOperatorNode binaryOperator) {
  }

  protected void processBetweenInteriorBinaryOperator(final @NotNull BinaryOperatorNode binaryOperatorNode) {
  }

  protected void processAfterInteriorBinaryOperator(final @NotNull BinaryOperatorNode binaryOperator) {
  }
}
