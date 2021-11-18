package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

public interface Expr extends Operand {

    String getVal();

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

    static Expr empty() {
        return () -> "";
    }
}

abstract class BaseExpr implements Expr {
    @Getter
    protected final String val;
    @JsonProperty("args")
    protected final List<Operand> args;

    @JsonCreator
    protected BaseExpr(final @JsonProperty("val") String val,
                   final @JsonProperty("args") Operand ...args) {
        this.val = val;
        this.args = List.of(args);
    }
}

