//package com.javaorder.reply.domain;
//
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
//
//import java.time.LocalDateTime;
//
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import com.javaorder.board.domain.Board;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import lombok.extern.slf4j.Slf4j;
//
//@SpringBootTest
//@Slf4j
//public class ReplyTest {
//
////	Id
////	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_no_seq")
////	@Column(name = "reply_no", nullable = false)
////	private Long replyNo;
////	
////	@Column(name = "reply_name", nullable = false)
////	private String replyName;
////	
////	@CreationTimestamp
////	@ColumnDefault(value = "sysDate")
////	private LocalDateTime regDate;
////	
////	@ManyToOne
////	@JoinColumn(name = "boardNo")
////	private Board board;
//	
//		
//		@Test
//		public void ReplyInsertTest() {
//			Reply reply = new Reply();
//			reply.setReplyTitle("게시판이 이게 뭡니까");
//			reply.setReplyContent("쉬지말고 일하세요.");
//			reply.setReplyPasswd("1234");
//			log.info(reply.toString());		
//			repository.save(reply);
//			
//			Reply reply2 = new Reply();
//			reply2.setReplyTitle("게시판이 심심하세요");
//			reply2.setReplyContent("노예처럼 일하세요.");
//			reply2.setReplyPasswd("1234");
//			log.info(reply2.toString());		
//			repository.save(reply2);
//			
//			Reply reply3 = new Reply();
//			reply3.setReplyTitle("게시판이 가볍군요");
//			reply3.setReplyContent("더 일하세요.");
//			reply3.setReplyPasswd("1234");
//			log.info(reply3.toString());		
//			repository.save(reply3);
//			
//			Reply reply4 = new Reply();
//			reply4.setReplyTitle("게시판이 그 냥게시판이잖아!");
//			reply4.setReplyContent("알아서 일하세요.");
//			reply4.setReplyPasswd("1234");
//			log.info(reply4.toString());		
//			repository.save(reply4);		
//		}
//		@Test
////		public void replyListTests() {
////			List<Reply> list = repository.findAll();
////			for(Reply reply : list) {
////				log.info(reply.toString());
////			}
////		}
////		@Test
////		public void replyDetailTest() {
////			Optional<Reply> Boop = repository.findById(100001L);
////			if(Boop.isPresent()) {
////				Reply reply = Boop.get();
////				log.info(reply.toString());
////			}
////		}
////		@Test
////		public void replyUpdateTest() {
////			Optional<Reply> Boop = repository.findById(100001L);
////			if(Boop.isPresent()) {
////				Reply reply = Boop.get();
////				reply.setReplyTitle("게시판을 부셔라!");
////				reply.setReplyContent("골통도 같이 부셔줘라!");
////				log.info(reply.toString());
////				
////				repository.save(reply);
////			}
////		}
////		@Test
////		public void replyDeleteTest() {
////			repository.deleteById(100001L);
////				
////		}
//	}
//
//	
//}//////////////
