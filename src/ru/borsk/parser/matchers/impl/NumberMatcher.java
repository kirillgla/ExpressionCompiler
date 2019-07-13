package ru.borsk.parser.matchers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.ParseFailureException;
import ru.borsk.parser.matchers.VisitorMatcherBase;

public final class NumberMatcher<TSource extends ValidToken> extends VisitorMatcherBase<TSource, ValidNumberToken> {
  private @Nullable ValidNumberToken token;

  @Override
  protected ValidNumberToken provideResult() throws ParseFailureException {
    if (token == null) throw new ParseFailureException();
    return token;
  }

  @Override
  public void visitValidNumber(final @NotNull ValidNumberToken validNumber) {
    token = validNumber;
  }
}
