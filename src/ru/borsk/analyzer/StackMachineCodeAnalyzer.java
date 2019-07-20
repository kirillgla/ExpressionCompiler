package ru.borsk.analyzer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;
import ru.borsk.translator.code.StackMachineCode;

import java.util.ArrayList;
import java.util.List;

public final class StackMachineCodeAnalyzer {
  // +1 for format specifier "%d\0"
  public static final int ADDITIONAL_STACK_ITEMS = 1;
  private final @NotNull List<StackMachineCode> codes;

  public StackMachineCodeAnalyzer(final @NotNull List<StackMachineCode> codes) {
    this.codes = codes;
  }

  public final StackMachineCodeAnalysisResult analyze() {
    final StackMachineCodeAnalyzerVisitor visitor = new StackMachineCodeAnalyzerVisitor();
    final List<AnnotatedStackMachineCode> annotatedCodes = new ArrayList<>();
    for (final StackMachineCode code : codes) {
      code.accept(visitor);
      annotatedCodes.add(visitor.getCurrentCode());
    }
    return new StackMachineCodeAnalysisResult(visitor.getMaxDepth(), annotatedCodes);
  }
}
