package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class Expr implements Operand {

    public static Expr binary(final String symbol, final Operand left, final Operand right) {
        return new BinaryExpr(symbol, left, right);
    }

    public static Expr unary(final String symbol, final Operand operand) {
        return new UnaryExpr(symbol, operand);
    }

    public static Operand number(final double val) {
        return new Number(val);
    }

    public static Operand variable(final String var) {
        return new Variable(var);
    }

    public static Operand empty() {
        return new Empty();
    }

    @JsonProperty("symbol")
    protected final String symbol;
    @JsonProperty("args")
    protected final List<Operand> args;

    @JsonCreator
    protected Expr(final String symbol,
                   final List<Operand> args) {
        this.symbol = symbol;
        this.args = args;
    }
}

@ResourceRepresentation
class BinaryExpr extends Expr {
    public BinaryExpr(final String symbol,
                      final Operand arg1,
                      final Operand arg2) {
        super(symbol, List.of(arg1, arg2));
    }
}

@ResourceRepresentation
class UnaryExpr extends Expr {
    public UnaryExpr(final String symbol, final Operand arg) {
        super(symbol, List.of(arg));
    }
}
