package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.matcher.impl.BinaryOperatorMatcher;
import ru.borsk.lexer.matcher.impl.CompositeMatcher;
import ru.borsk.lexer.matcher.impl.NumberMatcher;
import ru.borsk.lexer.matcher.impl.ParenthesisMatcher;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.impl.ErrorToken;

import java.util.ArrayList;
import java.util.List;

public final class Lexer {
  private final @NotNull Filter filter;
  private final @NotNull Matcher matcher;
  private final @NotNull String input;
  private int lookaheadIndex;

  public Lexer(final @NotNull String input) {
    this.filter = new Filter();
    this.input = input;
    matcher = new CompositeMatcher(createDefaultMatchers());
    start();
  }

  public @NotNull List<@NotNull Token> scan() {
    final @NotNull List<@NotNull Token> result = new ArrayList<>();
    while (!hasFinished()) {
      result.add(scanNext());
    }
    return result;
  }

  private @NotNull List<@NotNull Matcher> createDefaultMatchers() {
    final @NotNull List<@NotNull Matcher> matchers = new ArrayList<>();
    matchers.add(new NumberMatcher());
    matchers.add(new BinaryOperatorMatcher());
    matchers.add(new ParenthesisMatcher());
    return matchers;
  }

  private void advance() {
    lookaheadIndex += 1;
    while (lookaheadIndex < input.length() && !filter.shouldUse(input.charAt(lookaheadIndex))) {
      lookaheadIndex += 1;
    }
  }

  private void start() {
    lookaheadIndex = -1;
    advance();
  }

  private boolean hasFinished() {
    return lookaheadIndex >= input.length();
  }

  private @NotNull Token scanNext() {
    int lastBestMatchLookaheadIndex = lookaheadIndex;
    @Nullable Token lastBestMatch = null;
    while (!hasFinished() && matcher.canMatchLater()) {
      matcher.consumeChar(input.charAt(lookaheadIndex));
      advance();
      final @Nullable Token token = matcher.tryProduce();
      if (token != null) {
        lastBestMatch = token;
        lastBestMatchLookaheadIndex = lookaheadIndex;
      }
    }
    matcher.reset();
    lookaheadIndex = lastBestMatchLookaheadIndex;
    if (lastBestMatch == null) return error();
    return lastBestMatch;
  }

  private @NotNull Token error() {
    if (hasFinished())
      throw new IllegalStateException();
    final @NotNull Token result = new ErrorToken(input.charAt(lookaheadIndex));
    advance();
    return result;
  }
}
