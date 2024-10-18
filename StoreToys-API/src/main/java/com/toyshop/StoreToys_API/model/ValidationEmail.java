package com.toyshop.StoreToys_API.model;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;

@RedisHash(value = "validation_emails", timeToLive = 3600)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ValidationEmail {

    @Id
    String id;

    String email;

    String validationCode;
}
