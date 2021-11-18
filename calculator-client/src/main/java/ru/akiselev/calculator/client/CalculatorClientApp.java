package ru.akiselev.calculator.client;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.akiselev.calculator.client.resource.ExpressionResource;

public class CalculatorClientApp extends Application<CalculatorClientConfiguration> {

    public static void main(String[] args) throws Exception {
        new CalculatorClientApp().run(args);
    }

    @Override
    public void run(CalculatorClientConfiguration configuration, Environment environment) {
        final Injector injector = Guice.createInjector(new CalculatorClientModule(configuration, environment));
        environment.jersey().register(injector.getInstance(ExpressionResource.class));
    }

    @Override
    public String getName() {
        return "Calculator client";
    }

    @Override
    public void initialize(final Bootstrap<CalculatorClientConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
