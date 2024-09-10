package com.javaOrder.admin.reply.domain;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.admin.reply.repository.ReplyRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class ReplyTest {

//	Id
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_no_seq")
//	@Column(name = "reply_no", nullable = false)
//	private Long replyNo;
//	
//	@Column(name = "reply_name", nullable = false)
//	private String replyName;
//	
//	@CreationTimestamp
//	@ColumnDefault(value = "sysDate")
//	private LocalDateTime regDate;
//	
//	@ManyToOne
//	@JoinColumn(name = "boardNo")
//	private Board board;
	
		
		
	@Setter(onMethod_ = @Autowired)
	private ReplyRepository repository;
	
	/* 1번 게시글의 코멘트 조회 */
	@Test
	public void replyListTest() {
		Board board = new Board();
		board.setBoardNo(1L);
		
		//List<Reply> list = repository.findByBoardNo(1L);
		List<Reply> list = repository.findByReplyName("홍길동");
		
		log.info("### 1번 게시글 코멘트 목록 조회");
		for(Reply reply : list) {
			log.info(reply.toString());
		}
	}
	// 데이터 입력 
//	@Test
//	public void replyInsertTest() {
//		Board board = new Board();
//		board.setBoardNo(102751L);
//		
//		Reply reply = new Reply();
//		reply.setReplyName("늘한봄");
//		reply.setReplyContent("우리 인생은 우리들이 노력한 만큼 가치가 있다.");
//		reply.setBoard(board);
//		log.info("### reply 테이블에 첫번째 데이터 입력");
//		repository.save(reply);
//		
//		Reply reply1 = new Reply();
//		reply1.setReplyName("홍길동");
//		reply1.setReplyContent("실패한 자가 패배하는 것이 아니라 포기한 자가 패배하는 것이다.");
//		reply1.setBoard(board);
//		log.info("### reply1 테이블에 두번째 데이터 입력");
//		repository.save(reply1);
//		
//		Reply reply2 = new Reply();
//		reply2.setReplyName("강희수");
//		reply2.setReplyContent("단 한번의 노력으로 자기의 바람을 성취하려한다면 그건 도둑놈이다.");
//		reply2.setBoard(board);
//		log.info("### reply1 테이블에 세번째 데이터 입력");
//		repository.save(reply2);
//		
//		Reply reply3 = new Reply();
//		reply3.setReplyName("김영희");
//		reply3.setReplyContent("말이 입힌 상처는 칼이 입힌 상처보다 깊다.");
//		reply3.setBoard(board);
//		log.info("### reply1 테이블에 네번째 데이터 입력");
//		repository.save(reply3);
//	}

	
}


