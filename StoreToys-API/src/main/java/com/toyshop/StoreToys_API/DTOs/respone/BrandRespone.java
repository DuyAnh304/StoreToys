package com.toyshop.StoreToys_API.DTOs.respone;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BrandRespone {

    @JsonProperty("brand_id")
    int brand_id;
    @JsonProperty("brand_name")
    String brand_name;
    @JsonProperty("brand_img")
    String brand_img;
}
