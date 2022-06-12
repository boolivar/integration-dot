package org.bool.integration.dot.api.model;

import java.util.List;
import java.util.Map;

public class IntegrationNode {

    private Integer nodeId;

    private String name;

    private String componentType;

    private String integrationPatternType;

    private String integrationPatternCategory;

    private String input;

    private String output;

    private List<String> routes;

    private List<HandlerNode> handlers;

    private Map<String, String> properties;

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getIntegrationPatternType() {
        return integrationPatternType;
    }

    public void setIntegrationPatternType(String integrationPatternType) {
        this.integrationPatternType = integrationPatternType;
    }

    public String getIntegrationPatternCategory() {
        return integrationPatternCategory;
    }

    public void setIntegrationPatternCategory(String integrationPatternCategory) {
        this.integrationPatternCategory = integrationPatternCategory;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public List<String> getRoutes() {
        return routes;
    }

    public void setRoutes(List<String> routes) {
        this.routes = routes;
    }

    public List<HandlerNode> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<HandlerNode> handlers) {
        this.handlers = handlers;
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "IntegrationNode [nodeId=" + nodeId + ", name=" + name + ", componentType=" + componentType
                + ", integrationPatternType=" + integrationPatternType + ", integrationPatternCategory="
                + integrationPatternCategory + ", input=" + input + ", output=" + output + ", routes=" + routes
                + ", handlers=" + handlers + ", properties=" + properties + "]";
    }
}
