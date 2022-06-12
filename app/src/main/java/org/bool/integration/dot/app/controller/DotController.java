package org.bool.integration.dot.app.controller;

import org.bool.integration.dot.api.DotRenderer;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStream;

@RestController("api")
public class DotController {

    @Autowired
    private DotRenderer renderer;

    @PostMapping(path = "convert", produces = MediaType.TEXT_PLAIN_VALUE)
    public void convert(@RequestBody IntegrationGraph graph, OutputStream out) throws IOException {
        renderer.render(graph, out);
    }
}
