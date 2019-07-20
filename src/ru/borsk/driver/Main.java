package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;
import ru.borsk.analyzer.StackMachineCodeAnalysisResult;
import ru.borsk.analyzer.StackMachineCodeAnalyzer;
import ru.borsk.generator.CodeGenerator;
import ru.borsk.lexer.BadTokenException;
import ru.borsk.lexer.Verifier;
import ru.borsk.lexer.impl.FilteringLexer;
import ru.borsk.lexer.token.Token;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.ParseFailureException;
import ru.borsk.parser.Parser;
import ru.borsk.parser.node.Node;
import ru.borsk.translator.Translator;
import ru.borsk.translator.code.StackMachineCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class Main {
  public static void main(String[] args) {
    try {

      final CommandLineOptions options = CommandLineOptions.create(args);
      final String assemblyInstructions = compile(options.getContents());
      final Path tempFile = Files.createTempFile("", ".s");
      Files.write(tempFile, Collections.singleton(assemblyInstructions));
      runGcc(options, tempFile);

    } catch (BadTokenException e) {
      System.out.println("Bad tokens:");
      e.getErrors().stream().map(Token::getPresentation).forEach(System.out::println);
    } catch (ParseFailureException e) {
      System.out.println("Parser error");
    } catch (OperationAbortedException | InterruptedException e) {
      System.out.println(e.getMessage());
    } catch (IOException e) {
      System.out.println("Could not write to destination file");
    }
  }

  private static void runGcc(final CommandLineOptions options, final Path tempFile)
    throws IOException, InterruptedException {
    final String command = "gcc " + tempFile.toAbsolutePath() + " -o " + options.getDestination().toAbsolutePath();
    final Process process = Runtime.getRuntime().exec(command);
    process.waitFor();
    BufferedReader b = new BufferedReader(new InputStreamReader(process.getInputStream()));
    String line;

    while ((line = b.readLine()) != null) {
      System.out.println(line);
    }
  }

  @NotNull
  private static String compile(final String input) throws BadTokenException, ParseFailureException {
    final @NotNull List<@NotNull Token> tokens = new FilteringLexer(input).scan();
    final @NotNull List<@NotNull ValidToken> validTokens = new Verifier().verify(tokens);
    final Node<? extends ValidToken> tree = new Parser(validTokens).parse();
    final List<StackMachineCode> stackMachineCodes = new Translator(tree).translate();
    final StackMachineCodeAnalysisResult annotatedCodes = new StackMachineCodeAnalyzer(stackMachineCodes).analyze();
    return new CodeGenerator(annotatedCodes).generate();
  }
}
