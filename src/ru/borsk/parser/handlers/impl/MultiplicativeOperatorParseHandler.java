package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.common.operator.impl.DivisionOperator;
import ru.borsk.common.operator.impl.MultiplicationOperator;

import java.util.ArrayList;
import java.util.List;

public final class MultiplicativeOperatorParseHandler extends OperatorParseHandlerBase {
  private static final @NotNull List<@NotNull BinaryOperator> acceptedOperators = new ArrayList<>();

  static {
    acceptedOperators.add(MultiplicationOperator.Instance);
    acceptedOperators.add(DivisionOperator.Instance);
  }

  public MultiplicativeOperatorParseHandler() {
    super(acceptedOperators);
  }
}
