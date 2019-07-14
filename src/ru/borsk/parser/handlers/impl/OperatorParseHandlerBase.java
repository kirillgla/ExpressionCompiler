package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.parser.ParseFailureException;

import java.util.List;

public abstract class OperatorParseHandlerBase extends VisitorParseHandlerBase<OperatorToken> {
  private @Nullable OperatorToken token;

  private final @NotNull List<@NotNull BinaryOperator> acceptedTokens;

  protected OperatorParseHandlerBase(final @NotNull List<@NotNull BinaryOperator> acceptedTokens) {
    this.acceptedTokens = acceptedTokens;
  }

  @NotNull
  @Override
  protected OperatorToken provideResult() throws ParseFailureException {
    if (token == null) throw new ParseFailureException();
    return token;
  }

  @Override
  public void visitOperator(final @NotNull OperatorToken operator) {
    if (acceptedTokens.contains(operator.getOperator())) token = operator;
  }
}
