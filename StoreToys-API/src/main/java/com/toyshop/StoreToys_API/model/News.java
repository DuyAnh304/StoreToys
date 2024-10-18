package com.toyshop.StoreToys_API.model;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "news")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News {

    @Id
    String id;

    @Field("subject")
    String subject;

    @Field("title")
    String title;

    @Field("content")
    List<Content> content;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class Content {

        @Field("paragraph")
        String paragraph;

        @Field("image")
        String image;
    }
}
