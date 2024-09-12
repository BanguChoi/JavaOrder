package com.javaOrder.common.kakaopay.service;

import java.util.List;

import com.javaOrder.common.kakaopay.domain.KakaoPayReadyResponse;
import com.javaOrder.common.kakaopay.domain.PayApproveRequest;
import com.javaOrder.common.kakaopay.domain.PayReadyRequestVO;
import com.javaOrder.member.cart.domain.CartItem;

public interface KakaoPayService {
	public KakaoPayReadyResponse ready(PayReadyRequestVO readyRequest);
	public String approve(String pgToken, PayApproveRequest payApproveRequest);
	public void copyCartToOrder(String mCode, String orderNumber);
	public String generateOrderNumber();
	public String generateOrderName(List<CartItem> cartItems);
}
