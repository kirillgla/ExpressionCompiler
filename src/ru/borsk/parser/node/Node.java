package ru.borsk.parser.node;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.interfaces.Presentable;
import ru.borsk.lexer.token.ValidToken;

public interface Node<TToken extends ValidToken> extends Presentable {
  @NotNull TToken getToken();

  void visit(final @NotNull NodeVisitor visitor);
}
