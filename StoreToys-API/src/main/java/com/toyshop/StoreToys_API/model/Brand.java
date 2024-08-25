package com.toyshop.StoreToys_API.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "brands")
@Builder
public class Brand {

    @Id
    @Column(name = "brand_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer brandId;

    @Column(name = "brand_name")
    String brandName;

    @Column(name = "brand_img")
    String brandImg;

}
