package com.tweet.tweetconsumer.mapper;

import com.tweet.tweetconsumer.DTO.HashtagDTO;
import com.tweet.tweetconsumer.repository.entity.HashtagEntity;

import java.util.List;
import java.util.stream.Collectors;

public class HashtagMapper {

    public static HashtagEntity hashtag4jToHashtagEntity(twitter4j.HashtagEntity hashtag) {
        return new HashtagEntity(hashtag.getText());
    }

    public static List<HashtagDTO> hashtagEntitiesToHashtagDTOs(List<HashtagEntity> hashtag) {
        return hashtag.stream().map(HashtagMapper::hashtagEntityToHashtagDTO).collect(Collectors.toList());
    }

    public static HashtagDTO hashtagEntityToHashtagDTO(HashtagEntity hashtag) {
        return new HashtagDTO(hashtag.getText());
    }
}
