package com.tweet.tweetconsumer.repository.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "followers_count", nullable = false)
    private Integer followersCount;

    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch=FetchType.LAZY)
    private List<TweetEntity> publishedTweets;

    public UserEntity() {}

    public UserEntity(Long id, String name, Integer followersCount) {
        this.id = id;
        this.name = name;
        this.followersCount = followersCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(Integer followersCount) {
        this.followersCount = followersCount;
    }

    public List<TweetEntity> getPublishedTweets() {
        return publishedTweets;
    }

    public void setPublishedTweets(List<TweetEntity> publishedTweets) {
        this.publishedTweets = publishedTweets;
    }
}
