package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;

import java.util.List;

public class MockLexer implements Lexer {
  private final @NotNull List<@NotNull Token> tokens;

  public MockLexer(final @NotNull List<@NotNull Token> tokens) {
    this.tokens = tokens;
  }

  @Override
  public @NotNull List<@NotNull Token> scan() {
    return tokens;
  }
}
