package ru.borsk.lexer.token.impl;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.TokenVisitor;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.ValidTokenVisitor;

public final class OperatorToken implements Token, ValidToken {
  private @NotNull BinaryOperator operator;

  @Contract(pure = true)
  public @NotNull BinaryOperator getOperator() {
    return operator;
  }

  public OperatorToken(@NotNull BinaryOperator operator) {
    this.operator = operator;
  }

  @Override
  public boolean isValid() {
    return false;
  }

  @Override
  public @NotNull ValidToken toValidToken() {
    return this;
  }

  @Override
  public @NotNull String getPresentation() {
    return operator.getPresentation();
  }

  @Override
  public boolean equals(final Object obj) {
    if (!(obj instanceof OperatorToken)) return false;
    final OperatorToken other = (OperatorToken)obj;
    // Reference comparison will do because operators are singletons
    return operator == other.operator;
  }

  @Override
  public void visit(final @NotNull TokenVisitor visitor) {
    visitor.visitOperator(this);
  }

  @Override
  public void visitValid(final @NotNull ValidTokenVisitor visitor) {
    visitor.visitOperator(this);
  }
}
