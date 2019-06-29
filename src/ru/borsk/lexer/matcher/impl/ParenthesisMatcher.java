package ru.borsk.lexer.matcher.impl;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.parenthesis.ClosingParenthesis;
import ru.borsk.common.parenthesis.OpeningParenthesis;
import ru.borsk.common.parenthesis.Parenthesis;
import ru.borsk.lexer.matcher.Matcher;
import ru.borsk.lexer.token.impl.ParenthesisToken;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParenthesisMatcher extends CompositeMatcher {
  private static final List<Matcher> Matchers;

  static {
    final @NotNull List<@NotNull Parenthesis> Parentheses = new ArrayList<>();
    Parentheses.add(OpeningParenthesis.Instance);
    Parentheses.add(ClosingParenthesis.Instance);
    Matchers = Parentheses.stream().map(ConcreteParenthesisMatcher::new).collect(Collectors.toList());
  }

  public ParenthesisMatcher() {
    super(Matchers, "Parenthesis");
  }

  private static final class ConcreteParenthesisMatcher extends VerbatimMatcher {
    private ConcreteParenthesisMatcher(final @NotNull Parenthesis parenthesis) {
      super(parenthesis.getPresentation(), input -> new ParenthesisToken(parenthesis));
    }
  }
}
