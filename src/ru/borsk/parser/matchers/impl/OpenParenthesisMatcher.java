package ru.borsk.parser.matchers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.common.parenthesis.OpeningParenthesis;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.ParenthesisToken;
import ru.borsk.parser.ParseFailureException;
import ru.borsk.parser.matchers.VisitorMatcherBase;

public final class OpenParenthesisMatcher<TSource extends ValidToken>
  extends VisitorMatcherBase<TSource, ParenthesisToken> {
  private @Nullable ParenthesisToken token;

  @Override
  protected ParenthesisToken provideResult() throws ParseFailureException {
    if (token == null) throw new ParseFailureException();
    return token;
  }

  @Override
  public void visitParenthesis(final @NotNull ParenthesisToken parenthesis) {
    if (parenthesis.getParenthesis() != OpeningParenthesis.Instance) return;
    token = parenthesis;
  }
}
