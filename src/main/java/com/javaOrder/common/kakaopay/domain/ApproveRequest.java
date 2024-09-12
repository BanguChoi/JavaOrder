package com.javaOrder.common.kakaopay.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ApproveRequest {
	private String cid;
	private String tid;
	private String partnerOrderId;
	private String partnerUserId;
	private String pgToken;
}
