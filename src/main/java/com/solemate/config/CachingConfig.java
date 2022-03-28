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
    @Scheduled(cron ="0 0 0/1 * * *")
    public void reportCacheEvict() {
        System.out.println("Flush Cache " +new Date());
    }
}
