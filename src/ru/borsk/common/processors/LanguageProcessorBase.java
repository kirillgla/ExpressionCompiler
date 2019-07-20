package ru.borsk.common.processors;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public abstract class LanguageProcessorBase<TInput> {
  private final @NotNull List<@NotNull TInput> elements;
  private int lookaheadIndex;
  private int savedLookaheadIndex;

  public LanguageProcessorBase(final @NotNull List<@NotNull TInput> elements) {
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

  protected final TInput getLookahead() {
    return elements.get(lookaheadIndex);
  }

  protected final int getLookaheadIndex() {
    return lookaheadIndex;
  }

  protected final void setLookaheadIndex(final int lookaheadIndex) {
    this.lookaheadIndex = lookaheadIndex;
  }
}
