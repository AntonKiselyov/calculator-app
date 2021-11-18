package ru.akiselev.calculator.client;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.setup.Environment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.akiselev.calculator.client.client.CalculatorServiceApiBuilder;
import ru.akiselev.calculator.client.client.ExpressionClient;
import ru.akiselev.calculator.client.service.ExpressionService;

@Slf4j
@AllArgsConstructor
public class CalculatorClientModule extends AbstractModule {

    private final CalculatorClientConfiguration configuration;
    private final Environment environment;

    @Override
    protected void configure() {
        bind(CalculatorClientConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);
    }

    @Provides
    @Singleton
    public ExpressionClient expressionClient() {
        return CalculatorServiceApiBuilder.build(ExpressionClient.class);
    }

    @Provides
    @Singleton
    public ExpressionService expressionService(final ExpressionClient expressionClient) {
        return new ExpressionService(expressionClient);
    }
}