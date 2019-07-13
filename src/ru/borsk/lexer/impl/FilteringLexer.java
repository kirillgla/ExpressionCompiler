package ru.borsk.lexer.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.Filter;
import ru.borsk.lexer.Lexer;
import ru.borsk.lexer.token.Token;

import java.util.List;
import java.util.stream.Collectors;

public final class FilteringLexer implements Lexer {
  private final Filter filter = new Filter();
  private final Lexer lexer;

  public FilteringLexer(final @NotNull String input) {
    this(new BasicLexer(input));
  }

  public FilteringLexer(final @NotNull Lexer lexer) {
    this.lexer = lexer;
  }

  @Override
  public @NotNull List<@NotNull Token> scan() {
    return lexer.scan().stream().filter(filter::shouldUse).collect(Collectors.toList());
  }
}
