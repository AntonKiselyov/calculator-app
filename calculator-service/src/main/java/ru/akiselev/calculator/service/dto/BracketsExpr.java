package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class BracketsExpr extends UnaryExpr {

    public BracketsExpr(final Operand arg) {
        super("()", arg);
    }

    @Override
    public String toString() {
        return "BracketsExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
