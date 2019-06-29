package ru.borsk.lexer;

public final class Filter {
  public boolean shouldUse(final char c) {
    return !Character.isWhitespace(c);
  }
}
