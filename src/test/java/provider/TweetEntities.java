package provider;

import com.tweet.tweetconsumer.repository.entity.GeolocationEntity;
import com.tweet.tweetconsumer.repository.entity.HashtagEntity;
import com.tweet.tweetconsumer.repository.entity.TweetEntity;
import com.tweet.tweetconsumer.repository.entity.UserEntity;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static provider.HashtagsEntities.getHashtagEntities;
import static provider.UserEntities.getUserEntity;

public class TweetEntities {

    private static final String DEFAULT_TWEET_TEXT = "Text";
    private static final UserEntity DEFAULT_USER_ENTITY = getUserEntity();
    private static final Set<HashtagEntity> DEFAULT_HASHTAG_ENTITIES = getHashtagEntities();
    private static final GeolocationEntity DEFAULT_GEOLOCATION = null;

    private static final AtomicLong tweetId = new AtomicLong();

    public static List<TweetEntity> getTweetEntities(int n) {
        return IntStream.range(0, n).mapToObj(i -> getTweetEntity()).collect(Collectors.toList());
    }

    public static TweetEntity getTweetEntity() {
        return getTweetEntity(DEFAULT_TWEET_TEXT, DEFAULT_USER_ENTITY, DEFAULT_HASHTAG_ENTITIES, DEFAULT_GEOLOCATION);
    }

    public static TweetEntity getTweetEntityWithHashtags(List<String> topics) {
        return getTweetEntity(DEFAULT_TWEET_TEXT, DEFAULT_USER_ENTITY, getHashtagEntities(topics), DEFAULT_GEOLOCATION);
    }

    public static TweetEntity getTweetEntityWithHashtags(String ...topics) {
        return getTweetEntity(DEFAULT_TWEET_TEXT, DEFAULT_USER_ENTITY, getHashtagEntities(topics), DEFAULT_GEOLOCATION);
    }

    public static TweetEntity getTweetEntity(String text, UserEntity userEntity, Set<HashtagEntity> hashtagEntities, GeolocationEntity geolocationEntity) {
        return new TweetEntity(tweetId.getAndIncrement(), text, userEntity, hashtagEntities, geolocationEntity);
    }
}
