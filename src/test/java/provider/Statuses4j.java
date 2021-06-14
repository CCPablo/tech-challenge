package provider;

import twitter4j.*;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import static provider.Hashtags4j.getHashtags;
import static provider.Users4j.get4jUser;

public class Statuses4j {

    private static final User DEFAULT_USER = get4jUser();
    private static final String DEFAULT_TWEET_TEXT = "Text";
    private static final String DEFAULT_LANG = "es";
    private static final HashtagEntity[] DEFAULT_HASHTAGS = getHashtags();

    private static final AtomicLong tweetId = new AtomicLong();

    public static Status get4jStatus() {
        return get4jStatus(DEFAULT_LANG);
    }

    public static Status get4jStatus(String lang) {
        return get4jStatus(DEFAULT_USER, lang);
    }

    public static Status get4jStatus(User user, String lang) {
        return get4jStatus(DEFAULT_TWEET_TEXT, user, lang, DEFAULT_HASHTAGS);
    }

    public static Status get4jStatus(int followersCount, String lang) {
        return get4jStatus(DEFAULT_TWEET_TEXT, get4jUser(followersCount), lang, DEFAULT_HASHTAGS);
    }

    public static Status get4jStatus(String text, User user, String lang, HashtagEntity[] hashtagEntities) {
        long id = tweetId.getAndIncrement();
        return new Status() {
            @Override
            public Date getCreatedAt() {
                return null;
            }

            @Override
            public long getId() {
                return id;
            }

            @Override
            public String getText() {
                return text;
            }

            @Override
            public int getDisplayTextRangeStart() {
                return 0;
            }

            @Override
            public int getDisplayTextRangeEnd() {
                return 0;
            }

            @Override
            public String getSource() {
                return null;
            }

            @Override
            public boolean isTruncated() {
                return false;
            }

            @Override
            public long getInReplyToStatusId() {
                return 0;
            }

            @Override
            public long getInReplyToUserId() {
                return 0;
            }

            @Override
            public String getInReplyToScreenName() {
                return null;
            }

            @Override
            public GeoLocation getGeoLocation() {
                return null;
            }

            @Override
            public Place getPlace() {
                return null;
            }

            @Override
            public boolean isFavorited() {
                return false;
            }

            @Override
            public boolean isRetweeted() {
                return false;
            }

            @Override
            public int getFavoriteCount() {
                return 0;
            }

            @Override
            public User getUser() {
                return user;
            }

            @Override
            public boolean isRetweet() {
                return false;
            }

            @Override
            public Status getRetweetedStatus() {
                return null;
            }

            @Override
            public long[] getContributors() {
                return new long[0];
            }

            @Override
            public int getRetweetCount() {
                return 0;
            }

            @Override
            public boolean isRetweetedByMe() {
                return false;
            }

            @Override
            public long getCurrentUserRetweetId() {
                return 0;
            }

            @Override
            public boolean isPossiblySensitive() {
                return false;
            }

            @Override
            public String getLang() {
                return lang;
            }

            @Override
            public Scopes getScopes() {
                return null;
            }

            @Override
            public String[] getWithheldInCountries() {
                return new String[0];
            }

            @Override
            public long getQuotedStatusId() {
                return 0;
            }

            @Override
            public Status getQuotedStatus() {
                return null;
            }

            @Override
            public URLEntity getQuotedStatusPermalink() {
                return null;
            }

            @Override
            public int compareTo(Status o) {
                return 0;
            }

            @Override
            public UserMentionEntity[] getUserMentionEntities() {
                return new UserMentionEntity[0];
            }

            @Override
            public URLEntity[] getURLEntities() {
                return new URLEntity[0];
            }

            @Override
            public HashtagEntity[] getHashtagEntities() {
                return hashtagEntities;
            }

            @Override
            public MediaEntity[] getMediaEntities() {
                return new MediaEntity[0];
            }

            @Override
            public SymbolEntity[] getSymbolEntities() {
                return new SymbolEntity[0];
            }

            @Override
            public RateLimitStatus getRateLimitStatus() {
                return null;
            }

            @Override
            public int getAccessLevel() {
                return 0;
            }
        };
    }
}
