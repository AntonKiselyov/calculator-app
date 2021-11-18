package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;

@ResourceRepresentation
class BinaryExpr extends BaseExpr {

    @JsonCreator
    public BinaryExpr(final String val,
                      final Operand arg1,
                      final Operand arg2) {
        super(val, arg1, arg2);
    }

    @Override
    public String toString() {
        return "BinaryExpr{" +
                "val='" + val + '\'' +
                ", args=" + args +
                '}';
    }
}
