package com.javaOrder.common.kakaopay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class PayReadyRequestVO {
	private String partnerOrderId;	// 주문번호
	private String partnerUserId;		// 회원번호
	private String itemName;		// 상품명
	private Integer quantity;			// 수량
	private Integer totalAmount;		// 총금액
	private Integer taxFreeAmount;
	private Integer vatAmount;
	private Integer takeout;	// 수령방식
}
