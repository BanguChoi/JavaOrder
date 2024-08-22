package com.javaOrder.common.kakaopay.service;

import com.javaOrder.common.kakaopay.model.KakaoPayReadyResponseVO;

public interface KakaoPayService {
	public KakaoPayReadyResponseVO ready(String agent, String openType);
	public String approve(String pgToken);
}
