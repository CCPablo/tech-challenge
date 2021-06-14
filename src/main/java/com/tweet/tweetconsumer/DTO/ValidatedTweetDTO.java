package com.tweet.tweetconsumer.DTO;

import java.util.Map;
import java.util.Set;

public class ValidatedTweetDTO {

    private Long id;

    private String text;

    private Long publisherId;

    private Set<String> hashtags;

    private Map<String, Double> geolocation;

    public ValidatedTweetDTO(Long id, String text, Long publisherId, Set<String> hashtags, Map<String, Double> geolocation) {
        this.id = id;
        this.text = text;
        this.publisherId = publisherId;
        this.hashtags = hashtags;
        this.geolocation = geolocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public Set<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(Set<String> hashtags) {
        this.hashtags = hashtags;
    }

    public Map<String, Double> getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(Map<String, Double> geolocation) {
        this.geolocation = geolocation;
    }
}
