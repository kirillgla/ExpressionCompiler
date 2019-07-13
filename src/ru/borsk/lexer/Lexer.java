package ru.borsk.lexer;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.Token;

import java.util.List;

public interface Lexer {
  @NotNull List<@NotNull Token> scan();
}
