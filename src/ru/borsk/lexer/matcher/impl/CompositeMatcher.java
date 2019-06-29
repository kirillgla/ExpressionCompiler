package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.Token;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompositeMatcher implements Matcher {
  private final @NotNull List<@NotNull Matcher> matchers;

  private @Nullable List<@NotNull String> descriptions;

  public CompositeMatcher(final @NotNull List<@NotNull Matcher> matchers, final @Nullable String description) {
    this.matchers = matchers;
    if (description != null) {
      this.descriptions = Collections.singletonList(description);
    }
  }

  public CompositeMatcher(final @NotNull List<@NotNull Matcher> matchers) {
    this(matchers, null);
  }

  @Override
  public final void consumeChar(final char c) {
    matchers.forEach(it -> it.consumeChar(c));
  }

  @Override
  public final @Nullable Token tryProduce() {
    return matchers
      .stream()
      .map(Matcher::tryProduce)
      .filter(Objects::nonNull)
      .findFirst()
      .orElse(null);
  }

  @Override
  public final boolean canMatchLater() {
    return matchers
      .stream()
      .anyMatch(Matcher::canMatchLater);
  }

  @Override
  @Contract(pure = true)
  public final @NotNull List<String> getExpectations() {
    if (descriptions != null) return descriptions;
    final @NotNull List<@NotNull String> descriptions = matchers
      .stream()
      .flatMap(matcher -> matcher.getExpectations().stream())
      .collect(Collectors.toList());
    this.descriptions = descriptions;
    return descriptions;
  }

  @Override
  public final void reset() {
    matchers.forEach(Matcher::reset);
  }
}
