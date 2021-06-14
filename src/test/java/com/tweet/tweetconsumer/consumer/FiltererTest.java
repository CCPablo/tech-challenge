package com.tweet.tweetconsumer.consumer;

import com.tweet.tweetconsumer.consumer.config.FilterProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import twitter4j.Status;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static provider.Statuses4j.get4jStatus;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class FiltererTest {

    private final static String[] allowedLanguages = {"es", "fr", "it"};
    private final static String notAllowedLanguage = "ja";
    private final static int minFollowers = 1500;

    @Mock
    FilterProperties props;

    @InjectMocks
    Filterer filterer;

    @BeforeEach
    void init() {
        when(props.getAllowedLanguages()).thenReturn(allowedLanguages);
        when(props.getMinFollowers()).thenReturn(minFollowers);
    }

    @Test
    void whenLangNotValidShouldReturnFalse() {
        //When
        Status status = get4jStatus(minFollowers, notAllowedLanguage);

        //When
        boolean isValid = filterer.isTweetValid(status);

        //Then
        assertFalse(isValid);
    }

    @Test
    void whenFollowersCountNotValidShouldReturnFalse() {
        //When
        Status status = get4jStatus(minFollowers - 1, allowedLanguages[0]);

        //When
        boolean isValid = filterer.isTweetValid(status);

        //Then
        assertFalse(isValid);
    }

    @Test
    void whenFollowersCountAndLangIsValidShouldReturnFalse() {
        //When
        Status status = get4jStatus(minFollowers, allowedLanguages[0]);

        //When
        boolean isValid = filterer.isTweetValid(status);

        //Then
        assertTrue(isValid);
    }
}