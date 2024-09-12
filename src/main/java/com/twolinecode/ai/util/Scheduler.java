package com.twolinecode.ai.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;



@Component
@RequiredArgsConstructor
@Log4j2
public class Scheduler {

    private final CacheManager cacheManager;



    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void executeWeatherTasks() throws JsonProcessingException {
        try {
            log.info("날씨 스케쥴러 시작 ! ! ! !");

            cacheManager.getCache("weather").clear();
            log.info("날씨 정보 insert 작동 ! ! ! ! !");
        } catch (ResourceAccessException e) {
            // 로그에 예외 정보 기록
            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }
    // 어플리케이션 최초실행시 스케쥴러를 한번 시작
//    @PostConstruct
//    public void init() {
//        // 서비스 시작 시 한 번 실행할 작업
//        try {
//            executeWeatherTasks();
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//
//            e.printStackTrace();
//        }
//    }
}
