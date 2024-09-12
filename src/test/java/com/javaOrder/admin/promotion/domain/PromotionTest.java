package com.javaOrder.admin.promotion.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.javaOrder.admin.promotion.repository.PromotionRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class PromotionTest {
	
	@Setter(onMethod_= @Autowired)
	private PromotionRepository repository;
	
	@Test
	public void promotionSearchKeywordTest() {
		PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(10).size(1).keyword("10월").build();
		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() -1, //1페이지가 0 부터 시작
				pageRequestDTO.getSize(), Sort.by("promotionCode").descending());
		Page<Promotion> result = repository.findByPromotionTitleContaining("10월", pageable);
		
		log.info("-------------------------");
		for(Promotion p : result) {
			log.info(p.toString());
		}
		log.info("-------------------------");
	}
	
	/* 게시판 전체 레코드 수 구하기 - count(): 사용가능한 엔티티 수를 반환 
	@Test
	public void promotionCountTest() {
		long promotionCount = repository.count();
		log.info(String.valueOf(promotionCount));
	} 
	*/
	// 게시판 등록 - save(): 주어진 엔티티를 저장 
//	@Test
//	public void promotionInsertTest() {
//		Promotion promotion = new Promotion();
//		promotion.setName("늘한봄");
//		promotion.setTitle("노력 명언");
//		promotion.setContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
//		promotion.setPasswd("1234");
//		promotion.setRegDate(LocalDateTime.now());
//		
//		log.info("### promotion 테이블에 첫번째 데이터 입력");
//		promotionRepository.save(promotion);
//		
//		promotion promotion1 = new promotion();
//		promotion1.setName("홍길동");
//		promotion1.setTitle("끈기 명언");
//		promotion1.setContent("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는 것이다.");
//		promotion1.setPasswd("1234");
//		promotion.setRegDate(LocalDateTime.now());
//		
//		log.info("### promotion 테이블에 두번째 데이터 입력");
//		promotionRepository.save(promotion1);
//		
//		promotion promotion2 = new promotion();
//		promotion2.setName("강희수");
//		promotion2.setTitle("끈기 명언");
//		promotion2.setContent("단 한번의 노력으로 자기의 바람을 성취할 수 없다. 또한 단 한번의 실패로 그 소망을 모두 포기할 수도 없는 것이다.");
//		promotion2.setPasswd("1234");
//		
//		log.info("### promotion 테이블에 세번째 데이터 입력");
//		promotionRepository.save(promotion2);
//		
//		promotion promotion3 = new promotion();
//		promotion3.setName("강희수");
//		promotion3.setTitle("언어 명언");
//		promotion3.setContent("말이 입힌 상처는 칼이 입힌 상처보다 깊다.");
//		promotion3.setPasswd("1234");
//		
//		log.info("### promotion 테이블에 네번째 데이터 입력");
//		promotionRepository.save(promotion3);
//	} 
	
	/*
	  @Test
		public void promotionInsertTest() {
			Promotion promotion = new Promotion();
			promotion.setPromotionCode((long)(100009));
			promotion.setPromotionTitle("노력 명언");
//			promotion.setPromotionContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
//			promotion.setPromotionName("으라!");
			promotion.setRegDate(LocalDateTime.now());
			
			log.info("### promotion 테이블에 첫번째 데이터 입력");
			repository.save(promotion);
	  }*/
	  
	/* 게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.
	@Test
	public void promotionListTest() {
		List<Promotion> promotionList = (List<Promotion>) repository.findAll();
		for(Promotion promotion: promotionList) {
			log.info(promotion.toString());
		}
	}*/
//	
//	// 게시판 상세 조회 - findById(ID id): ID로 엔티티를 검색.  
//	@Test
//	public void promotionDetailTest() {
//		Optional<Promotion> promotionOptional = repository.findById(1L);
//		// isPresent() 메소드를 사용하여 Optional 객체에 저장된 값이 null인지 아닌지를 먼저 확인
//		if(promotionOptional.isPresent()) {
//			Promotion promotion = promotionOptional.get();
//			log.info(promotion.toString());
//		}
//	} 
}
//package com.javaOrder.admin.promotion.domain;
//

//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.javaOrder.admin.promotion.repository.PromotionRepository;
//
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//public class PromotionTest {
//	
//	@Setter(onMethod_= @Autowired)
//	private PromotionRepository repository;
//	
//	// 게시판 전체 레코드 수 구하기 - count(): 사용가능한 엔티티 수를 반환 
//	@Test
//	public void promotionCountTest() {
//		long promotionCount = repository.count();
//		log.info(String.valueOf(promotionCount));
//	} 
//	
//	// 게시판 등록 - save(): 주어진 엔티티를 저장 
////	@Test
////	public void promotionInsertTest() {
////		Promotion promotion = new Promotion();
////		promotion.setName("늘한봄");
////		promotion.setTitle("노력 명언");
////		promotion.setContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
////		promotion.setPasswd("1234");
////		promotion.setRegDate(LocalDateTime.now());
////		
////		log.info("### promotion 테이블에 첫번째 데이터 입력");
////		promotionRepository.save(promotion);
////		
////		promotion promotion1 = new promotion();
////		promotion1.setName("홍길동");
////		promotion1.setTitle("끈기 명언");
////		promotion1.setContent("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는 것이다.");
////		promotion1.setPasswd("1234");
////		promotion.setRegDate(LocalDateTime.now());
////		
////		log.info("### promotion 테이블에 두번째 데이터 입력");
////		promotionRepository.save(promotion1);
////		
////		promotion promotion2 = new promotion();
////		promotion2.setName("강희수");
////		promotion2.setTitle("끈기 명언");
////		promotion2.setContent("단 한번의 노력으로 자기의 바람을 성취할 수 없다. 또한 단 한번의 실패로 그 소망을 모두 포기할 수도 없는 것이다.");
////		promotion2.setPasswd("1234");
////		
////		log.info("### promotion 테이블에 세번째 데이터 입력");
////		promotionRepository.save(promotion2);
////		
////		promotion promotion3 = new promotion();
////		promotion3.setName("강희수");
////		promotion3.setTitle("언어 명언");
////		promotion3.setContent("말이 입힌 상처는 칼이 입힌 상처보다 깊다.");
////		promotion3.setPasswd("1234");
////		
////		log.info("### promotion 테이블에 네번째 데이터 입력");
////		promotionRepository.save(promotion3);
////	} 
//	
//	
//	  @Test
//		public void promotionInsertTest() {
//			Promotion promotion = new Promotion();
//			promotion.setPromCode((long)(100009));
//			promotion.setPromTitle("노력 명언");
//			promotion.setPromContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
//			promotion.setPromName("으라!");
//			promotion.setRegDate(LocalDateTime.now());
//			
//			log.info("### promotion 테이블에 첫번째 데이터 입력");
//			repository.save(promotion);
//	  }
//	  
//	// 게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.
//	@Test
//	public void promotionListTest() {
//		List<Promotion> promotionList = (List<Promotion>) repository.findAll();
//		for(Promotion promotion: promotionList) {
//			log.info(promotion.toString());
//		}

//	}
////	
////	// 게시판 상세 조회 - findById(ID id): ID로 엔티티를 검색.  
////	@Test
////	public void promotionDetailTest() {
////		Optional<Promotion> promotionOptional = repository.findById(1L);
////		// isPresent() 메소드를 사용하여 Optional 객체에 저장된 값이 null인지 아닌지를 먼저 확인
////		if(promotionOptional.isPresent()) {
////			Promotion promotion = promotionOptional.get();
////			log.info(promotion.toString());
////		}
////	} 
//}	
////import java.time.LocalDateTime;
////import java.util.List;
////
////import org.junit.jupiter.api.Test;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.boot.test.context.SpringBootTest;
////
////import com.javaorder.admin.promotion.repository.PromotionRepository;
////
////import lombok.Setter;
////import lombok.extern.slf4j.Slf4j;
////
////@SpringBootTest
////@Slf4j
////public class PromotionTest {
////    
////    @Setter(onMethod_= @Autowired)
////    private PromotionRepository repository;
//
////    // prom_code, prom_title, prom_content, prom_name, prom_img, reg_date)
////    @Test
////	public void promotionInsertTest() {
////		Promotion promotion = new Promotion();
////		promotion.setPromCode(100009);
////		promotion.setPromTitle("노력 명언");
////		promotion.setPromContent("우리 인생은 우리들이 노력한만큼 가치가 있다.");
////		promotion.setPromName("으라!");
////		promotion.setRegDate(LocalDateTime.now());
////		
////		log.info("### promotion 테이블에 첫번째 데이터 입력");
////		repository.save(promotion);
////		
////		
////    @Test
////   public void promotionListTest() {
////    	List<Promotion> list = (List<Promotion>)repository.findAll();
////    	
////		for(Promotion promotion : list) {
////			log.info(promotion.toString());
////		}
////		
////    }	
////}    
