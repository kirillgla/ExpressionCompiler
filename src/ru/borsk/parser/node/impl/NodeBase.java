package ru.borsk.parser.node.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.node.Node;

import java.util.Objects;

public abstract class NodeBase<TToken extends ValidToken> implements Node<TToken> {
  private final @NotNull TToken token;

  protected NodeBase(final @NotNull TToken token) {
    this.token = token;
  }

  @NotNull
  @Override
  public final TToken getToken() {
    return token;
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) return true;
    if (!(other instanceof NodeBase)) return false;
    final NodeBase<?> nodeBase = (NodeBase<?>)other;
    return token.equals(nodeBase.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(token);
  }

  @Override
  public @NotNull String getPresentation() {
    return token.getPresentation();
  }

  @Override
  @Deprecated
  public String toString() {
    return getPresentation();
  }
}
