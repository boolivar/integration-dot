package org.bool.integration.dot.api;

import org.bool.integration.dot.api.model.IntegrationGraph;

import java.io.IOException;
import java.io.OutputStream;

public interface DotRenderer {
    void render(IntegrationGraph graph, OutputStream out) throws IOException;
}
