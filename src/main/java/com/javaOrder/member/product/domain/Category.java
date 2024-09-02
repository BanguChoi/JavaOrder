package com.javaOrder.member.product.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Category")
public class Category {

	@Id
	@Column(name = "cate_code", nullable = false)
	private String categoryCode;

	@Column(name = "cate_name")
	private String categoryName;

}
