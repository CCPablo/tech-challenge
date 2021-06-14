package provider;

import com.tweet.tweetconsumer.repository.entity.UserEntity;

import java.util.concurrent.atomic.AtomicLong;

public class UserEntities {

    private static final String DEFAULT_USERNAME = "User";

    private static final AtomicLong userId = new AtomicLong();

    public static UserEntity getUserEntity() {
        return getUserEntity(DEFAULT_USERNAME);
    }

    public static UserEntity getUserEntity(String name) {
        return new UserEntity(userId.getAndIncrement(), name, 1500);
    }

}
