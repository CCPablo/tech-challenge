package com.tweet.tweetconsumer.repository.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tweet")
public class TweetEntity {

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "text", length = 500, nullable = false)
    private String text;

    @Column(name = "validated", nullable = false, columnDefinition="boolean default false")
    private Boolean validated = false;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference
    private UserEntity user;

    @ManyToMany(cascade = {
            CascadeType.ALL
    })
    @JoinTable(
            name = "hashtag_tweet",
            joinColumns = { @JoinColumn(name = "tweet_id") },
            inverseJoinColumns = { @JoinColumn(name = "hashtag_id") }
    )
    private Set<HashtagEntity> hashtagSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "geolocation_id")
    private GeolocationEntity geolocation;

    public TweetEntity() {}

    public TweetEntity(Long id, String text, UserEntity user, Set<HashtagEntity> hashtagSet, GeolocationEntity geolocation) {
        this.id = id;
        this.text = text;
        this.user = user;
        this.hashtagSet = hashtagSet;
        this.geolocation = geolocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<HashtagEntity> getHashtagSet() {
        return hashtagSet;
    }

    public void setHashtagSet(Set<HashtagEntity> hashtagSet) {
        this.hashtagSet = hashtagSet;
    }

    public GeolocationEntity getGeolocation() {
        return geolocation;
    }

    public void setGeolocation(GeolocationEntity geolocation) {
        this.geolocation = geolocation;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof TweetEntity) {
            return ((TweetEntity) obj).getId().equals(getId());
        }
        return false;
    }
}
