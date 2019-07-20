package ru.borsk.analyzer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;

import java.util.List;

public final class StackMachineCodeAnalysisResult {
  private final int maxDepth;
  private final @NotNull List<AnnotatedStackMachineCode> codes;

  public StackMachineCodeAnalysisResult(final int maxDepth, final @NotNull List<AnnotatedStackMachineCode> codes) {
    this.maxDepth = maxDepth;
    this.codes = codes;
  }

  public int getMaxDepth() {
    return maxDepth;
  }

  @NotNull
  public List<AnnotatedStackMachineCode> getCodes() {
    return codes;
  }
}
