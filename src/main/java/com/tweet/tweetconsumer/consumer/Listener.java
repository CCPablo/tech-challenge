package com.tweet.tweetconsumer.consumer;

import com.tweet.tweetconsumer.service.TweetService;
import org.springframework.stereotype.Component;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

@Component
public class Listener implements StatusListener {

    private final Filterer filterer;

    private final TweetService tweetService;

    public Listener(Filterer filterer, TweetService tweetService) {
        this.filterer = filterer;
        this.tweetService = tweetService;
    }

    @Override
    public void onStatus(Status status) {
        if(!filterer.isTweetValid(status)) {
            return;
        }
        tweetService.addTweet(status);
    }

    @Override
    public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {

    }

    @Override
    public void onTrackLimitationNotice(int i) {

    }

    @Override
    public void onScrubGeo(long l, long l1) {
        System.out.println(l);
    }

    @Override
    public void onStallWarning(StallWarning stallWarning) {

    }

    @Override
    public void onException(Exception e) {
        e.printStackTrace();
    }
}
