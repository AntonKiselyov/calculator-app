package ru.akiselev.calculator.client.client.dto;

import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.String.format;

public interface Operand {

    double evaluate();

    static Operand binary(final String symbol, final BinaryOperator<Double> operator, final Operand ...args) {
        return new BinaryExpr(symbol, operator, args);
    }

    static Operand unary(final String symbol, final UnaryOperator<Double> operator, final Operand ...args) {
        return new UnaryExpr(symbol, operator, args);
    }

    static Operand empty() {
        return () -> 0;
    }

    static Operand number(final double val) {
        return new Number(val);
    }

    static Operand variable(final String val) {
        return new Variable(val);
    }

    class Variable implements Operand {

        @Getter
        private final String val;
        private Double substitute;

        public Variable(final String val) {
            this.val = val;
        }

        @Override
        public double evaluate() {
            Preconditions.checkNotNull(substitute, "Substitute can't be null.");
            return substitute;
        }

        public void applySubstitute(final double param) {
            this.substitute = param;
        }

        @Override
        public String toString() {
            return "Variable{" +
                    "val='" + val + '\'' +
                    ", param=" + substitute +
                    '}';
        }
    }

    class Number implements Operand {

        private final double val;

        public Number(final double val) {
            this.val = val;
        }

        @Override
        public double evaluate() {
            return val;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "val=" + val +
                    '}';
        }
    }

    interface Expr extends Operand {

        String symbol();

        Operand[] args();

        int numberOfArgs();
    }

    @AllArgsConstructor
    class BinaryExpr implements Expr {

        private final String symbol;
        private final BinaryOperator<Double> operator;
        private final Operand[] args;

        @Override
        public String symbol() {
            return symbol;
        }

        @Override
        public Operand[] args() {
            return args;
        }

        @Override
        public double evaluate() {
            Preconditions.checkNotNull(args, "Args cannot be null!");
            Preconditions.checkState(numberOfArgs() == args.length, "Number of arguments should be equal to " + numberOfArgs() + ".");
            return operator.apply(args[0].evaluate(), args[1].evaluate());
        }

        @Override
        public int numberOfArgs() {
            return 2;
        }

        @Override
        public String toString() {
            return "BinaryExpr{" +
                    "val='" + symbol + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }

    @AllArgsConstructor
    class UnaryExpr implements Expr {

        private final String symbol;
        private final UnaryOperator<Double> operator;
        private final Operand[] args;

        @Override
        public String symbol() {
            return symbol;
        }

        @Override
        public Operand[] args() {
            return args;
        }

        @Override
        public double evaluate() {
            Preconditions.checkNotNull(args, "Args cannot be null!");
            Preconditions.checkState(numberOfArgs() == args.length, format("Number of arguments should be equal to %d.", numberOfArgs()));
            return operator.apply(args[0].evaluate());
        }

        @Override
        public int numberOfArgs() {
            return 1;
        }

        @Override
        public String toString() {
            return "UnaryExpr{" +
                    "val='" + symbol + '\'' +
                    ", args=" + Arrays.toString(args) +
                    '}';
        }
    }
}