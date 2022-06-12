package org.bool.integration.dot;

import org.bool.integration.dot.api.model.IntegrationGraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

class JacksonGraphReaderTest {

    private final JacksonGraphReader reader = new JacksonGraphReader();

    @Test
    void testReadV11() throws IOException {
        IntegrationGraph graph = readGraph("/analog-1.1.json");
        assertThat(graph.getContentDescriptor())
                .hasFieldOrPropertyWithValue("providerVersion", "5.2.4.RELEASE")
                .hasFieldOrPropertyWithValue("providerFormatVersion", "1.1")
                .hasFieldOrPropertyWithValue("provider", "spring-integration")
                .hasFieldOrPropertyWithValue("name", "analog")
                ;
        assertThat(graph.getNodes())
                .hasSize(32)
                ;
        assertThat(graph.getLinks())
                .hasSize(21)
                ;
    }

    @Test
    void testReadV12() throws IOException {
        IntegrationGraph graph = readGraph("/spring-1.2.json");
        assertThat(graph.getContentDescriptor())
                .hasFieldOrPropertyWithValue("providerVersion", "5.5.12")
                .hasFieldOrPropertyWithValue("providerFormatVersion", "1.2")
                .hasFieldOrPropertyWithValue("provider", "spring-integration")
                .hasFieldOrPropertyWithValue("name", "myAppName:1.0")
                ;
        assertThat(graph.getNodes())
                .hasSize(3)
                ;
        assertThat(graph.getLinks())
                .hasSize(1)
                ;
    }

    private IntegrationGraph readGraph(String resource) throws IOException {
        try (InputStream in = getClass().getResourceAsStream(resource)) {
            return reader.readGraph(in);
        }
    }
}
