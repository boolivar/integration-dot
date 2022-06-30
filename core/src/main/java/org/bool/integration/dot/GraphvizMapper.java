package org.bool.integration.dot;

import guru.nidi.graphviz.attribute.Shape;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import org.bool.integration.dot.api.model.IntegrationGraph;
import org.bool.integration.dot.api.model.IntegrationLink;
import org.bool.integration.dot.api.model.IntegrationNode;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static guru.nidi.graphviz.model.Factory.*;

public class GraphvizMapper {

    public Graph mapIntegrationGraph(IntegrationGraph graph) {
        return graph(Objects.toString(graph.getContentDescriptor().getName(), ""))
                .directed()
                .with(mapNodes(graph.getNodes()))
                .with(collect(graph.getLinks(), this::mapLink))
                ;
    }

    private List<LinkSource> mapNodes(List<IntegrationNode> nodes) {
        Map<Boolean, List<IntegrationNode>> gateways = nodes.stream()
                .collect(Collectors.partitioningBy(n -> endsWith(n.getComponentType(), "gateway")));
        return mapNodes(gateways.get(false), gateways.get(true).stream()
                .collect(Collectors.groupingBy(g -> g.getName().replaceAll("\\..*", ""))));
    }

    private List<LinkSource> mapNodes(List<IntegrationNode> plainNodes, Map<String, List<IntegrationNode>> gateways) {
        return Stream.concat(plainNodes.stream().map(this::mapNode), gateways.entrySet().stream()
                        .map(e -> graph(e.getKey()).cluster().graphAttr().with("label", e.getKey()).with(collect(e.getValue(), this::mapNode))))
                .collect(Collectors.toList());

    }

    private <T> List<LinkSource> collect(List<T> elements, Function<T, LinkSource> mapper) {
        return elements.stream().map(mapper).collect(Collectors.toList());
    }

    private LinkSource mapNode(IntegrationNode node) {
        return node(node.getNodeId().toString())
                .with("label", formatName(node))
                .with(mapNodeShape(node.getComponentType()))
                ;
    }

    private String formatName(IntegrationNode node) {
        String name = formatName(node.getName());
        if (node.getHandlers() == null || node.getHandlers().isEmpty()) {
            return name;
        }
        return Stream.concat(Stream.of(name), node.getHandlers().stream().map(h -> "{" + h.getType() + "|" + formatName(h.getName()) + "}"))
                .collect(Collectors.joining("|", "{", "}"));
    }

    private String formatName(String name) {
        return name != null ? name.replaceAll("\\(.+", "").replaceAll(".+\\.", "") : null;
    }

    private Shape mapNodeShape(String componentType) {
        if (endsWith(componentType, "channel")) {
            return Shape.BOX;
        }
        if (endsWith(componentType, "chain")) {
            return Shape.RECORD;
        }
        if (endsWith(componentType, "bridge")) {
            return Shape.PLAIN;
        }
        if (endsWith(componentType, "adapter")) {
            return Shape.R_ARROW;
        }
        return Shape.ELLIPSE;
    }

    private LinkSource mapLink(IntegrationLink link) {
        return node(link.getFrom().toString())
                .link(link.getTo().toString())
                ;
    }

    private boolean endsWith(String string, String suffix) {
        return string != null && string.toLowerCase().endsWith(suffix);
    }
}
