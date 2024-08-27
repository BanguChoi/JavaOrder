package com.javaOrder.member.cartItem.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.member.cart.domain.Cart;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@SequenceGenerator(
		name = "cartItem_generator",
		sequenceName = "item_id_seq",
		initialValue = 1000,
		allocationSize = 1)
public class CartItem {

	@Id
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, generator = "cartItem_generator")
	private String itemIdSeq;

	@Column(name = "item_id", nullable = false)
	private String itemId;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	/*
	@OneToOne
	@JoinColumn(name = "p_id")
	private Product product;
	*/

	@Column(name = "item_num", nullable = false)
	private int itemNum;

	@Column(name = "item_price", nullable = false)
	private int itemPrice;

	@Column(name = "opt_shot", nullable = false)
	@ColumnDefault(value = "0")
	private int optionShot;

	@Column(name = "opt_size", nullable = false)
	private int optionSize;

	@Column(name = "opt_temp", nullable = false)
	private int optionTemperature;

	@Column(name = "opt_syrup")
	private int optionSyrup;

	
	
	
	@PrePersist
	public void preItemId() {
		this.itemId = cart.getCartId() + this.itemIdSeq;
	}
	
	
	/* 총 합을 구하는 로직
	@PrePersist
    @PreUpdate
	public void calculateItemPrice() {
		int addPrice = 0;

		if(this.optionShot > 0) {
			addPrice += optionShot * 500;
		}
		
		if(this.optionSize > 1) {
			addPrice += optionSize * 700;
		}
		
		if(this.optionSyrup > 0) {
			addPrice += optionSyrup * 500;
		}
		
		this.itemPrice = (this.product.getProductPrice() + addPrice) * this.itemNum ;
	}
	 */

}

