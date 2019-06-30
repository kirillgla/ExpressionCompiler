package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;

public interface Token {
  void visit(final @NotNull TokenVisitorBase visitor);
}
