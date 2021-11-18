package ru.akiselev.calculator.service.dto;

@ResourceRepresentation
class UnaryExpr extends BaseExpr {

    public UnaryExpr(final String val, final Operand arg) {
        super(val, arg);
    }

    @Override
    public String toString() {
        return "UnaryExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                '}';
    }
}
