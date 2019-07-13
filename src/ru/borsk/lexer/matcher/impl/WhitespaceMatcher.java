package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.WhitespaceToken;

import java.util.Collections;
import java.util.List;

public final class WhitespaceMatcher implements Matcher {

  private boolean failed;

  public WhitespaceMatcher() {
    failed = false;
  }

  @Override
  public void consumeChar(final char c) {
    if (!Character.isWhitespace(c)) failed = true;
  }

  @Override
  public @Nullable Token tryProduce() {
    if (failed) return null;
    return WhitespaceToken.Instance;
  }

  @Override
  public boolean canMatchLater() {
    return !failed;
  }

  @Override
  public @NotNull List<@NotNull String> getExpectations() {
    return Collections.singletonList("Whitespace");
  }

  @Override
  public void reset() {
    failed = false;
  }
}
