package ru.borsk.parser.node.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.node.NodeVisitor;

public final class NumberNode extends NodeBase<ValidNumberToken> {
  public NumberNode(final @NotNull ValidNumberToken token) {
    super(token);
  }

  @Override
  public void visit(final @NotNull NodeVisitor visitor) {
    visitor.visitNumberNode(this);
  }
}
