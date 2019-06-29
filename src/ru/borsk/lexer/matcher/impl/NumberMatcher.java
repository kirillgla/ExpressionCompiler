package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.NumberToken;

import java.util.Collections;
import java.util.List;

public final class NumberMatcher implements Matcher {
  private final StringBuilder builder;

  private boolean failed;

  public NumberMatcher() {
    builder = new StringBuilder();
    failed = false;
  }

  @Override
  public final void consumeChar(final char c) {
    if (failed) return;
    if (!Character.isDigit(c)) {
      failed = true;
      return;
    }
    builder.append(c);
  }

  @Override
  public final @Nullable Token tryProduce() {
    if (failed) return null;
    return new NumberToken(builder.toString());
  }

  @Override
  public final boolean canMatchLater() {
    return !failed;
  }

  @Override
  @Contract(pure = true)
  public final @NotNull List<@NotNull String> getExpectations() {
    return Collections.singletonList("Number");
  }

  @Override
  public final void reset() {
    builder.setLength(0);
    failed = false;
  }
}
