package org.bool.integration.dot.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.graph.IntegrationGraphServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

@RestController
public class IntegrationGraphController {

    @Autowired
    private IntegrationGraphServer server;

    @Autowired
    private ObjectMapper jsonMapper;

    @GetMapping("integrationgraph")
    public void getIntegrationGraph(OutputStream out) throws IOException {
        String graph = jsonMapper.writeValueAsString(server.getGraph());
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/integrationgraph.html")));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {
            reader.lines().map(line -> line.replace("{integration-graph}", graph)).forEach(line -> write(line, writer));
        }
    }

    private void write(String line, BufferedWriter writer) {
        try {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }
    }
}