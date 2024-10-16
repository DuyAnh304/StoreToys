package com.toyshop.StoreToys_API.model;

import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document("validation_emails")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@CompoundIndexes({
        @CompoundIndex(name = "code_email", def = "{'code' : 1, 'email' : 1}")
})
public class ValidationEmail {

    @Id
    String id;

    @Field("code")
    String validationCode;

    @Field("email")
    String email;
}
