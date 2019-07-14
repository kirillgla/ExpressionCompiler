package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.ValidTokenVisitor;
import ru.borsk.parser.ParseFailureException;
import ru.borsk.parser.handlers.ParseHandler;

public abstract class VisitorParseHandlerBase<TResult> extends ValidTokenVisitor implements ParseHandler<TResult> {
  protected abstract @NotNull TResult provideResult() throws ParseFailureException;

  @Override
  public final TResult consume(final @NotNull ValidToken token) throws ParseFailureException {
    token.visitValid(this);
    return provideResult();
  }
}
