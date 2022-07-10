package org.bool.integration.dot.app.service;

import java.io.IOException;
import java.io.OutputStream;

public interface ResultWriter {
    void write(OutputStream out) throws IOException;
}
