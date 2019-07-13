package ru.borsk.common;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LanguageProcessorBase<T> {
  private final @NotNull List<@NotNull T> elements;
  private int lookaheadIndex;
  private int savedLookaheadIndex; // TODO: use stack for that

  public LanguageProcessorBase(final @NotNull List<@NotNull T> elements) {
    this.elements = elements;
    lookaheadIndex = 0;
  }

  protected final boolean canContinue() {
    return lookaheadIndex < elements.size();
  }

  protected final void advance() {
    lookaheadIndex += 1;
  }

  protected final void saveLookaheadIndex() {
    savedLookaheadIndex = lookaheadIndex;
  }

  protected final void restoreLookaheadIndex() {
    lookaheadIndex = savedLookaheadIndex;
  }

  protected final T getLookahead() {
    return elements.get(lookaheadIndex);
  }
}
