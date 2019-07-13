package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.ValidToken;

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
  public final boolean isValid() {
    return true;
  }

  @Override
  public final @Nullable ValidToken toValidToken() {
    return null;
  }

  @Override
  public @NotNull String getPresentation() {
    return Character.toString(problem);
  }

  @Override
  public final boolean equals(final Object obj) {
    if (!(obj instanceof ErrorToken)) return false;
    final ErrorToken other = (ErrorToken)obj;
    return problem == other.problem;
  }

  @Override
  public void visit(final @NotNull TokenVisitor visitor) {
    visitor.visitError(this);
  }
}
