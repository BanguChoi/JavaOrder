package com.javaOrder.member.cart.domain;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.product.domain.Product;
import com.javaOrder.member.product.repository.ProductRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;


@SpringBootTest
@Slf4j
public class CartItemTests {
	
	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Test
	@Transactional
	public void insertCartItemTest() {
		// db에 넣어둔 cart, product 데이터 조회
		Cart cart = cartRepository.findById("CM0001").orElseThrow();
		// log.info(String.valueOf(cart.getMember()));
		
		Product product = productRepository.findById("A0101").orElseThrow();
		// log.info(String.valueOf(product.getProductPrice()));
		
	    
		CartItem item = new CartItem();
		item.setCart(cart);
		item.setProduct(product);
		
		item.setItemNum(1);
		item.setItemPrice(product.getProductPrice());
		item.setOptionShot(2);
		item.setOptionSize(1);
		item.setOptionTemperature(0);
		
		cartItemRepository.save(item);
		
        log.info("cartitemid: " + item.getItemId());
        
        CartItem savedItem = cartItemRepository.findById(item.getItemId()).orElse(null);
        assertNotNull(savedItem, "CartItem should be saved in the database.");
	}
	

}
