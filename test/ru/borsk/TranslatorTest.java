package ru.borsk;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.borsk.common.operator.impl.AdditionOperator;
import ru.borsk.common.operator.impl.DivisionOperator;
import ru.borsk.common.operator.impl.MultiplicationOperator;
import ru.borsk.lexer.token.ValidToken;
import ru.borsk.lexer.token.impl.OperatorToken;
import ru.borsk.lexer.token.impl.ValidNumberToken;
import ru.borsk.parser.node.Node;
import ru.borsk.parser.node.impl.BinaryOperatorNode;
import ru.borsk.parser.node.impl.NumberNode;
import ru.borsk.translator.Translator;
import ru.borsk.translator.code.StackMachineCode;
import ru.borsk.translator.code.impl.OperateStackTop;
import ru.borsk.translator.code.impl.PushConstant;

import java.util.Arrays;
import java.util.List;

public final class TranslatorTest {
  private void testTranslate(@NotNull Node<? extends ValidToken> node, @NotNull StackMachineCode... expected) {
    final List<StackMachineCode> actual = new Translator(node).translate();
    Assertions.assertEquals(Arrays.asList(expected), actual);
  }

  @Test
  public void testSingleConstant() {
    // 42
    testTranslate(
      new NumberNode(new ValidNumberToken(42)),
      new PushConstant(42)
    );
  }

  @Test
  public void testSimpleExpression() {
    // 2 + 2
    testTranslate(
      new BinaryOperatorNode(
        new OperatorToken(AdditionOperator.Instance),
        new NumberNode(new ValidNumberToken(2)),
        new NumberNode(new ValidNumberToken(3))
      ),
      new PushConstant(2),
      new PushConstant(3),
      new OperateStackTop(AdditionOperator.Instance)
    );
  }

  @Test
  public void testSmallNonTrivialExpression() {
    // (2 + 3) * 4
    testTranslate(
      new BinaryOperatorNode(
        new OperatorToken(MultiplicationOperator.Instance),
        new BinaryOperatorNode(
          new OperatorToken(AdditionOperator.Instance),
          new NumberNode(new ValidNumberToken(2)),
          new NumberNode(new ValidNumberToken(3))
        ),
        new NumberNode(new ValidNumberToken(4))
      ),
      new PushConstant(2),
      new PushConstant(3),
      new OperateStackTop(AdditionOperator.Instance),
      new PushConstant(4),
      new OperateStackTop(MultiplicationOperator.Instance)
    );
  }

  @Test
  public void testSmallBalancedTree() {
    // (2 + 3) + (4 + 5)
    testTranslate(
      new BinaryOperatorNode(
        new OperatorToken(MultiplicationOperator.Instance),
        new BinaryOperatorNode(
          new OperatorToken(AdditionOperator.Instance),
          new NumberNode(new ValidNumberToken(2)),
          new NumberNode(new ValidNumberToken(3))
        ),
        new BinaryOperatorNode(
          new OperatorToken(DivisionOperator.Instance),
          new NumberNode(new ValidNumberToken(4)),
          new NumberNode(new ValidNumberToken(5))
        )
      ),
      new PushConstant(2),
      new PushConstant(3),
      new OperateStackTop(AdditionOperator.Instance),
      new PushConstant(4),
      new PushConstant(5),
      new OperateStackTop(DivisionOperator.Instance),
      new OperateStackTop(MultiplicationOperator.Instance)
    );
  }
}
