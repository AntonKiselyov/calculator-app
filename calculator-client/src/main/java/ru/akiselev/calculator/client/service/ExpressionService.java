package ru.akiselev.calculator.client.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.akiselev.calculator.client.client.ExpressionClient;
import ru.akiselev.calculator.client.client.dto.Operand;
import ru.akiselev.calculator.client.dto.ExpressionRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Slf4j
@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionService {

    private final ExpressionClient expressionClient;

    public double evaluateExpr(final ExpressionRequest request) {
        Preconditions.checkNotNull(request, "Expression request can't be null.");
        Preconditions.checkNotNull(request.getExpression(), "Expression can't be null.");
        final Operand expr = expressionClient.buildExpression(request.getExpression());
        log.info(format("Expression: %s", expr));
        return substituteVariables(expr, request.getParams()).evaluate();
    }

    private Operand substituteVariables(final Operand operand, final Map<String, Double> params) {
        Preconditions.checkNotNull(params, "Parameters can't be null.");
        Preconditions.checkNotNull(operand, "Expression can't be null");

        if (operand instanceof Operand.Expr) {
            final Operand.Expr expr = (Operand.Expr) operand;

            final List<Operand> args = expr.args()
                    .stream()
                    .map(arg -> substituteVariables(arg, params))
                    .collect(Collectors.toList());

            if (expr instanceof Operand.BinaryExpr) {

                final Operand.BinaryExpr binaryExpr = (Operand.BinaryExpr) expr;
                return Operand.binary(binaryExpr.symbol(), binaryExpr.getOperator(), args);

            } else if (expr instanceof Operand.UnaryExpr) {

                final Operand.UnaryExpr unaryExpr = (Operand.UnaryExpr) expr;
                return Operand.unary(unaryExpr.symbol(), unaryExpr.getOperator(), args);

            }
        } else if (operand instanceof Operand.Variable) {

            final Operand.Variable variable = (Operand.Variable) operand;
            if (params.containsKey(variable.getVar())) {
                return Operand.number(params.get(variable.getVar()));
            }
            return operand;

        }
        return operand;
    }
}
