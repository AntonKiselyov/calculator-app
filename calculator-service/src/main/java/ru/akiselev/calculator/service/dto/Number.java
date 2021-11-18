package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@ResourceRepresentation
class Number implements Operand {

    private final double val;

    @JsonCreator
    public Number(final @JsonProperty("val") double val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Number{" +
                "val=" + val +
                '}';
    }
}
