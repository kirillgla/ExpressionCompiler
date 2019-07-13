package ru.borsk.lexer.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.Lexer;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.matcher.impl.*;
import ru.borsk.lexer.token.Token;

import java.util.ArrayList;
import java.util.List;

public final class BasicLexer implements Lexer {
  private final @NotNull Matcher matcher;
  private final @NotNull String input;
  private int lookaheadIndex;

  public BasicLexer(final @NotNull String input) {
    this.input = input;
    matcher = new CompositeMatcher(createDefaultMatchers());
    start();
  }

  @Override
  public @NotNull List<@NotNull Token> scan() {
    final @NotNull List<@NotNull Token> result = new ArrayList<>();
    while (canContinue()) {
      result.add(scanNext());
    }
    return result;
  }

  private @NotNull List<@NotNull Matcher> createDefaultMatchers() {
    final @NotNull List<@NotNull Matcher> matchers = new ArrayList<>();
    matchers.add(new NumberMatcher());
    matchers.add(new BinaryOperatorMatcher());
    matchers.add(new ParenthesisMatcher());
    matchers.add(new WhitespaceMatcher());
    matchers.add(new ErrorMatcher()); // should be last
    return matchers;
  }

  private void advance() {
    lookaheadIndex += 1;
  }

  private void start() {
    lookaheadIndex = -1;
    advance();
  }

  private boolean canContinue() {
    return lookaheadIndex < input.length();
  }

  private @NotNull Token scanNext() {
    int lastBestMatchLookaheadIndex = lookaheadIndex;
    @Nullable Token lastBestMatch = null;
    while (canContinue() && matcher.canMatchLater()) {
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
    if (lastBestMatch == null) throw new IllegalStateException();
    return lastBestMatch;
  }
}
