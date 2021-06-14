package com.tweet.tweetconsumer.controller;

import com.tweet.tweetconsumer.DTO.TweetDTO;
import com.tweet.tweetconsumer.DTO.ValidatedTweetDTO;
import com.tweet.tweetconsumer.service.TweetService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static provider.TweetDTOs.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = TweetController.class)
class TweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TweetService tweetService;

    @Test
    void getTweetPage() throws Exception {
        //Given
        List<TweetDTO> tweetEntities = getTweetDTOs(2);
        Map<String, Object> response = Map.of(
                "data",tweetEntities,
                "currentPage", 0,
                "totalItems", 3,
                "totalPages", 1
        );

        when(tweetService.getPagedTweets(anyInt(), anyInt())).thenReturn(response);

        //When
        ResultActions mvcResult = mockMvc.perform(
                get("/api/tweets"));

        //Then
        mvcResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPage", is(response.get("currentPage"))))
                .andExpect(jsonPath("$.totalItems", is(response.get("totalItems"))))
                .andExpect(jsonPath("$.totalPages", is(response.get("totalPages"))))
                .andExpect(jsonPath("$.data[0].id", is(tweetEntities.get(0).getId().intValue())))
                .andExpect(jsonPath("$.data[1].id", is(tweetEntities.get(1).getId().intValue())));
    }

    @Test
    void whenGetValidatedWithNoUserIdShouldReturnAllValidated() throws Exception {
        //Given
        List<ValidatedTweetDTO> response = getValidatedTweetDTOs(2);

        when(tweetService.getValidatedTweets()).thenReturn(response);

        //When
        ResultActions mvcResult = mockMvc.perform(
                get("/api/tweets/validated"));

        //Then
        mvcResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is(response.get(0).getId().intValue())))
                .andExpect(jsonPath("$[1].id", is(response.get(1).getId().intValue())));

        verify(tweetService).getValidatedTweets();
        verify(tweetService, never()).getValidatedTweetsByUserId(anyLong());
    }

    @Test
    void whenGetValidatedWithUserIdShouldReturnValidatedByUser() throws Exception {
        //Given
        List<ValidatedTweetDTO> response = List.of(
                getValidatedTweetDTO(1L),
                getValidatedTweetDTO(1L)
        );

        when(tweetService.getValidatedTweetsByUserId(1L)).thenReturn(response);

        //When
        ResultActions mvcResult = mockMvc.perform(
                get("/api/tweets/validated?userId=1"));

        //Then
        mvcResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].publisherId", is(1)))
                .andExpect(jsonPath("$[1].publisherId", is(1)));

        verify(tweetService).getValidatedTweetsByUserId(anyLong());
        verify(tweetService, never()).getValidatedTweets();
    }

    @Test
    void whenValidateWithInvalidTweetIdShouldReturnNotFound() throws Exception {
        //Given
        when(tweetService.validateTweet(1L)).thenThrow(EntityNotFoundException.class);

        //When
        ResultActions mvcResult = mockMvc.perform(
                patch("/api/tweets/validate?id=1"));

        //Then
        mvcResult.andExpect(status().isNotFound());
    }

    @Test
    void whenValidateWithValidTweetIdShouldReturnValidatedTweet() throws Exception {
        //Given
        when(tweetService.validateTweet(1L)).thenReturn(getValidatedTweetDTO(1L));

        //When
        ResultActions mvcResult = mockMvc.perform(
                patch("/api/tweets/validate?id=1"));

        //Then
        mvcResult
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.publisherId", is(1)));
    }
}