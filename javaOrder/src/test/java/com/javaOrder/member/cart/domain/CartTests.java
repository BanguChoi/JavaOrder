package com.javaOrder.member.cart.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.member.cart.repository.CartRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class CartTests {
	
	@Setter(onMethod_ = @Autowired)
	private CartRepository cartRepository;
	
	@Test
	public void cartCountTest() {
		long cartCount = cartRepository.count();
		log.info(String.valueOf(cartCount));
	}
	
}
