package org.bool.integration.dot;

import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.*;

class GraphvizRendererTest {

    private final GraphvizMapper mapper = mock(GraphvizMapper.class);

    private final GraphvizRenderer renderer = new GraphvizRenderer(mapper, Format.DOT, Engine.DOT);

    @Test
    void testRender() throws IOException {
        given(mapper.mapIntegrationGraph(any()))
                .willReturn(graph("test").directed().with(node("X")));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        renderer.render(new IntegrationGraph(), out);

        assertThat(out.toString())
                .isEqualTo("digraph \"test\" {\n\"X\"\n}")
                ;
    }
}
