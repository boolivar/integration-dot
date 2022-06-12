package org.bool.integration.dot;

import guru.nidi.graphviz.model.MutableGraph;
import org.bool.integration.dot.api.model.ContentDescriptor;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.bool.integration.dot.api.model.IntegrationLink;
import org.bool.integration.dot.api.model.IntegrationNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(Lifecycle.PER_CLASS)
class GraphvizMapperTest {

    private final GraphvizMapper mapper = new GraphvizMapper();

    @Test
    void testEmpty() {
        IntegrationGraph integrationGraph = new IntegrationGraph();
        integrationGraph.setContentDescriptor(new ContentDescriptor());
        integrationGraph.setLinks(Arrays.asList());
        integrationGraph.setNodes(Arrays.asList());

        MutableGraph graph = mapper.mapIntegrationGraph(integrationGraph).toMutable();

        assertThat(graph.name().toString())
                .isNull();
        assertThat(graph.links())
                .isEmpty();
        assertThat(graph.rootNodes())
                .isEmpty();
    }

    @Test
    void testMapping() {
        IntegrationGraph integrationGraph = new IntegrationGraph();

        ContentDescriptor descriptor = new ContentDescriptor();
        descriptor.setName("TEST");

        integrationGraph.setContentDescriptor(descriptor);

        IntegrationLink link = new IntegrationLink();
        link.setFrom(1);
        link.setTo(1);
        link.setType("input");

        integrationGraph.setLinks(Arrays.asList(link));

        IntegrationNode node1 = new IntegrationNode();
        node1.setNodeId(1);
        node1.setName("node1");

        IntegrationNode node2 = new IntegrationNode();
        node2.setNodeId(2);
        node2.setName("node2");

        integrationGraph.setNodes(Arrays.asList(node1, node2));

        MutableGraph graph = mapper.mapIntegrationGraph(integrationGraph).toMutable();

        assertThat(graph.name().toString())
                .isEqualTo("TEST");
        assertThat(graph.rootNodes())
                .hasSize(3);
    }
}
