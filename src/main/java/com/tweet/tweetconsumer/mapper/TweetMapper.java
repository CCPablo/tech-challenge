package com.tweet.tweetconsumer.mapper;

import com.tweet.tweetconsumer.DTO.TweetDTO;
import com.tweet.tweetconsumer.DTO.ValidatedTweetDTO;
import com.tweet.tweetconsumer.repository.entity.GeolocationEntity;
import com.tweet.tweetconsumer.repository.entity.HashtagEntity;
import com.tweet.tweetconsumer.repository.entity.TweetEntity;
import com.tweet.tweetconsumer.repository.entity.UserEntity;
import twitter4j.Status;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tweet.tweetconsumer.mapper.GeolocationMapper.geolocation4jToGeolocationEntity;
import static com.tweet.tweetconsumer.mapper.UserMapper.user4jToUserEntity;

public class TweetMapper {

    public static TweetEntity status4jToTweetEntity(Status status) {
        Set<HashtagEntity> hashtagSet = Arrays.stream(status.getHashtagEntities())
                .map(HashtagMapper::hashtag4jToHashtagEntity)
                .collect(Collectors.toSet());
        UserEntity user = user4jToUserEntity(status.getUser());
        GeolocationEntity geolocation = geolocation4jToGeolocationEntity(status.getGeoLocation());
        return new TweetEntity(status.getId(), status.getText(), user, hashtagSet, geolocation);
    }

    public static List<TweetDTO> tweetEntityToTweetDTO(List<TweetEntity> tweetEntities) {
        return tweetEntities.stream().map(TweetMapper::tweetEntityToTweetDTO).collect(Collectors.toList());
    }

    public static TweetDTO tweetEntityToTweetDTO(TweetEntity tweet) {
        return new TweetDTO(
                tweet.getId(),
                tweet.getText(),
                tweet.getValidated(),
                tweet.getUser().getId(),
                tweet.getHashtagSet().stream().map(HashtagEntity::getText).collect(Collectors.toSet()),
                mapGeolocation(tweet.getGeolocation()));
    }

    public static List<ValidatedTweetDTO> tweetEntitiesToValidatedTweetDTOs(List<TweetEntity> tweetEntities) {
        return tweetEntities.stream().map(TweetMapper::tweetEntityToValidatedTweetDTO).collect(Collectors.toList());
    }

    public static ValidatedTweetDTO tweetEntityToValidatedTweetDTO(TweetEntity tweet) {
        return new ValidatedTweetDTO(
                tweet.getId(),
                tweet.getText(),
                tweet.getUser().getId(),
                tweet.getHashtagSet().stream().map(HashtagEntity::getText).collect(Collectors.toSet()),
                mapGeolocation(tweet.getGeolocation()));
    }

    private static Map<String, Double> mapGeolocation(GeolocationEntity geoLocation) {
        if(geoLocation == null) {
            return null;
        }
        return Map.of("longitude", geoLocation.getLongitude(), "latitude", geoLocation.getLatitude());
    }

}
