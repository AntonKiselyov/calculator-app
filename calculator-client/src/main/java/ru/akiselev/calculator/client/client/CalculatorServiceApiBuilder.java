package ru.akiselev.calculator.client.client;

import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;

public class CalculatorServiceApiBuilder {

    static final String HOST = "http://localhost:8080";

    public static <T> T build(final Class<T> clazz) {
        return builder()
                .target(clazz, HOST);
    }

    private static Feign.Builder builder() {
        return Feign.builder()
                .encoder(new JacksonEncoder())
                .decoder(new JacksonDecoder())
                .decoder(new OperandDecoder())
                .contract(new JAXRSContract());
    }
}
