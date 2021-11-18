package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public interface Expr extends Operand {

    static Expr minus(final Operand leftOp, final Operand rightOp) {
        return new MinusExpr(leftOp, rightOp);
    }

    static Expr plus(final Operand leftOp, final Operand rightOp) {
        return new PlusExpr(leftOp, rightOp);
    }

    static Expr multiply(final Operand leftOp, final Operand rightOp) {
        return new MulExpr(leftOp, rightOp);
    }

    static Expr division(final Operand leftOp, final Operand rightOp) {
        return new DivExpr(leftOp, rightOp);
    }

    static Expr brackets(final Operand operand) {
        return new BracketsExpr(operand);
    }

    static Expr unaryMinus(final Operand operand) {
        return new UnaryMinusExpr(operand);
    }

    static Operand number(final double parseDouble) {
        return new Number(parseDouble);
    }

    static Operand variable(final String val) {
        return new Variable(val);
    }

    static Operand empty() {
        return new Empty();
    }
}

@ResourceRepresentation
class BinaryExpr implements Expr {
    @JsonProperty("val")
    protected final String val;
    @JsonProperty("args")
    protected final List<Operand> args;

    @JsonCreator
    public BinaryExpr(final String val,
                      final Operand arg1,
                      final Operand arg2) {
        this.val = val;
        this.args = List.of(arg1, arg2);
    }
}

@ResourceRepresentation
class PlusExpr extends BinaryExpr {
    public PlusExpr(final Operand arg1, final Operand arg2) {
        super("+", arg1, arg2);
    }
}

@ResourceRepresentation
class MinusExpr extends BinaryExpr {
    public MinusExpr(final Operand arg1, final Operand arg2) {
        super("-", arg1, arg2);
    }
}

@ResourceRepresentation
class DivExpr extends BinaryExpr {
    public DivExpr(final Operand arg1, final Operand arg2) {
        super("/", arg1, arg2);
    }
}

@ResourceRepresentation
class MulExpr extends BinaryExpr {
    public MulExpr(final Operand arg1, final Operand arg2) {
        super("*", arg1, arg2);
    }
}

@ResourceRepresentation
class UnaryExpr implements Expr {
    @JsonProperty("val")
    protected final String val;
    @JsonProperty("args")
    protected final List<Operand> args;

    public UnaryExpr(final String val, final Operand arg) {
        this.val = val;
        this.args = List.of(arg);
    }
}

@ResourceRepresentation
class BracketsExpr extends UnaryExpr {
    public BracketsExpr(final Operand arg) {
        super("()", arg);
    }
}

@ResourceRepresentation
class UnaryMinusExpr extends UnaryExpr {
    public UnaryMinusExpr(final Operand arg) {
        super("--", arg);
    }
}
