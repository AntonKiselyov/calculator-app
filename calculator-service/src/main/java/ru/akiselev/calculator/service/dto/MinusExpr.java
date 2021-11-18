package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class MinusExpr extends BinaryExpr {
    public MinusExpr(final Operand arg1, final Operand arg2) {
        super("-", arg1, arg2);
    }

    @Override
    public String toString() {
        return "MinusExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
