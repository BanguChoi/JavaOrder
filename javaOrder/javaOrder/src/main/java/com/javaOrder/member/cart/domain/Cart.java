package com.javaOrder.member.cart.domain;

import com.javaOrder.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Cart {

	@Id
	@Column(name = "cart_id", nullable = false)
	private String cartId;

	
	@OneToOne
	@JoinColumn(name = "m_code")
	private Member memberCode;
	

	@Column(name = "cart_price")
	private Integer cartPrice;

	/*
	@PrePersist
	public void preCartId() {
		this.cartId = "c" + member.getMCode();
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