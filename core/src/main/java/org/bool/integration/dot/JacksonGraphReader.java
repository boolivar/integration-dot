package org.bool.integration.dot;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bool.integration.dot.api.GraphReader;
import org.bool.integration.dot.api.model.IntegrationGraph;

import java.io.IOException;
import java.io.InputStream;

public class JacksonGraphReader implements GraphReader {

    private final ObjectMapper jsonMapper;

    public JacksonGraphReader() {
        this(defaultJsonMapper());
    }

    public JacksonGraphReader(ObjectMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    @Override
    public IntegrationGraph readGraph(InputStream in) throws IOException {
        return jsonMapper.readValue(in, IntegrationGraph.class);
    }

    private static ObjectMapper defaultJsonMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return mapper;
    }
}
