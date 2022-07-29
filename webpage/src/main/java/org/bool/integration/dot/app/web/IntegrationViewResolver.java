package org.bool.integration.dot.app.web;

import org.springframework.web.servlet.view.AbstractTemplateViewResolver;

public class IntegrationViewResolver extends AbstractTemplateViewResolver {

    public IntegrationViewResolver() {
        this("integrationgraph", IntegrationTemplateView.class, "/", ".html");
    }

    public IntegrationViewResolver(String viewName, Class<?> viewClass, String prefix, String suffix) {
        this.setViewNames(viewName);
        this.setViewClass(viewClass);
        this.setPrefix(prefix);
        this.setSuffix(suffix);
    }
}
