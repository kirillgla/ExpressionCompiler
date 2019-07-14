package ru.borsk.parser.handlers;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.ParseFailureException;

import java.util.function.Function;

public interface ParseHandler<TResult> {
  TResult consume(final @NotNull ValidToken source) throws ParseFailureException;
}
