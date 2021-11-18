package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class MulExpr extends BinaryExpr {
    public MulExpr(final Operand arg1, final Operand arg2) {
        super("*", arg1, arg2);
    }

    @Override
    public String toString() {
        return "MulExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
