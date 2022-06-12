package org.bool.integration.dot.api.model;

import java.util.List;

public class IntegrationGraph {

    private ContentDescriptor contentDescriptor;

    private List<IntegrationNode> nodes;

    private List<IntegrationLink> links;

    public ContentDescriptor getContentDescriptor() {
        return contentDescriptor;
    }

    public void setContentDescriptor(ContentDescriptor contentDescriptor) {
        this.contentDescriptor = contentDescriptor;
    }

    public List<IntegrationNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<IntegrationNode> nodes) {
        this.nodes = nodes;
    }

    public List<IntegrationLink> getLinks() {
        return links;
    }

    public void setLinks(List<IntegrationLink> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        return "IntegrationGraph [contentDescriptor=" + contentDescriptor + ", nodes=" + nodes + ", links=" + links
                + "]";
    }
}
