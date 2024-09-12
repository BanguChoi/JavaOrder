package com.javaOrder.common.kakaopay.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class PayApproveRequest {
	private String partnerOrderId;
	private String partnerUserId;
}
