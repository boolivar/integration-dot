package org.bool.integration.dot.app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.graph.Graph;
import org.springframework.integration.graph.IntegrationGraphServer;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IntegrationGraphController {

    @Autowired
    private IntegrationGraphServer server;

    @ModelAttribute("integration-graph")
    @GetMapping("integrationgraph")
    public Graph integrationGraph() {
        return server.getGraph();
    }
}