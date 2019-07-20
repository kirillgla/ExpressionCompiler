package ru.borsk.generator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.translator.code.StackMachineCode;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public final class CodeGenerator {
  private final @NotNull List<StackMachineCode> codes;
  private final StringBuilder builder = new StringBuilder();

  public CodeGenerator(final @NotNull List<StackMachineCode> codes) {
    this.codes = codes;
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
    final StackMachineCodeConverterVisitor visitor = new StackMachineCodeConverterVisitor(builder);
    for (final StackMachineCode code : codes) {
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
