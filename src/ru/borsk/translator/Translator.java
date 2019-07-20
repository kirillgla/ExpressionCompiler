package ru.borsk.translator;

import org.jetbrains.annotations.NotNull;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.parser.node.Node;
import ru.borsk.translator.code.StackMachineCode;

import java.util.List;

public final class Translator {
  private final @NotNull Node<? extends ValidToken> tree;

  public Translator(@NotNull final Node<? extends ValidToken> tree) {
    this.tree = tree;
  }

  public @NotNull List<StackMachineCode> translate() {
    final @NotNull TranslationNodeVisitor visitor = new TranslationNodeVisitor();
    tree.visit(visitor);
    return visitor.getCodes();
  }
}
