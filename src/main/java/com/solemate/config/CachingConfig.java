package com.solemate.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Slf4j
@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    @CacheEvict(allEntries = true, value = "airQuality")
    @Scheduled(cron ="1 * * * * *")
    public void airQualityCacheEvict() {
        log.info("airQuality Cache Evict");
    }

    @CacheEvict(allEntries = true, value = "weather")
    @Scheduled(cron ="0 0/30 0 * * *")
    public void weatherCacheEvict() {
        log.info("weather Cache Evict");
    }
}
