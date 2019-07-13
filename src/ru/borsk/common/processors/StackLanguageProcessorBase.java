package ru.borsk.common.processors;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Stack;

public abstract class StackLanguageProcessorBase<T> extends LanguageProcessorBase<T> {
  private Stack<Integer> savedIndices = new Stack<>();

  public StackLanguageProcessorBase(final @NotNull List<@NotNull T> elements) {
    super(elements);
  }

  protected final void stackSaveLookaheadIndex() {
    savedIndices.push(getLookaheadIndex());
  }

  protected final void stackRestoreLookaheadIndex() {
    setLookaheadIndex(savedIndices.peek());
  }

  protected final void stackPopLookaheadIndex() {
    savedIndices.pop();
  }
}
