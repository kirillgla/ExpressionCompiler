package ru.borsk.common.operator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.interfaces.Presentable;

public interface BinaryOperator extends Presentable {
  void visit(final @NotNull BinaryOperatorVisitor visitor);
}
