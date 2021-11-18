package ru.akiselev.calculator.service;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.dropwizard.setup.Environment;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.akiselev.calculator.service.service.ExpressionService;

@Slf4j
@AllArgsConstructor
public class CalculatorServiceModule extends AbstractModule {

    private final CalculatorServiceConfiguration configuration;
    private final Environment environment;

    @Override
    protected void configure() {
        bind(CalculatorServiceConfiguration.class).toInstance(configuration);
        bind(Environment.class).toInstance(environment);
    }

    @Provides
    @Singleton
    public ExpressionService expressionService() {
        return new ExpressionService();
    }
}
