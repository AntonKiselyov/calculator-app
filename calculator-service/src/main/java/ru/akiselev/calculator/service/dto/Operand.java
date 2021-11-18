package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface Operand {}

@ResourceRepresentation
class Number implements Operand {

    private final double val;

    @JsonCreator
    public Number(final @JsonProperty("val") double val) {
        this.val = val;
    }
}

@ResourceRepresentation
class Variable implements Operand {

    private final String val;

    @JsonCreator
    public Variable(final @JsonProperty("val") String val) {
        this.val = val;
    }
}

@ResourceRepresentation
class Empty extends Variable {

    public Empty() {
        super("");
    }
}

