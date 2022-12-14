package br.com.uan.zip.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.uan.zip.ZipTemplate;

@Configuration
@ConditionalOnMissingBean(ZipTemplate.class)
@ConfigurationProperties(prefix = "zip")
public class ZipAutoConfiguration
{
    @Bean
    ZipTemplate zipTemplate() throws IOException {
        return new ZipTemplate();
    }
}