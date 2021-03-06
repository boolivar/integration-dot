package org.bool.integration.dot.app.controller;

import org.bool.integration.dot.api.model.IntegrationGraph;
import org.bool.integration.dot.app.service.IntegrationGraphConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

@RestController("api")
public class DotController {

    @Autowired
    private IntegrationGraphConverter converter;

    @PostMapping(path = "convert", produces = MediaType.TEXT_PLAIN_VALUE)
    public void convert(@RequestBody IntegrationGraph graph, HttpServletResponse response) throws IOException {
        response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
        converter.convert(graph).write(response.getOutputStream());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    public String handleInvalidRequest(IllegalArgumentException e) {
        return e.getMessage();
    }
}
