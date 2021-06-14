package provider;

import twitter4j.HashtagEntity;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Hashtags4j {

    private static final String[] DEFAULT_TOPICS = {"Topic1", "Topic2"};

    public static HashtagEntity[] getHashtags() {
        return Arrays.stream(DEFAULT_TOPICS)
                .map(Hashtags4j::getHashtag)
                .collect(Collectors.toList())
                .toArray(HashtagEntity[]::new);
    }

    public static HashtagEntity[] getHashtags(String ...topics) {
         return Arrays.stream(topics)
                 .map(Hashtags4j::getHashtag)
                 .collect(Collectors.toList())
                 .toArray(HashtagEntity[]::new);
    }

    public static HashtagEntity getHashtag(String text) {
        return new HashtagEntity() {
            @Override
            public String getText() {
                return text;
            }

            @Override
            public int getStart() {
                return 0;
            }

            @Override
            public int getEnd() {
                return 0;
            }
        };
    }
}
