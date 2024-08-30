package com.javaOrder.common.kakaopay.domain;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
// 클래스 단위로 매핑 시의 naming strategy 지정가능
// 근데 PropertyNamingStrategy는 이제 @Deprecated, 대신 PropertyNamingStrategies 사용 
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)	
public class KakaoPayReadyRequest {
	private String cid;
    private String partnerOrderId;
    private String partnerUserId;
    private String itemName;
    private Integer quantity;
    private Integer totalAmount;
    private Integer taxFreeAmount;
    private Integer vatAmount;
    private String approvalUrl;
    private String cancelUrl;
    private String failUrl;
}
