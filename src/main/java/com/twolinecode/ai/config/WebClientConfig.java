package com.twolinecode.ai.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@RequiredArgsConstructor
public class WebClientConfig {



    private final WebClient.Builder webClientBuilder;
//    추가구현 필요 https://docs.spring.io/spring-framework/reference/integration/rest-clients.html
    @Bean
    public WebClient webClient() {
        // 기본 URL 설정 없이 빌더를 사용하여 WebClient 인스턴스를 생성합니다.
        return webClientBuilder.build();
    }

//    @Bean
//    public WebClient webClient() {
//        return webClientBuilder
//                .baseUrl("https://api.example.com")  // 기본 URL 추가
//                .defaultHeader("Content-Type", "application/json")  // 기본 헤더 설정
//                .build();
//    }
}
