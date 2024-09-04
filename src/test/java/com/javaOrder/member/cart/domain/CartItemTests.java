package com.javaOrder.member.cart.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.product.repository.ProductRepository;
import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.cart.repository.CartItemRepository;
import com.javaOrder.member.cart.repository.CartRepository;

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
	
	@Autowired
	private IdGenerationService idGenerationService;
	
	/* 카트 아이템 리스트 출력 테스트 */
	
	
	/*
	cart에 cartitem insert 테스트
	option 선택 시 금액 자동반영 테스트
	
	@Test
	public void insertCartItemTest() {
		// db에 넣어둔 cart, product 데이터 조회
		Cart cart = cartRepository.findById("CM0001").orElseThrow();
		// log.info(String.valueOf(cart.getMember()));
		
		Product product = productRepository.findById("A0101").orElseThrow();
		// log.info(String.valueOf(product.getProductPrice()));
		
	    String itemId = idGenerationService.generateId(cart.getCartId(), "item_id_seq");
		
	    // log.info("카트아이템 아이디: " + itemId);
		
		CartItem item = new CartItem();
		item.setItemId(itemId);
		
		item.setCart(cart);
		item.setProduct(product);
		
		item.setItemNum(1);
		item.setItemPrice(product.getProductPrice());
		item.setOptionShot(2);
		item.setOptionSize(1);
		item.setOptionTemperature(0);
		
		cartItemRepository.save(item);
		
		cart.calcCartPrice();
		cartRepository.save(cart);
		
        log.info("cartitemid: " + item.getItemId());
        log.info("cart total price: " + cart.getCartPrice());
	}
	*/
	
	
	/* cartitem update test
	@Test
	public void updateCartItemTest() {
		Cart cart = cartRepository.findById("CM0001").orElseThrow();
		CartItem item = cartItemRepository.findById("CM00010004").orElseThrow();
		
		item.setOptionShot(0);
		cartItemRepository.save(item);
		
		cart.calcCartPrice();
		cartRepository.save(cart);
		
		log.info(item.toString());
	}
	 */

	/* cartitem delete test
	@Test
	public void deleteCartItemTest() {
		Cart cart = cartRepository.findById("CM0001").orElseThrow();
		cartItemRepository.deleteById("CM00010006");
		
		cart.calcCartPrice();
		cartRepository.save(cart);		
	}
	*/
	
	
	
	
	
	
	
	
	
	
	
	

}
