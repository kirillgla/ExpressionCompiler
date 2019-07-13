package ru.borsk.parser.node;

import org.jetbrains.annotations.NotNull;
import ru.borsk.parser.node.impl.BinaryOperatorNode;
import ru.borsk.parser.node.impl.NumberNode;

public abstract class NodeVisitor {
  public void visitBinaryOperator(final @NotNull BinaryOperatorNode binaryOperator) {
  }

  public void visitNumberNode(final @NotNull NumberNode number) {
  }
}
