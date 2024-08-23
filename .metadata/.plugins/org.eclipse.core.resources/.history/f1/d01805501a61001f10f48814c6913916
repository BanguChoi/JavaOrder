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

}


/*
CREATE TABLE Cart_Item (
    item_id VARCHAR2(20) NOT NULL,
    cart_id VARCHAR2(20) NOT NULL,
    p_id VARCHAR2(20) NOT NULL,
    item_num NUMBER NOT NULL,
    item_price NUMBER NOT NULL,
    opt_shot NUMBER DEFAULT 0 NOT NULL,
    opt_size NUMBER NOT NULL,
    opt_temp NUMBER NOT NULL,
    opt_syrup NUMBER,
    CONSTRAINT pk_cart_item PRIMARY KEY (item_id),
    CONSTRAINT fk_cart_item_cart FOREIGN KEY (cart_id) REFERENCES Cart(cart_id),
    CONSTRAINT fk_cart_item_product FOREIGN KEY (p_id) REFERENCES Product(p_id)
);
*/