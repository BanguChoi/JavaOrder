package com.javaOrder.common.kakaopay.service;

import com.javaOrder.common.kakaopay.domain.KakaoPayReadyResponse;
import com.javaOrder.common.kakaopay.domain.PayApproveRequest;
import com.javaOrder.common.kakaopay.domain.PayReadyRequestVO;
import com.javaOrder.common.orders.domain.Orders;

public interface KakaoPayService {
	public KakaoPayReadyResponse ready(PayReadyRequestVO readyRequest);
	public String approve(String pgToken, PayApproveRequest payApproveRequest);
	public Orders copyCartToOrder(String mCode);
}
