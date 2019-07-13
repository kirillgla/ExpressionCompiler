package ru.borsk.parser.matchers;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.ValidTokenVisitor;
import ru.borsk.parser.ParseFailureException;

public abstract class VisitorMatcherBase<TSource extends ValidToken, TResult>
  extends ValidTokenVisitor
  implements Matcher<TSource, TResult> {

  protected abstract TResult provideResult() throws ParseFailureException;

  @Override
  public final TResult consume(final @NotNull ValidToken token) throws ParseFailureException {
    token.visitValid(this);
    return provideResult();
  }
}
