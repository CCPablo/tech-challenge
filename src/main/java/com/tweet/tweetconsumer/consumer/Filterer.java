package com.tweet.tweetconsumer.consumer;

import com.tweet.tweetconsumer.consumer.config.FilterProperties;
import org.springframework.stereotype.Component;
import twitter4j.Status;
import twitter4j.User;

import java.util.Arrays;

@Component
public class Filterer {

    private final FilterProperties props;

    public Filterer(FilterProperties filterProperties) {
        this.props = filterProperties;
    }

    public boolean isTweetValid(Status status) {
        return isLanguageValid(status.getLang()) && isUserValid(status.getUser());
    }

    private boolean isLanguageValid(String lang) {
        return Arrays.asList(props.getAllowedLanguages()).contains(lang);
    }

    private boolean isUserValid(User user) {
        return user.getFollowersCount() >= props.getMinFollowers();
    }
}
