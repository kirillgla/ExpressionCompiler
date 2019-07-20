package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class CommandLineOptions {
  private final @NotNull String contents;
  private final @NotNull Path destination;

  private CommandLineOptions(final @NotNull String contents, final @NotNull Path destination) {
    this.contents = contents;
    this.destination = destination;
  }

  public static CommandLineOptions create(final String[] args) throws OperationAbortedException {
    if (args.length != 3 || !args[1].equals("-o")) throw new OperationAbortedException(
      "usage:\n    expressioncompiler <source-file> -o <destination-file>");

    try {
      final String content = new String(Files.readAllBytes(Paths.get(args[0])), StandardCharsets.UTF_8);
      final Path destination = Paths.get(args[2]);
      return new CommandLineOptions(content, destination);
    } catch (IOException e) {
      throw new OperationAbortedException("file not found");
    }
  }

  @NotNull
  public String getContents() {
    return contents;
  }

  @NotNull
  public Path getDestination() {
    return destination;
  }
}
