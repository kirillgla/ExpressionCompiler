package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;

import java.util.List;

public final class BadTokenException extends Exception {
  private @NotNull List<@NotNull Token> errors;

  public BadTokenException(final @NotNull List<@NotNull Token> errors) {
    this.errors = errors;
  }

  public final @NotNull List<@NotNull Token> getErrors() {
    return errors;
  }
}
