package org.kanke.dropwizard;

import com.codahale.metrics.health.HealthCheck;


public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }
    @Override
    protected Result check() {
        final String saying = String.format(template, "AuthenticatorApplication");
        if (!saying.contains("AuthenticatorApplication")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}