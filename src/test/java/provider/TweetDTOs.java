package provider;

import com.tweet.tweetconsumer.DTO.TweetDTO;
import com.tweet.tweetconsumer.DTO.ValidatedTweetDTO;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TweetDTOs {

    private static final String DEFAULT_TWEET_TEXT = "Text";
    private static final Boolean DEFAULT_VALIDATED = false;
    private static final Long DEFAULT_USER_ID = 1L;
    private static final Set<String> DEFAULT_HASHTAGS = Set.of("Topic1", "Topic2");
    private static final Map<String, Double> DEFAULT_GEOLOCATION = null;

    private static final AtomicLong tweetId = new AtomicLong();

    public static List<ValidatedTweetDTO> getValidatedTweetDTOs(int n) {
        return IntStream.range(0, n).mapToObj(i -> getValidatedTweetDTO()).collect(Collectors.toList());
    }

    public static List<TweetDTO> getTweetDTOs(int n) {
        return IntStream.range(0, n).mapToObj(i -> getTweetDTO()).collect(Collectors.toList());
    }

    public static TweetDTO getTweetDTO() {
        return getTweetDTO(DEFAULT_TWEET_TEXT, DEFAULT_VALIDATED, DEFAULT_USER_ID, DEFAULT_HASHTAGS, DEFAULT_GEOLOCATION);
    }

    public static ValidatedTweetDTO getValidatedTweetDTO() {
        return getValidatedTweetDTO(DEFAULT_TWEET_TEXT, DEFAULT_USER_ID, DEFAULT_HASHTAGS, DEFAULT_GEOLOCATION);
    }

    public static ValidatedTweetDTO getValidatedTweetDTO(Long userId) {
        return getValidatedTweetDTO(DEFAULT_TWEET_TEXT, userId, DEFAULT_HASHTAGS, DEFAULT_GEOLOCATION);
    }

    public static TweetDTO getTweetDTO(String text, Boolean validated, Long userId, Set<String> hashtags, Map<String, Double> geolocation) {
        return new TweetDTO(tweetId.getAndIncrement(), text, validated, userId, hashtags, geolocation);
    }

    public static ValidatedTweetDTO getValidatedTweetDTO(String text, Long userId, Set<String> hashtags, Map<String, Double> geolocation) {
        return new ValidatedTweetDTO(tweetId.getAndIncrement(), text, userId, hashtags, geolocation);
    }
}
