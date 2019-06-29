package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import ru.borsk.lexer.token.Token;

public final class ErrorToken implements Token {
  private final char problem;

  @Contract(pure = true)
  public final char getProblem() {
    return problem;
  }

  public ErrorToken(final char problem) {
    this.problem = problem;
  }
}
