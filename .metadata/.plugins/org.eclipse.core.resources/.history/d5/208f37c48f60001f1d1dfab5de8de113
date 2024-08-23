package com.javaOrder.cart.domain;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="cart")
public class Cart {
	@Id
	private String cartId;
	
	@ManyToOne
	private String mCode;
	
	@Column(nullable=false)
	@ColumnDefault(value="0")
	private int cartPrice;
	
}
