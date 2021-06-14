package com.tweet.tweetconsumer.controller;

import com.tweet.tweetconsumer.DTO.HashtagDTO;
import com.tweet.tweetconsumer.service.HashtagService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hashtags")
public class HashtagController {

    @Value("${hashtag.trending-limit: 10}")
    private int topHashtagsLimit;

    private final HashtagService hashtagService;

    public HashtagController(HashtagService hashtagService) {
        this.hashtagService = hashtagService;
    }

    @GetMapping("trending")
    public ResponseEntity<List<HashtagDTO>> getTrendingTopics(@RequestParam(required = false) Integer limit) {
        List<HashtagDTO> hashtags = hashtagService.getTrendingTopics(Optional.ofNullable(limit).orElse(topHashtagsLimit));
        return ResponseEntity.ok().body(hashtags);
    }
}
