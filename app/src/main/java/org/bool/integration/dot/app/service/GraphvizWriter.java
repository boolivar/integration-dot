package org.bool.integration.dot.app.service;

import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.IOException;
import java.io.OutputStream;

public class GraphvizWriter implements ResultWriter {

    private final Graph graph;

    private final Format format;

    private final Engine engine;

    public GraphvizWriter(Graph graph, Format format, Engine engine) {
        this.graph = graph;
        this.format = format;
        this.engine = engine;
    }

    @Override
    public void write(OutputStream out) throws IOException {
        Graphviz.fromGraph(graph).engine(engine).render(format).toOutputStream(out);
    }
}
