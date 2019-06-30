package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.BadTokenException;
import ru.borsk.lexer.Lexer;
import ru.borsk.lexer.Verifier;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TrustedToken;
import ru.borsk.lexer.token.impl.ErrorToken;

import java.util.List;

public final class Main {
  public static void main(String[] args) {
    try {
      final @NotNull List<@NotNull Token> tokens = new Lexer("(42 + 2)\t)*2").scan();
      final @NotNull List<@NotNull TrustedToken> trustedTokens = new Verifier().verify(tokens);
    } catch (BadTokenException e) {
      printLexerErrors(e.getErrors());
    }
  }

  private static void printLexerErrors(final @NotNull List<@NotNull ErrorToken> errors) {
    System.out.println("Bad tokens:");
    errors.stream().map(ErrorToken::getProblem).forEach(System.out::println);
  }
}
