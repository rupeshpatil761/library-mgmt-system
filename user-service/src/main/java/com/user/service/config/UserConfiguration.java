package com.user.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "test")
public class UserConfiguration {

    //@Value("${test.value}")  // this is not required / its optional
    private String value;

    private String localProperty;

    public void setValue(String value) {
        this.value = value;
    }

    public void setLocalProperty(String localProperty) {
        this.localProperty = localProperty;
    }

    public String getLocalProperty() {
        return localProperty;
    }
    public String getValue() {
        return value;
    }
}
