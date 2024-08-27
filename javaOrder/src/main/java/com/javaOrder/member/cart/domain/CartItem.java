package com.javaOrder.member.cart.domain;

import org.hibernate.annotations.ColumnDefault;

import com.javaOrder.member.product.domain.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CartItem {
		
	@Id
	@Column(name = "item_id", nullable = false)
	private String itemId;
	
	@Transient
	private Long itemIdSeq;

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
	


    @PrePersist
    public void generateItemId() {
        if (this.cart != null && this.cart.getCartId() != null) {
            this.itemId = this.cart.getCartId() + String.format("%04d", this.itemIdSeq);
        }
    }

	 

}

