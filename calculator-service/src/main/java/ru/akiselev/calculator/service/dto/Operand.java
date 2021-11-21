package ru.akiselev.calculator.service.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public interface Operand {}

@ResourceRepresentation
class Number implements Operand {
    @JsonProperty("val")
    private final double val;

    @JsonCreator
    public Number(final double val) {
        this.val = val;
    }
}

@ResourceRepresentation
class Variable implements Operand {
    @JsonProperty("var")
    private final String var;

    @JsonCreator
    public Variable(final String var) {
        this.var = var;
    }
}

@ResourceRepresentation
class Empty extends Variable {
    public Empty() {
        super("");
    }
}

