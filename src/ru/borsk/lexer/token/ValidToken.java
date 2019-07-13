package ru.borsk.lexer.token;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.interfaces.Presentable;

public interface ValidToken extends Presentable {
  void visitValid(final @NotNull ValidTokenVisitor visitor);
}
