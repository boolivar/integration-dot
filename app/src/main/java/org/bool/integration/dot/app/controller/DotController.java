package org.bool.integration.dot.app.controller;

import org.bool.integration.dot.api.DotRenderer;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@RestController("api")
public class DotController {

    @Autowired
    private DotRenderer renderer;

    @PostMapping(path = "convert", produces = MediaType.TEXT_PLAIN_VALUE)
    public void convert(@RequestBody IntegrationGraph graph, HttpServletResponse response) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
        renderer.render(graph, response.getOutputStream());
    }
}
