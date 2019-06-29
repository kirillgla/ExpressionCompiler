package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.Token;

public final class OperatorToken implements Token {
  private @NotNull BinaryOperator operator;

  @Contract(pure = true)
  private @NotNull BinaryOperator getOperator() {
    return operator;
  }

  public OperatorToken(@NotNull BinaryOperator operator) {
    this.operator = operator;
  }
}
