package org.bool.integration.dot;

import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import org.bool.integration.dot.api.DotRenderer;
import org.bool.integration.dot.api.model.IntegrationGraph;

import java.io.IOException;
import java.io.OutputStream;

public class GraphvizRenderer implements DotRenderer {

    private final GraphvizMapper mapper;

    private final Format format;

    private final Engine engine;

    public GraphvizRenderer(GraphvizMapper mapper) {
        this(mapper, Format.DOT, Engine.DOT);
    }

    public GraphvizRenderer(GraphvizMapper mapper, Format format, Engine engine) {
        this.mapper = mapper;
        this.format = format;
        this.engine = engine;
    }

    @Override
    public void render(IntegrationGraph integrationGraph, OutputStream out) throws IOException {
        Graph graph = mapper.mapIntegrationGraph(integrationGraph);
        Graphviz.fromGraph(graph).engine(engine).render(format).toOutputStream(out);
    }
}
