package com.tweet.tweetconsumer.repository;

import com.tweet.tweetconsumer.repository.entity.HashtagEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static provider.HashtagsEntities.getRandomTopics;
import static provider.TweetEntities.getTweetEntityWithHashtags;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class HashtagRepositoryTest {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private HashtagRepository hashtagRepository;

    @Test
    void whenLessThanLimitTopicsShouldGetAllSortedTopics(){
        //Given
        List<String> topics = new ArrayList<>(getRandomTopics(4));
        tweetRepository.save(getTweetEntityWithHashtags(topics));
        tweetRepository.save(getTweetEntityWithHashtags(topics.get(1)));

        List<HashtagEntity> trendingTopics = hashtagRepository.getTrendingTopics(10);

        assertEquals(topics.size(), trendingTopics.size());
        assertEquals(topics.get(1), trendingTopics.get(0).getText());
    }

    @Test
    void whenMoreThanLimitTopicsShouldGetLimitedSortedTopics(){
        //Given
        List<String> topics = new ArrayList<>(getRandomTopics(12));
        List<String> topicList = new ArrayList<>(topics);
        while(topicList.size() != 0) {
            tweetRepository.save(getTweetEntityWithHashtags(topicList));
            topicList.remove(0);
        }

        //When
        List<HashtagEntity> trendingTopics = hashtagRepository.getTrendingTopics(10);

        //Then
        assertEquals(10, trendingTopics.size());
        assertEquals(topics.get(topics.size() - 1), trendingTopics.get(0).getText());
        assertEquals(topics.get(topics.size() - 5), trendingTopics.get(4).getText());
        assertEquals(topics.get(topics.size() - 10), trendingTopics.get(9).getText());
    }
}