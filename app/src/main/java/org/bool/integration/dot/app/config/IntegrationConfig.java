package org.bool.integration.dot.app.config;

import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.model.Graph;
import org.bool.integration.dot.GraphvizMapper;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.bool.integration.dot.app.service.GraphvizWriter;
import org.bool.integration.dot.app.service.IntegrationGraphConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.messaging.Message;

@EnableIntegration
@Configuration
public class IntegrationConfig {

    @Bean
    public IntegrationFlow integrationConverterFlow() {
        return IntegrationFlows.from("integrationConverterChannel")
                .enrichHeaders(enricher -> enricher.<IntegrationGraph>headerFunction("formatVersion", msg -> msg.getPayload().getContentDescriptor().getProviderFormatVersion()))
                .route(Message.class, msg -> msg.getHeaders().get("formatVersion"), router -> router.channelMapping("1.2", "convert12Channel"))
                .get()
                ;
    }

    @Bean
    public IntegrationFlow convert12Flow(GraphvizMapper mapper) {
        return IntegrationFlows.from("convert12Channel")
                .transform(mapper::mapIntegrationGraph)
                .channel("graphResultChannel")
                .get()
                ;
    }

    @Bean
    public IntegrationFlow resultFlow() {
        return IntegrationFlows.from("graphResultChannel")
                .enrichHeaders(enricher -> enricher.header("dotFormat", Format.DOT).header("dotEngine", Engine.DOT))
                .<Graph>handle((graph, headers) -> new GraphvizWriter(graph, headers.get("dotFormat", Format.class), headers.get("dotEngine", Engine.class)))
                .get()
                ;
    }
}
