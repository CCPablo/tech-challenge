package com.tweet.tweetconsumer.service;

import com.tweet.tweetconsumer.DTO.TweetDTO;
import com.tweet.tweetconsumer.repository.TweetRepository;
import com.tweet.tweetconsumer.repository.entity.TweetEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import twitter4j.Status;

import static com.tweet.tweetconsumer.mapper.TweetMapper.status4jToTweetEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static provider.Statuses4j.get4jStatus;

@ExtendWith(MockitoExtension.class)
class TweetServiceTest {

    @Mock
    TweetRepository tweetRepository;

    @InjectMocks
    TweetService tweetService;

    @Test
    public void shouldAddTweet() {
        //Given
        final ArgumentCaptor<TweetEntity> captor = ArgumentCaptor.forClass(TweetEntity.class);

        Status status = get4jStatus();
        TweetEntity tweet = status4jToTweetEntity(status);
        when(tweetRepository.save(captor.capture())).thenReturn(tweet);

        //When
        TweetDTO tweetDTO = tweetService.addTweet(status);

        //Then
        assertEquals(tweet.getId(), tweetDTO.getId());
        assertEquals(tweet.getId(), captor.getValue().getId());
        verify(tweetRepository).save(any(TweetEntity.class));
    }
}