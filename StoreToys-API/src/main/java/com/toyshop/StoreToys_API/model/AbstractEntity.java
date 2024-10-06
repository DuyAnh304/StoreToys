package com.toyshop.StoreToys_API.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class AbstractEntity {

	@Column(name = "create_at")
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	Date createdAt;
	
	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	Date updatedAt;
}
