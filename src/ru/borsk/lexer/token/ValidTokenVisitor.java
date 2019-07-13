package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ParenthesisToken;
import ru.borsk.lexer.token.impl.ValidNumberToken;

public abstract class ValidTokenVisitor {
  public void visitOperator(final @NotNull OperatorToken operator) {
  }

  public void visitParenthesis(final @NotNull ParenthesisToken parenthesis) {
  }

  public void visitValidNumber(final @NotNull ValidNumberToken validNumber) {
  }
}
