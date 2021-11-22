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
        switch (node) {
            case MinusContext minusContext -> {

                final Operand leftOp = parse(minusContext.getChild(0));
                final Operand rightOp = parse(minusContext.getChild(2));
                return Expr.binary("-", leftOp, rightOp);

            }
            case PlusContext plusContext -> {

                final Operand leftOp = parse(plusContext.getChild(0));
                final Operand rightOp = parse(plusContext.getChild(2));
                return Expr.binary("+", leftOp, rightOp);

            }
            case MultiplyContext multiplyContext -> {

                final Operand leftOp = parse(multiplyContext.getChild(0));
                final Operand rightOp = parse(multiplyContext.getChild(2));
                return Expr.binary("*", leftOp, rightOp);

            }
            case DivisionContext divisionContext -> {

                final Operand leftOp = parse(divisionContext.getChild(0));
                final Operand rightOp = parse(divisionContext.getChild(2));
                return Expr.binary("/", leftOp, rightOp);

            }
            case BracketsContext bracketsContext -> {

                final Operand operand = parse(bracketsContext.getChild(1));
                return Expr.unary("()", operand);

            }
            case UnaryMinusContext unaryMinusContext -> {

                final Operand operand = parse(unaryMinusContext.getChild(0));
                return Expr.unary("--", operand);

            }
            case OperandContext operandContext -> {

                final String val = operandContext.getChild(0).toString();
                final Operand operand;
                if (isNumeric(val)) {
                    operand = Expr.number(Double.parseDouble(val));
                } else {
                    operand = Expr.variable(val);
                }
                return operand;
            }
            default -> {
                return Expr.empty();
            }
        }
    }
}
