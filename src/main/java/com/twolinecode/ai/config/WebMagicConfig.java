package com.twolinecode.ai.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.pipeline.Pipeline;

@Configuration
public class WebMagicConfig {

    @Value("${crawler.baseUrl}")
    private String baseUrl;

    // 크롤러의 사이트 설정
    @Bean
    public Site site() {
        return Site.me()
                .setRetryTimes(3)  // 실패 시 재시도 횟수
                .setSleepTime(1000)  // 요청 간 대기 시간
                .setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
    }

    // 페이지 프로세서 설정 (데이터 추출 로직 통합)
    @Bean
    public PageProcessor pageProcessor(Site site) {
        return new PageProcessor() {
            @Override
            public void process(Page page) {
                // 페이지에서 데이터를 추출하는 로직
                page.putField("title", page.getHtml().xpath("//title/text()").get());
                page.putField("links", page.getHtml().links().all());
            }

            @Override
            public Site getSite() {
                return site;  // 의존성 주입된 Site 객체 반환
            }
        };
    }

    // 파이프라인 설정 (콘솔에 출력)
    @Bean
    public Pipeline consolePipeline() {
        return new ConsolePipeline();  // 결과를 콘솔에 출력
    }

    // 크롤러 스파이더 설정
    @Bean
    public Spider spider(PageProcessor pageProcessor, Pipeline consolePipeline) {
        return Spider.create(pageProcessor)
                .addUrl(baseUrl)
                .addPipeline(consolePipeline) // 데이터를 콘솔에 출력
                .thread(5); // 쓰레드 수 설정
    }
}

