package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class UnaryMinusExpr extends UnaryExpr {
    public UnaryMinusExpr(final Operand arg) {
        super("--", arg);
    }

    @Override
    public String toString() {
        return "UnaryMinusExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
