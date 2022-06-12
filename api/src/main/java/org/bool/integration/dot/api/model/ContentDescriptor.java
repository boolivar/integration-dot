package org.bool.integration.dot.api.model;

public class ContentDescriptor {

    private String name;

    private String provider;

    private String providerVersion;

    private String providerFormatVersion;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderVersion() {
        return providerVersion;
    }

    public void setProviderVersion(String providerVersion) {
        this.providerVersion = providerVersion;
    }

    public String getProviderFormatVersion() {
        return providerFormatVersion;
    }

    public void setProviderFormatVersion(String providerFormatVersion) {
        this.providerFormatVersion = providerFormatVersion;
    }

    @Override
    public String toString() {
        return "ContentDescriptor [name=" + name + ", provider=" + provider + ", providerVersion=" + providerVersion
                + ", providerFormatVersion=" + providerFormatVersion + "]";
    }
}
