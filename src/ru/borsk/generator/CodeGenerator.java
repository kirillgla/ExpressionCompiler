package ru.borsk.generator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.StackMachineCodeAnalysisResult;
import ru.borsk.analyzer.code.AnnotatedStackMachineCode;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public final class CodeGenerator {
  public static final int SIZE_OF_INT = 4;
  private final StringBuilder builder = new StringBuilder();
  private final @NotNull StackMachineCodeAnalysisResult info;

  public CodeGenerator(final @NotNull StackMachineCodeAnalysisResult info) {
    this.info = info;
  }

  public String generate() {
    builder.setLength(0);
    generatePrefix();
    generateInstructions();
    generateSuffix();
    return builder.toString();
  }

  private void generatePrefix() {
    generateFromResource("resources/generatedPrefix.txt");
  }

  private void generateInstructions() {
    builder.append("  subl $").append((info.getMaxDepth()) * SIZE_OF_INT).append(", %esp\n");
    final StackMachineCodeConverterVisitor visitor = new StackMachineCodeConverterVisitor(builder);
    for (final AnnotatedStackMachineCode code : info.getCodes()) {
      code.accept(visitor);
    }
  }

  private void generateSuffix() {
    generateFromResource("resources/generatedSuffix.txt");
  }

  private void generateFromResource(final @NotNull String resource) {
    try (final InputStream stream = getClass().getResourceAsStream(resource);
         final Scanner scanner = new Scanner(stream).useDelimiter("\\A")) {
      if (scanner.hasNext()) builder.append(scanner.next());
    } catch (IOException ignored) {
    }
  }
}
