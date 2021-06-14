package provider;

import com.tweet.tweetconsumer.repository.entity.HashtagEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HashtagsEntities {

    public static Set<String> getRandomTopics(int n) {
        return IntStream.range(0, n).mapToObj(i -> UUID.randomUUID().toString()).collect(Collectors.toSet());
    }

    public static Set<HashtagEntity> getRandomHashtags(int n) {
        return getHashtagEntities(getRandomTopics(n));
    }

    public static Set<HashtagEntity> getHashtagEntities(Set<String> hashtags) {
        return hashtags.stream().map(HashtagEntity::new).collect(Collectors.toSet());
    }

    public static Set<HashtagEntity> getHashtagEntities(List<String> hashtags) {
        return hashtags.stream().map(HashtagEntity::new).collect(Collectors.toSet());
    }

    public static Set<HashtagEntity> getHashtagEntities(String ...hashtags) {
        return Arrays.stream(hashtags).map(HashtagEntity::new).collect(Collectors.toSet());
    }
}
