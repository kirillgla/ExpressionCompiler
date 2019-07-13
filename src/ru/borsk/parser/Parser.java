package ru.borsk.parser;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.interfaces.ThrowingProvider;
import ru.borsk.common.processors.StackLanguageProcessorBase;
import ru.borsk.lexer.matcher.impl.NumberMatcher;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.matchers.Matcher;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.impl.NumberNode;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

public final class Parser extends StackLanguageProcessorBase<ValidToken> {
  public Parser(@NotNull final List<@NotNull ValidToken> tokens) {
    super(tokens);
  }

  public @NotNull Node<? extends ValidToken> parse() {
    throw new UnsupportedOperationException("Not implemented");
  }

  private @NotNull Node<? extends ValidToken> addend() {

  }

  private @NotNull Node<? extends ValidToken> factor() {

  }

  private Node<? extends ValidToken> atom() throws ParseFailureException {
    return tryAll(
//      () -> new NumberNode(match(ValidNumberToken.class)),
//      () -> {
//      }
    );
  }

//  @SafeVarargs
//  private final Node<? extends ValidToken> tryAll(
//    ThrowingProvider<? extends Node<? extends ValidToken>, ParseFailureException>... providers
//  ) throws ParseFailureException {
//    stackSaveLookaheadIndex();
//    for (final ThrowingProvider<? extends Node<? extends ValidToken>, ParseFailureException> provider : providers) {
//      try {
//        final Node<? extends ValidToken> result = provider.provide();
//        stackPopLookaheadIndex();
//        return result;
//      } catch (ParseFailureException e) {
//        stackRestoreLookaheadIndex();
//      }
//    }
//    throw new ParseFailureException();
//  }

  @NotNull
  @SafeVarargs
  private final <TResult> TResult tryAll(@NotNull Matcher<? super ValidToken, TResult>... matchers)
    throws ParseFailureException {
    stackSaveLookaheadIndex();
    final @NotNull ValidToken lookahead = getLookahead();
    for (final @NotNull Matcher<? super ValidToken, TResult> matcher : matchers) {
      try {
        final TResult result = matcher.consume(lookahead);
        stackPopLookaheadIndex();
        advance();
        return result;
      } catch (ParseFailureException e) {
        stackRestoreLookaheadIndex();
      }
    }
    throw new ParseFailureException();
  }

  private <TToken> @NotNull TToken match(Class<TToken> c) throws ParseFailureException {
    final @NotNull ValidToken lookahead = getLookahead();
    if (!c.isInstance(lookahead)) throw new ParseFailureException();
    advance();
    return c.cast(lookahead);
  }

  private @NotNull ValidToken match(Predicate<ValidToken> predicate) throws ParseFailureException {
    final @NotNull ValidToken lookahead = getLookahead();
    if (!predicate.test(lookahead)) throw new ParseFailureException();
    advance();
    return lookahead;
  }
}
