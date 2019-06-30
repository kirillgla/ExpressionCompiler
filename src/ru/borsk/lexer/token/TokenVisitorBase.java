package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.impl.ErrorToken;
import ru.borsk.lexer.token.impl.NumberToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ParenthesisToken;

public abstract class TokenVisitorBase {
  public void visitError(@NotNull ErrorToken error) {
  }

  public void visitNumber(@NotNull NumberToken number) {
  }

  public void visitOperator(@NotNull OperatorToken operator) {
  }

  public void visitParenthesis(@NotNull ParenthesisToken parenthesis) {
  }
}
