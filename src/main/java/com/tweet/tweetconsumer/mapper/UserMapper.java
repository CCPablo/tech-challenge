package com.tweet.tweetconsumer.mapper;

import com.tweet.tweetconsumer.repository.entity.UserEntity;
import twitter4j.User;

public class UserMapper {

    public static UserEntity user4jToUserEntity(User user) {
        return new UserEntity(user.getId(), user.getName(), user.getFollowersCount());
    }
}
