package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitorBase;

public final class OperatorToken implements Token {
  private @NotNull BinaryOperator operator;

  @Contract(pure = true)
  private @NotNull BinaryOperator getOperator() {
    return operator;
  }

  public OperatorToken(@NotNull BinaryOperator operator) {
    this.operator = operator;
  }

  @Override
  public void visit(final @NotNull TokenVisitorBase visitor) {
    visitor.visitOperator(this);
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof OperatorToken)) return false;
    final OperatorToken other = (OperatorToken) obj;
    // Reference comparison will do because operators are singletons
    return operator == other.operator;
  }
}
