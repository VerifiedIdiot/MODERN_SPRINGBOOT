package com.twolinecode.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
//    추가구현 필요
    // https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .build();
    }

}

