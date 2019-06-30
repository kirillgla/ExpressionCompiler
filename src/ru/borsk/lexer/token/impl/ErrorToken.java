package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitorBase;

public final class ErrorToken implements Token {
  private final char problem;

  @Contract(pure = true)
  public final char getProblem() {
    return problem;
  }

  public ErrorToken(final char problem) {
    this.problem = problem;
  }

  @Override
  public void visit(final @NotNull TokenVisitorBase visitor) {
    visitor.visitError(this);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof ErrorToken)) return false;
    final ErrorToken other = (ErrorToken) obj;
    return problem == other.problem;
  }
}
