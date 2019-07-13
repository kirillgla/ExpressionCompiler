package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.ValidToken;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Verifier {
  public final @NotNull List<@NotNull ValidToken> verify(final @NotNull List<@NotNull Token> tokens)
    throws BadTokenException {
    final @NotNull List<@NotNull Token> errorTokens =
      tokens.stream().filter(Token::isValid).collect(Collectors.toList());
    if (!errorTokens.isEmpty()) throw new BadTokenException(errorTokens);
    return tokens.stream().map(Token::toValidToken).filter(Objects::nonNull).collect(Collectors.toList());
  }
}
