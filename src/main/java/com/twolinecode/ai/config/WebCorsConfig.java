package com.twolinecode.ai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebCorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
//            https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/cors/CorsConfiguration.html
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000") // 클라이언트 도메인
                        .allowedMethods("GET", "POST", "PUT")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
//    서로 다른 REST API 체계가 필요하면 아래 방식으로 세부사항 조정
//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(List.of("*"));  // 허용할 출처
//        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));  // 허용할 메서드
//        configuration.setAllowedHeaders(List.of("*"));  // 모든 헤더 허용
//        configuration.setAllowCredentials(true);  // 자격 증명 허용
//        configuration.setMaxAge(3600L);  // Pre-flight 요청 캐시 시간 설정
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);  // 모든 경로에 적용
//        return source;
//    }
}
