package com.toyshop.StoreToys_API.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@RedisHash("RedisToken")
public class RedisToken extends AbstractEntity{

    Integer id;

    String username;

    String accessToken;
    
    String refreshToken;
}
