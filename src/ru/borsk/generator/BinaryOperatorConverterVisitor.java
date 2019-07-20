package ru.borsk.generator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperatorVisitor;
import ru.borsk.common.operator.impl.AdditionOperator;
import ru.borsk.common.operator.impl.DivisionOperator;
import ru.borsk.common.operator.impl.MultiplicationOperator;
import ru.borsk.common.operator.impl.SubtractionOperator;

public final class BinaryOperatorConverterVisitor extends BinaryOperatorVisitor {
  private String instruction;

  public String getInstruction() {
    return instruction;
  }

  @Override
  public void visitAddition(final @NotNull AdditionOperator operator) {
    instruction = "addl";
  }

  @Override
  public void visitSubtraction(final @NotNull SubtractionOperator operator) {
    instruction = "subl";
  }

  @Override
  public void visitMultiplication(final @NotNull MultiplicationOperator operator) {
    instruction = "imull";
  }

  @Override
  public void visitDivision(final @NotNull DivisionOperator operator) {
    instruction = "idivl";
  }
}
