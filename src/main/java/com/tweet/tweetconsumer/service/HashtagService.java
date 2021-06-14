package com.tweet.tweetconsumer.service;

import com.tweet.tweetconsumer.DTO.HashtagDTO;
import com.tweet.tweetconsumer.repository.HashtagRepository;
import com.tweet.tweetconsumer.repository.entity.HashtagEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tweet.tweetconsumer.mapper.HashtagMapper.hashtagEntitiesToHashtagDTOs;

@Service
public class HashtagService {

    private final HashtagRepository hashtagRepository;

    public HashtagService(HashtagRepository hashtagRepository) {
        this.hashtagRepository = hashtagRepository;
    }

    public List<HashtagDTO> getTrendingTopics(Integer limit) {
        List<HashtagEntity> hashtagEntities = hashtagRepository.getTrendingTopics(limit);
        return hashtagEntitiesToHashtagDTOs(hashtagEntities);
    }
}
