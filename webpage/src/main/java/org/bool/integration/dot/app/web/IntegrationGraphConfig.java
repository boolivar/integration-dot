package org.bool.integration.dot.app.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationGraphConfig {

    @Bean
    public IntegrationViewResolver integrationViewResolver() {
        return new IntegrationViewResolver();
    }
}
