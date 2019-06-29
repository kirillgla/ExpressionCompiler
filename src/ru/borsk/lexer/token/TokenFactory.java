package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface TokenFactory<T> {
  @NotNull Token produce(final @NotNull T input);
}
