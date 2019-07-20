package ru.borsk.parser.node.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.NodeVisitor;

import java.util.Objects;

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

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof BinaryOperatorNode)) return false;
    if (!super.equals(other)) return false;
    final BinaryOperatorNode that = (BinaryOperatorNode)other;
    return leftOperand.equals(that.leftOperand) && rightOperand.equals(that.rightOperand);
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), leftOperand, rightOperand);
  }

  @Override
  public @NotNull String getPresentation() {
    return "(" + leftOperand.getPresentation() + super.getPresentation() + rightOperand.getPresentation() + ")";
  }
}
