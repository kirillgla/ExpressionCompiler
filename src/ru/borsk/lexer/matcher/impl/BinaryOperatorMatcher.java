package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.operator.BinaryOperator;
import ru.borsk.common.operator.impl.AdditionOperator;
import ru.borsk.common.operator.impl.DivisionOperator;
import ru.borsk.common.operator.impl.MultiplicationOperator;
import ru.borsk.common.operator.impl.SubtractionOperator;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.impl.OperatorToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class BinaryOperatorMatcher extends CompositeMatcher {
  private static final List<Matcher> Matchers;

  static {
    final @NotNull List<@NotNull BinaryOperator> Operators = new ArrayList<>();
    Operators.add(AdditionOperator.Instance);
    Operators.add(SubtractionOperator.Instance);
    Operators.add(MultiplicationOperator.Instance);
    Operators.add(DivisionOperator.Instance);

    Matchers = Operators.stream().map(ConcreteBinaryOperatorMatcher::new).collect(Collectors.toList());
  }

  public BinaryOperatorMatcher() {
    super(Matchers, "Operator");
  }

  private static final class ConcreteBinaryOperatorMatcher extends VerbatimMatcher {
    private ConcreteBinaryOperatorMatcher(final @NotNull BinaryOperator operator) {
      super(operator.getPresentation(), input -> new OperatorToken(operator));
    }
  }
}
