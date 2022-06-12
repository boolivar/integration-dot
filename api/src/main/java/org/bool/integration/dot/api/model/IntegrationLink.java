package org.bool.integration.dot.api.model;

public class IntegrationLink {

    private Integer from;

    private Integer to;

    private String type;

    public Integer getFrom() {
        return from;
    }

    public void setFrom(Integer from) {
        this.from = from;
    }

    public Integer getTo() {
        return to;
    }

    public void setTo(Integer to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Link [from=" + from + ", to=" + to + ", type=" + type + "]";
    }
}
