package com.tweet.tweetconsumer.mapper;

import org.springframework.data.domain.Page;

import java.util.Map;

public class PageMapper {

    public static <T>Map<String, Object> getPageResponse(Page<T> page) {
        return Map.of(
                "data", page.getContent(),
                "currentPage", page.getNumber(),
                "totalItems", page.getTotalElements(),
                "totalPages", page.getTotalPages()
        );
    }
}
