package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;

import java.util.Collections;
import java.util.List;

public class ErrorMatcher implements Matcher {
  private @Nullable Character problem = null;
  private boolean failed = false;

  @Override
  public void consumeChar(final char c) {
    if (problem == null) problem = c;
    else failed = true;
  }

  @Override
  public @Nullable Token tryProduce() {
    if (failed || problem == null) return null;
    return new ErrorToken(problem);
  }

  @Override
  public boolean canMatchLater() {
    return problem == null;
  }

  @Override
  public @NotNull List<@NotNull String> getExpectations() {
    return Collections.emptyList();
  }

  @Override
  public void reset() {
    problem = null;
    failed = false;
  }
}
