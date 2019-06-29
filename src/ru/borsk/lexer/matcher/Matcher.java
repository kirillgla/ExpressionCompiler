package ru.borsk.lexer.matcher;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.token.Token;

import java.util.List;

public interface Matcher {
  void consumeChar(final char c);

  @Nullable Token tryProduce();

  boolean canMatchLater();

  @Contract(pure = true)
  @NotNull List<@NotNull String> getExpectations();

  void reset();
}
