package com.tweet.tweetconsumer.service;

import com.tweet.tweetconsumer.DTO.TweetDTO;
import com.tweet.tweetconsumer.DTO.ValidatedTweetDTO;
import com.tweet.tweetconsumer.repository.TweetRepository;
import com.tweet.tweetconsumer.repository.entity.TweetEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import twitter4j.Status;

import java.util.List;
import java.util.Map;

import static com.tweet.tweetconsumer.mapper.PageMapper.getPageResponse;
import static com.tweet.tweetconsumer.mapper.TweetMapper.*;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;

    public TweetService(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    public TweetDTO addTweet(Status status) {
        TweetEntity tweetEntity = status4jToTweetEntity(status);
        TweetEntity saved = tweetRepository.save(tweetEntity);
        return tweetEntityToTweetDTO(saved);
    }

    public Map<String, Object> getPagedTweets(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TweetEntity> tweetsPage = tweetRepository.findAll(pageable);
        return getPageResponse(tweetsPage);
    }

    public List<ValidatedTweetDTO> getValidatedTweets() {
        List<TweetEntity> validatedTweetEntities = tweetRepository.findAllValidated();
        return tweetEntitiesToValidatedTweetDTOs(validatedTweetEntities);
    }

    public List<ValidatedTweetDTO> getValidatedTweetsByUserId(long userId) {
        List<TweetEntity> validatedTweetEntities = tweetRepository.findAllValidatedByUserId(userId);
        return tweetEntitiesToValidatedTweetDTOs(validatedTweetEntities);
    }

    public ValidatedTweetDTO validateTweet(long tweetId) {
        TweetEntity tweet = tweetRepository.getById(tweetId);
        tweet.setValidated(true);
        TweetEntity saved = tweetRepository.save(tweet);
        return tweetEntityToValidatedTweetDTO(saved);
    }
}
