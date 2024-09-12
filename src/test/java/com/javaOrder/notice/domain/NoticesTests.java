//package com.javaOrder.notice.domain;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.javaOrder.admin.notices.domain.Notices;
//import com.javaOrder.admin.notices.repository.NoticesRepository;
//
//import lombok.Setter;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//public class NoticesTests {
//
//	@Setter(onMethod_= @Autowired)
//	private NoticesRepository repository;
//	
//
//		
//		@Test
//		public void noticesInsertTest() {
//			Notices notices = new Notices();
//		//	notices.setNoticesNo((long)(100001));
//			notices.setNoticesTitle("노력 명언");
//			notices.setNoticesContent("우리 인생은 우리들이 노력한만큼 가치가 있다1.");
//			notices.setNoticesName("된장");
//			notices.setRegDate(LocalDateTime.now());
//			
//			log.info("### notices 테이블에 첫번째 데이터 입력");
//			repository.save(notices);
//			
//			Notices notices2 = new Notices();
//			notices2.setNoticesNo((long)(100002));
//			notices2.setNoticesTitle("노력 명언2");
//			notices2.setNoticesContent("우리 인생은 우리들이 노력한만큼 가치가 있다2.");
//			notices2.setNoticesName("고추장");
//			notices2.setRegDate(LocalDateTime.now());
//			
//			log.info("### notices 테이블에 첫번째 데이터 입력");
//			repository.save(notices2);
//			
//			Notices notices3 = new Notices();
//		//	notices3.setNoticesNo((long)(100003));
//			notices3.setNoticesTitle("노력 명언3");
//			notices3.setNoticesContent("우리 인생은 우리들이 노력한만큼 가치가 있다3.");
//			notices3.setNoticesName("쌈장");
//			notices3.setRegDate(LocalDateTime.now());
//			
//			log.info("### notices 테이블에 첫번째 데이터 입력");
//			repository.save(notices3);
//			
//	  }
//	  
//	 //게시판 리스트 - findAll(): T타입의 모든 인스턴스를 반환.
//		@Test
//		public void noticesListTest() {
//			List<Notices> noticesList = (List<Notices>) repository.findAll();
//			for(Notices notices: noticesList) {
//				log.info("되나?");
//				log.info(notices.toString());
//			}
//		}
//		@Test
//		public void noticeDetail() {			
//		Optional<Notices> Nop = repository.findById(100042L);
//		if(Nop.isPresent()) {
//			Notices notices = Nop.get();
//			log.info(notices.toString());
//		}
//		}
//		@Test
//		public void noticeUpdate() {			
//		Optional<Notices> Nop = repository.findById(100043L);
//		if(Nop.isPresent()) {
//			Notices notices = Nop.get();
//			notices.setNoticesTitle("된장국");
//			notices.setNoticesContent("된장국은 양파 파  호박 멸치나 해물을 조금 넣고 끓으면 된장을 넣는다. 거품을 걷너내고 간을 맞추면 된다.뭐야 야채 비싸!");
//			log.info(notices.toString());
//			
//			repository.save(notices);
//		}
//		}
//		@Test
//		public void noticeDelete() {
//			repository.deleteById(100042L);
//		
//	}
//}
