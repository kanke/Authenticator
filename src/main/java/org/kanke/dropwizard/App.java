package org.kanke.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.kanke.filter.AuthorizationFilter;
import org.kanke.resource.HealthCheckResource;
import org.kanke.resource.WelcomeResource;
import org.kanke.resource.AuthenticatorResource;

@Slf4j
public class App extends Application<Configuration> {

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration configuration, Environment environment) {

        //****** Dropwizard Health check ***********//
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck("config.yml");
        environment.healthChecks().register("Authenticator", healthCheck);
        final HealthCheckResource healthCheckResource = new HealthCheckResource();
        environment.jersey().register(healthCheckResource);

        //****** Dropwizard REST Endpoints ***********//
        log.info("Registering REST resources");
        final AuthenticatorResource authenticatorResource = new AuthenticatorResource();
        final WelcomeResource welcomeResource = new WelcomeResource();
        environment.jersey().register(authenticatorResource);
        environment.jersey().register(welcomeResource);

        //****** Dropwizard security - custom class ***********//
        final AuthorizationFilter authorizationFilter = new AuthorizationFilter();
        environment.jersey().register(authorizationFilter);

    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}