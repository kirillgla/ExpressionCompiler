package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.ParseFailureException;

public final class ValidNumberParseHandler extends VisitorParseHandlerBase<ValidNumberToken> {
  private @Nullable ValidNumberToken token;

  @Override
  protected @NotNull ValidNumberToken provideResult() throws ParseFailureException {
    if (token == null) throw new ParseFailureException();
    return token;
  }

  @Override
  public void visitValidNumber(final @NotNull ValidNumberToken validNumber) {
    token = validNumber;
  }
}
