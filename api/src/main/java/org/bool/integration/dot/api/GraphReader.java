package org.bool.integration.dot.api;

import org.bool.integration.dot.api.model.IntegrationGraph;

import java.io.IOException;
import java.io.InputStream;

public interface GraphReader {
    IntegrationGraph readGraph(InputStream in) throws IOException;
}
