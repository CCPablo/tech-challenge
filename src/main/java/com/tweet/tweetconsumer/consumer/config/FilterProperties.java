package com.tweet.tweetconsumer.consumer.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FilterProperties {

    @Value("${tweet.languages: es, fr, it}")
    private String[] allowedLanguages;

    @Value("${tweet.min-followers: 1500}")
    private Integer minFollowers;

    public String[] getAllowedLanguages() {
        return allowedLanguages;
    }

    public Integer getMinFollowers() {
        return minFollowers;
    }
}
