package com.toyshop.StoreToys_API.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product extends AbstractEntity {

	@Id
	@Column(name = "product_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer productId;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	Category category;
	
	@ManyToOne
	@JoinColumn(name = "brand_id")
	Brand brand;
	
	@Column(name = "product_name")
	String productName;
	
	@Column(name = "product_quantity")
	Integer productQuantity;
	
	@Column(name = "product_img")
	String productImg;
	
	@Column(name = "product_sex")
	String productSex;
	
	@Column(name = "product_price")
	Float productPrice;

	@OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	Set<Cart> cart;
}
