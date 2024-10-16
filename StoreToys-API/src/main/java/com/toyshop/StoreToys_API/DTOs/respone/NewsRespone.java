package com.toyshop.StoreToys_API.DTOs.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsRespone {

    @JsonProperty("id")
    String id;

    @JsonProperty("subject")
    String subject;

    @JsonProperty("title")
    String title;

    @JsonProperty("content")
    List<ContentResponse> content;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class ContentResponse {

        @JsonProperty("paragraph")
        String paragraph;

        @JsonProperty("image")
        String image;
    }
}
