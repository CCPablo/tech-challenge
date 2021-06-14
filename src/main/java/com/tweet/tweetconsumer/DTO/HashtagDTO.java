package com.tweet.tweetconsumer.DTO;

public class HashtagDTO {

    private String text;

    public HashtagDTO(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
