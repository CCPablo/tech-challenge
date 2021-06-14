package com.tweet.tweetconsumer.repository;

import com.tweet.tweetconsumer.repository.entity.TweetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<TweetEntity, Long> {

    @Query(value = """
            SELECT *
            FROM tweet t
            WHERE t.validated=true""", nativeQuery = true)
    List<TweetEntity> findAllValidated();

    @Query(value = """
            SELECT *
            FROM tweet t
            WHERE t.validated=true AND t.user_id=:userId""", nativeQuery = true)
    List<TweetEntity> findAllValidatedByUserId(@Param("userId") Long userId);

}
