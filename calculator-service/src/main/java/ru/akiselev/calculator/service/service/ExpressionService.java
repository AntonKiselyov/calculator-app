package ru.akiselev.calculator.service.service;

import com.google.common.base.Preconditions;
import com.google.inject.Singleton;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import ru.akiselev.calculator.service.CalculatorLexer;
import ru.akiselev.calculator.service.CalculatorParser;
import ru.akiselev.calculator.service.CalculatorParser.BracketsContext;
import ru.akiselev.calculator.service.CalculatorParser.DivisionContext;
import ru.akiselev.calculator.service.CalculatorParser.MinusContext;
import ru.akiselev.calculator.service.CalculatorParser.MultiplyContext;
import ru.akiselev.calculator.service.CalculatorParser.OperandContext;
import ru.akiselev.calculator.service.CalculatorParser.PlusContext;
import ru.akiselev.calculator.service.CalculatorParser.UnaryMinusContext;
import ru.akiselev.calculator.service.dto.Expr;
import ru.akiselev.calculator.service.dto.Operand;

import static org.apache.commons.lang3.StringUtils.isNumeric;

@Singleton
public class ExpressionService {

    public Operand buildExpression(final String expression) {
        final CalculatorLexer lexer = new CalculatorLexer(CharStreams.fromString(expression));
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final CalculatorParser parser = new CalculatorParser(tokens);
        final ParseTree tree = parser.expression();
        return parse(tree);
    }

    private Operand parse(final ParseTree node) {
        Preconditions.checkNotNull(node);
        if (node instanceof MinusContext) {

            final Operand leftOp = parse(node.getChild(0));
            final Operand rightOp = parse(node.getChild(2));
            return Expr.binary("-", leftOp, rightOp);

        } else if (node instanceof PlusContext) {

            final Operand leftOp = parse(node.getChild(0));
            final Operand rightOp = parse(node.getChild(2));
            return Expr.binary("+", leftOp, rightOp);

        } else if (node instanceof MultiplyContext) {

            final Operand leftOp = parse(node.getChild(0));
            final Operand rightOp = parse(node.getChild(2));
            return Expr.binary("*", leftOp, rightOp);

        } else if (node instanceof DivisionContext) {

            final Operand leftOp = parse(node.getChild(0));
            final Operand rightOp = parse(node.getChild(2));
            return Expr.binary("/", leftOp, rightOp);

        } else if (node instanceof BracketsContext) {

            final Operand operand = parse(node.getChild(1));
            return Expr.unary("()", operand);

        } else if (node instanceof UnaryMinusContext) {

            final Operand operand = parse(node.getChild(0));
            return Expr.unary("--", operand);

        } else if (node instanceof OperandContext) {

            final String val = node.getChild(0).toString();
            final Operand operand;
            if (isNumeric(val)) {
                operand = Expr.number(Double.parseDouble(val));
            } else {
                operand = Expr.variable(val);
            }
            return operand;
        }
        return Expr.empty();
    }
}
