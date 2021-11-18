package ru.akiselev.calculator.client.service;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import lombok.AllArgsConstructor;
import ru.akiselev.calculator.client.client.ExpressionClient;
import ru.akiselev.calculator.client.client.dto.Operand;
import ru.akiselev.calculator.client.dto.ExpressionRequest;

import java.util.Map;

@Singleton
@AllArgsConstructor(onConstructor = @__({@Inject}))
public class ExpressionService {

    private final ExpressionClient expressionClient;

    public double evaluateExpr(final ExpressionRequest request) {
        Preconditions.checkNotNull(request, "Expression request can't be null.");
        Preconditions.checkNotNull(request.getExpression(), "Expression can't be null.");
        final Operand expr = expressionClient.buildExpression(request.getExpression());
        System.out.println(expr);
        applyParamsToVariables(expr, request.getParams());
        return expr.evaluate();
    }

    private void applyParamsToVariables(final Operand operand, final Map<String, Double> params) {
        Preconditions.checkNotNull(params, "Parameters can't be null.");
        Preconditions.checkNotNull(operand, "Expression can't be null");
        if (operand instanceof Operand.Variable) {
            final Operand.Variable variable = (Operand.Variable) operand;
            if (params.containsKey(variable.getVal())) {
                final Double substitute = params.get(variable.getVal());
                variable.applySubstitute(substitute);
            }
        } else if (operand instanceof Operand.Expr) {
            final Operand.Expr e = (Operand.Expr) operand;
            if (e.args() != null) {
                for (final Operand arg : e.args()) {
                    applyParamsToVariables(arg, params);
                }
            }
        }
    }
}
