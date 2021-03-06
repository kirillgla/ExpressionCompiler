package ru.borsk.common.processors;

import org.jetbrains.annotations.NotNull;
import ru.borsk.parser.ParseFailureException;

import java.util.List;
import java.util.Stack;

public abstract class StackLanguageProcessorBase<T> extends LanguageProcessorBase<T> {
  private Stack<Integer> savedIndices = new Stack<>();

  public StackLanguageProcessorBase(final @NotNull List<@NotNull T> elements) {
    super(elements);
  }

  protected final void throwIfNotCompleted() throws ParseFailureException {
    if (canContinue()) throw new ParseFailureException();
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
