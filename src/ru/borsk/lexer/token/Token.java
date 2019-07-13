package ru.borsk.lexer.token;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.borsk.common.interfaces.Presentable;

public interface Token extends Presentable {
  @Contract(pure = true)
  boolean isValid();

  @Nullable
  ValidToken toValidToken();

  void visit(final @NotNull TokenVisitor visitor);
}
