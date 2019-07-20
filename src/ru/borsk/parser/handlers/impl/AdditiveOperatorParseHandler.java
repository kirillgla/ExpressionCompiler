package ru.borsk.parser.handlers.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.AdditionOperator;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.common.operator.SubtractionOperator;

import java.util.ArrayList;
import java.util.List;

public final class AdditiveOperatorParseHandler extends OperatorParseHandlerBase {
  private static final @NotNull List<@NotNull BinaryOperator> acceptedOperators = new ArrayList<>();

  static {
    acceptedOperators.add(AdditionOperator.Instance);
    acceptedOperators.add(SubtractionOperator.Instance);
  }

  public AdditiveOperatorParseHandler() {
    super(acceptedOperators);
  }
}
