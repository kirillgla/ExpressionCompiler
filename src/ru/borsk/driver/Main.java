package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.BadTokenException;
import ru.borsk.lexer.Verifier;
import ru.borsk.lexer.impl.FilteringLexer;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.ValidToken;

import java.util.List;

public final class Main {
  public static void main(String[] args) {
    try {
      final @NotNull List<@NotNull Token> tokens =
        new FilteringLexer("(42 + 2)\t)*2 9999999999999999999999999999999").scan();
      final @NotNull List<@NotNull ValidToken> trustedTokens = new Verifier().verify(tokens);
    } catch (BadTokenException e) {
      printLexerErrors(e.getErrors());
    }
  }

  private static void printLexerErrors(final @NotNull List<@NotNull Token> errors) {
    System.out.println("Bad tokens:");
    errors.stream().map(Token::getPresentation).forEach(System.out::println);
  }
}
