package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class DivExpr extends BinaryExpr {
    public DivExpr(final Operand arg1, final Operand arg2) {
        super("/", arg1, arg2);
    }

    @Override
    public String toString() {
        return "DivExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                "} ";
    }
}
