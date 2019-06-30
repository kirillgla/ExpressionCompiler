package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TrustedToken;
import ru.borsk.lexer.token.impl.ErrorToken;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Verifier {
  public final @NotNull List<@NotNull TrustedToken> verify(final @NotNull List<@NotNull Token> tokens)
    throws BadTokenException {
    final @NotNull Stream<@NotNull ErrorToken> errorTokens = tokens
      .stream()
      .filter(ErrorToken.class::isInstance)
      .map(ErrorToken.class::cast);
    if (errorTokens.findAny().isPresent()) throw new BadTokenException(errorTokens.collect(Collectors.toList()));
    return tokens.stream().map((TrustedToken.class::cast)).collect(Collectors.toList());
  }
}
