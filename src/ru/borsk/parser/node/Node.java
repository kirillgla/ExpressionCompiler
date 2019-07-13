package ru.borsk.parser.node;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;

public interface Node<TToken extends ValidToken> {
  @NotNull TToken getToken();

  void visit(final @NotNull NodeVisitor visitor);
}
