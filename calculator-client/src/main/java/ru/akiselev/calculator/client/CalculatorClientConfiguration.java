package ru.akiselev.calculator.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CalculatorClientConfiguration extends Configuration {
    @Valid
    @NotNull
    private String calculatorServerHost;

    @JsonProperty("calculatorServerHost")
    public String getCalculatorServerHost() {
        return calculatorServerHost;
    }

    @JsonProperty("calculatorServerHost")
    public void setCalculatorServerHost(final String host) {
        this.calculatorServerHost = host;
    }
}

