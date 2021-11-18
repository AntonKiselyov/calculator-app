package ru.akiselev.calculator.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@ResourceRepresentation
@Getter
public class ExpressionRequest {
    private final String expression;
    private final Map<String, Double> params;

    public ExpressionRequest(final @JsonProperty("expression") String expression,
                             final @JsonProperty("params") Map<String, Double> params) {
        this.expression = expression;
        this.params = params;
    }
}
