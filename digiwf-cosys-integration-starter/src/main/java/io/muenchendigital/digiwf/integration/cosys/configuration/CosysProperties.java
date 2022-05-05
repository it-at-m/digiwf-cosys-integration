package io.muenchendigital.digiwf.integration.cosys.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

@Getter
@Setter
@ConfigurationProperties(prefix = "io.muenchendigital.digiwf.cosys")
public class CosysProperties {

    @NestedConfigurationProperty
    private MergeProperties merge;

    private String url;

    private String ssoTokenRequestUrl;

    private String ssoTokenClientId;

    private String ssoTokenClientSecret;

    @Getter
    @Setter
    public static class MergeProperties {

        private String datafile;

        private String inputLanguage;

        private String outputLanguage;

        private String keepFields;

    }
}
