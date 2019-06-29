package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenFactory;

import java.util.Collections;
import java.util.List;

public class VerbatimMatcher implements Matcher {
  private final @NotNull String template;

  private final @NotNull TokenFactory<@NotNull String> factory;

  private int expectedCharIndex;

  private boolean seenPrefix;

  private final @NotNull List<@NotNull String> description;

  private VerbatimMatcher(
    final @NotNull String template,
    final @NotNull TokenFactory<@NotNull String> factory,
    final @Nullable String description
  ) {
    this.template = template;
    this.factory = factory;
    expectedCharIndex = 0;
    seenPrefix = true;
    this.description = Collections.singletonList(description != null ? description : template);
  }

  public VerbatimMatcher(final @NotNull String template, final @NotNull TokenFactory<@NotNull String> factory) {
    this(template, factory, null);
  }

  @Override
  public final void consumeChar(final char c) {
    if (template.length() <= expectedCharIndex || template.charAt(expectedCharIndex) != c) {
      seenPrefix = false;
    }
    expectedCharIndex += 1;
  }

  @Override
  public final @Nullable Token tryProduce() {
    if (!seenPrefix || expectedCharIndex != template.length()) return null;
    return factory.produce(template);
  }

  @Override
  public final boolean canMatchLater() {
    return seenPrefix && expectedCharIndex < template.length();
  }

  @Override
  @Contract(pure = true)
  public final @NotNull List<@NotNull String> getExpectations() {
    return description;
  }

  @Override
  public final void reset() {
    expectedCharIndex = 0;
    seenPrefix = true;
  }
}
