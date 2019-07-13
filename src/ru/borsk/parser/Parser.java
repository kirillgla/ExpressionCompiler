package ru.borsk.parser;

import org.jetbrains.annotations.NotNull;
import ru.borsk.common.LanguageProcessorBase;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.node.Node;

import java.util.List;

public final class Parser extends LanguageProcessorBase<ValidToken> {
  public Parser(@NotNull final List<@NotNull ValidToken> tokens) {
    super(tokens);
  }

  public @NotNull Node parse() {
    throw new UnsupportedOperationException("Not implemented");
  }

  private @NotNull Node addend() {

  }
}
