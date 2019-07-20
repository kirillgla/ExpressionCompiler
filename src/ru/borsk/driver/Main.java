package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;
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

import java.util.List;

public final class Main {
  public static void main(String[] args) {
    try {
      final String input = "(1 + 2) * 3";
      final @NotNull List<@NotNull Token> tokens = new FilteringLexer(input).scan();
      final @NotNull List<@NotNull ValidToken> validTokens = new Verifier().verify(tokens);
      final Node<? extends ValidToken> tree = new Parser(validTokens).parse();
      final List<StackMachineCode> stackMachineCodes = new Translator(tree).translate();
    } catch (BadTokenException e) {
      printLexerErrors(e.getErrors());
    } catch (ParseFailureException e) {
      System.out.println("Parser error");
    }
  }

  private static void printLexerErrors(final @NotNull List<@NotNull Token> errors) {
    System.out.println("Bad tokens:");
    errors.stream().map(Token::getPresentation).forEach(System.out::println);
  }
}
