package ru.borsk.lexer.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.common.processors.LanguageProcessorBase;
import ru.borsk.common.text.Utils;
import ru.borsk.lexer.Lexer;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.matcher.impl.*;
import ru.borsk.lexer.token.Token;

import java.util.ArrayList;
import java.util.List;

public final class BasicLexer extends LanguageProcessorBase<Character> implements Lexer {
  private final @NotNull Matcher matcher;

  public BasicLexer(final @NotNull String input) {
    super(Utils.asList(input));
    matcher = new CompositeMatcher(createDefaultMatchers());
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

  private @NotNull Token scanNext() {
    saveLookaheadIndex();
    @Nullable Token lastBestMatch = null;
    while (canContinue() && matcher.canMatchLater()) {
      matcher.consumeChar(getLookahead());
      advance();
      final @Nullable Token token = matcher.tryProduce();
      if (token != null) {
        lastBestMatch = token;
        saveLookaheadIndex();
      }
    }
    matcher.reset();
    restoreLookaheadIndex();
    if (lastBestMatch == null) throw new IllegalStateException();
    return lastBestMatch;
  }
}
