package ru.borsk.common.operator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.impl.AdditionOperator;
import ru.borsk.common.operator.impl.DivisionOperator;
import ru.borsk.common.operator.impl.MultiplicationOperator;
import ru.borsk.common.operator.impl.SubtractionOperator;

public abstract class BinaryOperatorVisitor {
  public void visitAddition(final @NotNull AdditionOperator operator) {
  }

  public void visitSubtraction(final @NotNull SubtractionOperator operator) {
  }

  public void visitMultiplication(final @NotNull MultiplicationOperator operator) {
  }

  public void visitDivision(final @NotNull DivisionOperator operator) {
  }
}
