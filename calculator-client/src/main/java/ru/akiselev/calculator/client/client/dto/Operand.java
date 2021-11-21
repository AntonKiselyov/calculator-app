package ru.akiselev.calculator.client.client.dto;

import com.google.common.base.Preconditions;
import lombok.Getter;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import static java.lang.String.format;

public interface Operand {

    double evaluate();

    static Operand binary(final String symbol, final BinaryOperator<Double> operator, final List<Operand> args) {
        return new BinaryExpr(symbol, operator, args);
    }

    static Operand unary(final String symbol, final UnaryOperator<Double> operator, final List<Operand> args) {
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
        private final String var;

        public Variable(final String var) {
            this.var = var;
        }

        @Override
        public double evaluate() {
            throw new RuntimeException("Variable can't be evaluated.");
        }

        @Override
        public String toString() {
            return "Variable{" +
                    "var='" + var + '\'' +
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

    abstract class Expr implements Operand {

        protected final String symbol;
        protected final List<Operand> args;

        protected Expr(final String symbol, final List<Operand> args) {
            this.symbol = symbol;
            this.args = args;
        }

        public abstract String symbol();

        public abstract List<Operand> args();

        protected abstract int numberOfArgs();

    }

    class BinaryExpr extends Expr {

        @Getter
        private final BinaryOperator<Double> operator;

        public BinaryExpr(final String symbol,
                          final BinaryOperator<Double> operator,
                          final List<Operand> args) {
            super(symbol, args);
            this.operator = operator;
        }

        @Override
        public String symbol() {
            return symbol;
        }

        @Override
        public List<Operand> args() {
            return args;
        }

        @Override
        public double evaluate() {
            Preconditions.checkNotNull(args, "Args cannot be null!");
            Preconditions.checkState(numberOfArgs() == args.size(), "Number of arguments should be equal to " + numberOfArgs() + ".");
            return operator.apply(args.get(0).evaluate(), args.get(1).evaluate());
        }

        @Override
        public int numberOfArgs() {
            return 2;
        }

        @Override
        public String toString() {
            return "BinaryExpr{" +
                    "symbol='" + symbol + '\'' +
                    ", args=" + args +
                    '}';
        }
    }

    class UnaryExpr extends Expr {

        @Getter
        private final UnaryOperator<Double> operator;

        public UnaryExpr(final String symbol,
                         final UnaryOperator<Double> operator,
                         final List<Operand> args) {
            super(symbol, args);
            this.operator = operator;
        }

        @Override
        public String symbol() {
            return symbol;
        }

        @Override
        public List<Operand> args() {
            return args;
        }

        @Override
        public double evaluate() {
            Preconditions.checkNotNull(args, "Args cannot be null!");
            Preconditions.checkState(numberOfArgs() == args.size(), format("Number of arguments should be equal to %d.", numberOfArgs()));
            return operator.apply(args.get(0).evaluate());
        }

        @Override
        public int numberOfArgs() {
            return 1;
        }

        @Override
        public String toString() {
            return "UnaryExpr{" +
                    "symbol='" + symbol + '\'' +
                    ", args=" + args +
                    '}';
        }
    }
}