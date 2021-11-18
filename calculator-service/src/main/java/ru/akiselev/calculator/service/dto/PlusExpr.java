package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class PlusExpr extends BinaryExpr {
    public PlusExpr(final Operand arg1, final Operand arg2) {
        super("+", arg1, arg2);
    }

    @Override
    public String toString() {
        return "PlusExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
