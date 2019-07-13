package ru.borsk.parser.node.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.parser.node.Node;

public abstract class NodeBase<TToken> implements Node<TToken> {
  private final @NotNull TToken token;

  protected NodeBase(final @NotNull TToken token) {
    this.token = token;
  }

  @NotNull
  @Override
  public TToken getToken() {
    return token;
  }
}
