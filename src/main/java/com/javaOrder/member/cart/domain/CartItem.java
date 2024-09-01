package com.javaOrder.member.cart.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.common.product.domain.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;
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
	@Column(name = "item_id", nullable = false)
	private String itemId;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;

	
	@ManyToOne
	@JoinColumn(name = "p_id")
	private Product product;
	

	@Column(name = "item_num", nullable = false)
	private Integer itemNum;

	@Column(name = "item_price", nullable = false)
	private Integer itemPrice;

	@Column(name = "opt_shot", nullable = false)
	@ColumnDefault(value = "0")
	private Integer optionShot;

	@Column(name = "opt_size", nullable = false)
	private Integer optionSize;

	@Column(name = "opt_temp", nullable = false)
	private Integer optionTemperature;

	@Column(name = "opt_syrup")
	private Integer optionSyrup;

	
	//@Id
	@Transient
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, generator = "cartItem_generator")
	private Long itemIdSeq;	
	
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

