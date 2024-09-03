package com.javaOrder.member.cart.domain;

import java.util.ArrayList;
import java.util.List;

import com.javaOrder.member.domain.Member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	@Id
	@Column(name = "cart_id", nullable = false)
	private String cartId;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "m_code", referencedColumnName = "m_code")
	private Member member;
	

	@Column(name = "cart_price")
	private int cartPrice;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<CartItem> cartItems = new ArrayList<>();
	
	
	/* 카트 아이디 자동 부여 
	@PrePersist
	public void preCartId() {
	    if (member != null) {
	        this.cartId = "C" + member.getMemberCode();
	    }
	}
	*/
	
	/* 카트 아이템 가격 총 합 구하는 로직
	@OneToMany(mappedBy = "cart")
	private List<CartItem> cartItem;

	@PostLoad
	public void calcCartPrice() {
		this.cartPrice = calcTotalCartPrice(); 
	}
	
	private int calcTotalCartPrice() {
		int total = 0;
		for(CartItem item : cartItem) {
			total += item.getItemPrice();
		}
		return total;
	}
	 */
}


/*
CREATE TABLE Cart (
    cart_id VARCHAR2(20) NOT NULL,
    m_code VARCHAR2(20) NOT NULL,
    cart_price NUMBER,
    CONSTRAINT pk_cart PRIMARY KEY (cart_id),
    CONSTRAINT fk_cart_member FOREIGN KEY (m_code) REFERENCES Member(m_code)
);
*/
