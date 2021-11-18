package ru.akiselev.calculator.service;

import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.akiselev.calculator.service.resource.ExpressionResource;

public class CalculatorServiceApp extends Application<CalculatorServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new CalculatorServiceApp().run(args);
    }

    @Override
    public void run(final CalculatorServiceConfiguration configuration, final Environment environment) {
        final Injector injector = Guice.createInjector(new CalculatorServiceModule(configuration, environment));
        environment.jersey().register(injector.getInstance(ExpressionResource.class));
    }

    @Override
    public String getName() {
        return "Calculator service";
    }

    @Override
    public void initialize(final Bootstrap<CalculatorServiceConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
        super.initialize(bootstrap);
    }
}
