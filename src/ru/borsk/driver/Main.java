package ru.borsk.driver;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.Lexer;
import ru.borsk.lexer.token.Token;

import java.util.List;

public final class Main {
  public static void main(String[] args) {
    final @NotNull List<@NotNull Token> tokens = new Lexer("(42 + 2)\t)*2").scan();
    System.out.println("123");
  }
}
