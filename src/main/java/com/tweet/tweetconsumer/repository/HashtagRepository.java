package com.tweet.tweetconsumer.repository;

import com.tweet.tweetconsumer.repository.entity.HashtagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashtagEntity, String> {
    @Query(value = """
            SELECT hashtag_id AS id
            FROM hashtag_tweet
            GROUP BY hashtag_id
            ORDER BY COUNT(hashtag_id) DESC
            limit :limit""", nativeQuery = true)
    List<HashtagEntity> getTrendingTopics(@Param("limit") Integer limit);
}
