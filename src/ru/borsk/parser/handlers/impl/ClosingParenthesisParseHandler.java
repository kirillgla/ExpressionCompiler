package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.common.parenthesis.ClosingParenthesis;
import ru.borsk.lexer.token.impl.ParenthesisToken;
import ru.borsk.parser.ParseFailureException;

public final class ClosingParenthesisParseHandler extends VisitorParseHandlerBase<ParenthesisToken> {
  private @Nullable ParenthesisToken token;

  @NotNull
  @Override
  protected ParenthesisToken provideResult() throws ParseFailureException {
    if (token == null) throw new ParseFailureException();
    return token;
  }

  @Override
  public void visitParenthesis(final @NotNull ParenthesisToken parenthesis) {
    if (parenthesis.getParenthesis() != ClosingParenthesis.Instance) return;
    token = parenthesis;
  }
}
