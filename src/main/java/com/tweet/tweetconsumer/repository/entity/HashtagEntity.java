package com.tweet.tweetconsumer.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hashtag")
public class HashtagEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String text;

    @ManyToMany(mappedBy = "hashtagSet", fetch = FetchType.LAZY)
    private Set<TweetEntity> relatedTweets;

    public HashtagEntity() {}

    public HashtagEntity(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
