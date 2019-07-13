package ru.borsk.parser.node.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.NodeVisitor;

public final class BinaryOperatorNode extends NodeBase<OperatorToken> {
  private final Node leftOperand;
  private final Node rightOperand;

  public BinaryOperatorNode(final @NotNull OperatorToken token, Node leftOperand, Node rightOperand) {
    super(token);
    this.leftOperand = leftOperand;
    this.rightOperand = rightOperand;
  }

  public Node getLeftOperand() {
    return leftOperand;
  }

  public Node getRightOperand() {
    return rightOperand;
  }

  public BinaryOperator getOperator() {
    return getToken().getOperator();
  }

  @Override
  public void visit(final @NotNull NodeVisitor visitor) {
    visitor.visitBinaryOperator(this);
  }
}
