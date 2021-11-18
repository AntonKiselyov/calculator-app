package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

@ResourceRepresentation
class Variable implements Operand {

    private final String val;

    @JsonCreator
    public Variable(final @JsonProperty("val") String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return "Variable{" +
                "val='" + val + '\'' +
                "} ";
    }
}
