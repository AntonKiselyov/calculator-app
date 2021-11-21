package ru.akiselev.calculator.client;

import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;

public class CalculatorClientApp extends Application<CalculatorClientConfiguration> {

    public static void main(String[] args) throws Exception {
        new CalculatorClientApp().run(args);
    }

    @Override
    public void run(CalculatorClientConfiguration configuration, Environment environment) {
    }

    @Override
    public String getName() {
        return "Calculator client";
    }

    @Override
    public void initialize(final Bootstrap<CalculatorClientConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                        .enableAutoConfig(getClass().getPackage().getName())
                        .modules(new CalculatorClientModule())
                .build());
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }
}
