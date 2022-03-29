package com.solemate.config;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

@Configuration
@EnableCaching
@EnableScheduling
public class CachingConfig {

    @CacheEvict(allEntries = true, value = "airQuality")
    @Scheduled(cron ="0 16 * * * *")
    public void airQualityCacheEvict() {
        System.out.println("airQuality Cache Evict :: " + new Date());
    }

    @CacheEvict(allEntries = true, value = "weather")
    @Scheduled(cron ="0 0/30 0 * * *")
    public void weatherCacheEvict() {
        System.out.println("weather Cache Evict  :: " +new Date());
    }
}
