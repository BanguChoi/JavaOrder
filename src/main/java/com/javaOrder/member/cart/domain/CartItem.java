package com.javaOrder.member.cart.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.admin.product.domain.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "Cart_Item")
public class CartItem {
		
	@Id
	@Column(name = "item_id",  nullable = false)
	private String itemId;

	@ManyToOne
	@JoinColumn(name = "cart_id")
	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "p_id")
	private Product product;

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
	
	
	/* 옵션값이 더해진 최종 가격 구하는 로직
	@PrePersist
    @PreUpdate
	public void calculateItemPrice() {
		int addPrice = 0;
		if(this.optionShot > 0) {
			addPrice += optionShot * 500;
		}
		
		if(this.optionSize > 0) {
			addPrice += optionSize * 700;
		}
		
		if(this.optionSyrup > 0) {
			addPrice += optionSyrup * 500;
		}
		
		this.itemPrice = (this.product.getProductPrice() + addPrice) * this.itemNum ;
	} 
	 */
}