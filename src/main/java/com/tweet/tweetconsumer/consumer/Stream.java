package com.tweet.tweetconsumer.consumer;

import com.tweet.tweetconsumer.consumer.config.FilterProperties;
import org.springframework.stereotype.Service;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;

import javax.annotation.PostConstruct;

@Service
public class Stream {

    private final Listener listener;

    private final FilterProperties props;

    private final TwitterStreamFactory twitterStreamFactory = new TwitterStreamFactory();

    public Stream(Listener listener, FilterProperties props) {
        this.listener = listener;
        this.props = props;
    }

    @PostConstruct
    void init() {
        getTwitterStream().sample();
    }

    private TwitterStream getTwitterStream() {
        return twitterStreamFactory.getInstance().addListener(listener);
    }

    /*
    private void sampleMultipleStreams() {
        Arrays.stream(props.getAllowedLanguages()).forEach(this::sampleLang);
    }

    private void sampleLang(String lang) {
        getTwitterStream().sample(lang);
    }

    private void filterByLanguages() {
        FilterQuery filterQuery = new FilterQuery();
        String[] keywords = {"a"};
        filterQuery.track(keywords).language(props.getAllowedLanguages());
        getTwitterStream().filter(filterQuery);
    }
    */
}
