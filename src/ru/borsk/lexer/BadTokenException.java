package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.impl.ErrorToken;

import java.util.List;

public final class BadTokenException extends Exception {
  private @NotNull List<@NotNull ErrorToken> errors;

  public final @NotNull List<@NotNull ErrorToken> getErrors() {
    return errors;
  }

  public BadTokenException(final @NotNull List<@NotNull ErrorToken> errors) {
    this.errors = errors;
  }
}
