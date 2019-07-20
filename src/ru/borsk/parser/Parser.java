package ru.borsk.parser;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.interfaces.ThrowingProvider;
import ru.borsk.common.processors.StackLanguageProcessorBase;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.parser.handlers.ParseHandler;
import ru.borsk.parser.handlers.impl.*;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.impl.BinaryOperatorNode;
import ru.borsk.parser.node.impl.NumberNode;

import java.util.List;

public final class Parser extends StackLanguageProcessorBase<ValidToken> {
  public Parser(@NotNull final List<@NotNull ValidToken> tokens) {
    super(tokens);
  }

  public @NotNull Node<? extends ValidToken> parse() throws ParseFailureException {
    final Node<? extends ValidToken> result = addend();
    throwIfNotCompleted();
    return result;
  }

  private @NotNull Node<? extends ValidToken> addend() throws ParseFailureException {
    final Node<? extends ValidToken> factor = factor();
    stackSaveLookaheadIndex();
    try {
      final OperatorToken operator = match(new AdditiveOperatorParseHandler());
      return addend(factor, operator);
    } catch (ParseFailureException e) {
      stackRestoreLookaheadIndex();
      return factor;
    } finally {
      stackPopLookaheadIndex();
    }
  }

  private @NotNull Node<? extends ValidToken> addend(
    final @NotNull Node<? extends ValidToken> node,
    final @NotNull OperatorToken operator
  ) throws ParseFailureException {
    final Node<? extends ValidToken> factor = factor();
    final BinaryOperatorNode newNode = new BinaryOperatorNode(operator, node, factor);
    stackSaveLookaheadIndex();
    try {
      final OperatorToken newOperator = match(new AdditiveOperatorParseHandler());
      return addend(newNode, newOperator);
    } catch (ParseFailureException e) {
      stackRestoreLookaheadIndex();
      return newNode;
    } finally {
      stackPopLookaheadIndex();
    }
  }

  private @NotNull Node<? extends ValidToken> factor() throws ParseFailureException {
    final Node<? extends ValidToken> atom = atom();
    stackSaveLookaheadIndex();
    try {
      final OperatorToken operator = match(new MultiplicativeOperatorParseHandler());
      return factor(atom, operator);
    } catch (ParseFailureException e) {
      stackRestoreLookaheadIndex();
      return atom;
    } finally {
      stackPopLookaheadIndex();
    }
  }

  private @NotNull Node<? extends ValidToken> factor(
    final @NotNull Node<? extends ValidToken> node,
    final @NotNull OperatorToken operator
  ) throws ParseFailureException {
    final Node<? extends ValidToken> atom = atom();
    final BinaryOperatorNode newNode = new BinaryOperatorNode(operator, node, atom);
    stackSaveLookaheadIndex();
    try {
      final OperatorToken newOperator = match(new MultiplicativeOperatorParseHandler());
      return factor(newNode, newOperator);
    } catch (ParseFailureException e) {
      stackRestoreLookaheadIndex();
      return newNode;
    } finally {
      stackPopLookaheadIndex();
    }
  }

  private Node<? extends ValidToken> atom() throws ParseFailureException {
    return tryAll(
      () -> new NumberNode(match(new ValidNumberParseHandler())),
      () -> {
        match(new OpenParenthesisParseHandler());
        final Node<? extends ValidToken> result = addend();
        match(new ClosingParenthesisParseHandler());
        return result;
      }
    );
  }

  @SafeVarargs
  private final <TResult> TResult tryAll(ThrowingProvider<TResult, ParseFailureException>... providers)
    throws ParseFailureException {
    stackSaveLookaheadIndex();
    for (final ThrowingProvider<TResult, ParseFailureException> provider : providers) {
      try {
        final TResult result = provider.provide();
        stackPopLookaheadIndex();
        return result;
      } catch (ParseFailureException e) {
        stackRestoreLookaheadIndex();
      }
    }
    stackPopLookaheadIndex();
    throw new ParseFailureException();
  }

  private <TResult> TResult match(final @NotNull ParseHandler<TResult> handler)
    throws ParseFailureException {
    if (!canContinue()) throw new ParseFailureException();
    final @NotNull ValidToken lookahead = getLookahead();
    final TResult result = handler.consume(lookahead);
    advance();
    return result;
  }
}
