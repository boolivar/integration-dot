package org.bool.integration.dot.app.config;

import org.bool.integration.dot.GraphvizMapper;
import org.bool.integration.dot.GraphvizRenderer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GraphvizConfig {

    @Bean
    public GraphvizRenderer graphvizRenderer(GraphvizMapper mapper) {
        return new GraphvizRenderer(mapper);
    }

    @Bean
    public GraphvizMapper graphvizMapper() {
        return new GraphvizMapper();
    }
}
