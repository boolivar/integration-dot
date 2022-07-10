package org.bool.integration.dot.app.service;

import org.bool.integration.dot.api.model.IntegrationGraph;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "integrationConverterChannel")
public interface IntegrationGraphConverter {
    ResultWriter convert(IntegrationGraph graph);
}
