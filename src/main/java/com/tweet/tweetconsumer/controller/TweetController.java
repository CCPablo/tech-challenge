package com.tweet.tweetconsumer.controller;

import com.tweet.tweetconsumer.DTO.ValidatedTweetDTO;
import com.tweet.tweetconsumer.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getTweets(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        Map<String, Object> tweets = tweetService.getPagedTweets(page, size);
        return ResponseEntity.ok().body(tweets);
    }

    @GetMapping("validated")
    public ResponseEntity<List<ValidatedTweetDTO>> getValidatedTweets(@RequestParam(required = false) Long userId) {
        List<ValidatedTweetDTO> validatedTweets = userId == null
                ? tweetService.getValidatedTweets()
                : tweetService.getValidatedTweetsByUserId(userId);

        return ResponseEntity.ok().body(validatedTweets);
    }

    @PatchMapping("validate")
    public ResponseEntity<ValidatedTweetDTO> validateTweet(@RequestParam(name= "id") Long tweetId) {
        try {
            ValidatedTweetDTO validated = tweetService.validateTweet(tweetId);
            return ResponseEntity.ok().body(validated);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
