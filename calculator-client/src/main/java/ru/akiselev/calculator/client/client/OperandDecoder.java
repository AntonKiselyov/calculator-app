package ru.akiselev.calculator.client.client;

import com.fasterxml.jackson.databind.JsonNode;
import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;
import ru.akiselev.calculator.client.client.dto.Operand;
import ru.akiselev.calculator.client.utils.JsonUtils;

import java.lang.reflect.Type;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static ru.akiselev.calculator.client.utils.DoubleUtils.isDouble;

public class OperandDecoder implements Decoder {

    private static final String VAL = "val";
    private static final String ARGS = "args";
    private static final String PLUS = "+";
    private static final String MINUS = "-";
    private static final String MUL = "*";
    private static final String DIV = "/";
    private static final String BRACKETS = "()";
    private static final String UNARY_MINUS = "--";

    @Override
    public Operand decode(Response response, Type type) throws FeignException {
        final JsonNode jsonNode = JsonUtils.toJsonNode(response);
        return parse(jsonNode);
    }

    private Operand parse(final JsonNode node) {
        if (node.has(VAL) && node.has(ARGS)) {
            final JsonNode val = node.get(VAL);
            final JsonNode args = node.get(ARGS);

            final List<Operand> exprs = newArrayList();
            args.elements().forEachRemaining(e -> {
                final Operand expr = parse(e);
                exprs.add(expr);
            });

            final String operator = val.asText();
            if (PLUS.equals(operator)) {

                return Operand.binary(PLUS, (a, b) -> a + b, exprs.toArray(new Operand[0]));

            } else if (MINUS.equals(operator)) {

                return Operand.binary(MINUS, (a, b) -> a - b, exprs.toArray(new Operand[0]));

            } else if (MUL.equals(operator)) {

                return Operand.binary(MUL, (a, b) -> a * b, exprs.toArray(new Operand[0]));

            } else if (DIV.equals(operator)) {

                return Operand.binary(DIV, (a, b) -> a / b, exprs.toArray(new Operand[0]));

            } else if (BRACKETS.equals(operator)) {

                return Operand.unary(BRACKETS, a -> a, exprs.toArray(new Operand[0]));

            } else if (UNARY_MINUS.equals(operator)) {

                return Operand.unary(UNARY_MINUS, a -> a--, exprs.toArray(new Operand[0]));

            }
            return Operand.empty();

        } else if (node.has(VAL)) {

            final JsonNode val = node.get(VAL);
            final String value = val.asText();

            if (isDouble(value)) {
                return Operand.number(Double.parseDouble(value));
            } else {
                return Operand.variable(value);
            }

        }
        return Operand.empty();
    }
}
