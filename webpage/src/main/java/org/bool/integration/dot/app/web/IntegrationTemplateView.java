package org.bool.integration.dot.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.view.AbstractTemplateView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

public class IntegrationTemplateView extends AbstractTemplateView {

    private ObjectMapper jsonMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        this.jsonMapper = getApplicationContext().getBean(ObjectMapper.class);
    }

    @Override
    protected void renderMergedTemplateModel(Map<String, Object> model,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String template = readTemplate();
        writeOutput(response.getOutputStream(), template.replace("{integration-graph}", writeJson(model.get("integration-graph"))));
    }

    protected String readTemplate() throws IOException {
        byte[] buffer = new byte[1024];
        try (InputStream in = getClass().getResourceAsStream(getUrl()); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            int len;
            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
            return out.toString(UTF_8);
        }
    }

    protected String writeJson(Object value) {
        try {
            return jsonMapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new RuntimeException("Json serialization error", e);
        }
    }

    protected void writeOutput(OutputStream out, String text) throws IOException {
        try (OutputStreamWriter writer = new OutputStreamWriter(out, UTF_8)) {
            writer.write(text);
        }
    }
}
