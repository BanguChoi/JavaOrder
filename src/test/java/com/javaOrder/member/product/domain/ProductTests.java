package com.javaOrder.member.product.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.product.domain.Product;
import com.javaOrder.admin.product.repository.ProductRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ProductTests {

	@Setter(onMethod_ = @Autowired)
	private ProductRepository productRepository;

	/* 제품 갯수 출력 */
	@Test
	public void productListTest() {
		List<Product> list  = productRepository.findAll();
		for(Product product : list) {
			log.info(product.toString());
		}
	}

}
