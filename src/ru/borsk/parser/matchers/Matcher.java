package ru.borsk.parser.matchers;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.ParseFailureException;

@FunctionalInterface
public interface Matcher<TSource extends ValidToken, TResult> {
  TResult consume(final @NotNull TSource source) throws ParseFailureException;
}
