package com.javaOrder.board.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.board.repository.BoardRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class BoardTests {
	
	@Setter(onMethod_ = @Autowired)
	private BoardRepository repository;
	
//	@Test
//	public void BoardInsertTest() {
//		Board board = new Board();
//		board.setBoardTitle("게시판이 이게 뭡니까");
//		board.setBoardContent("쉬지말고 일하세요.");
//		board.setBoardPasswd("1234");
//		log.info(board.toString());		
//		repository.save(board);
//		
//		Board board2 = new Board();
//		board2.setBoardTitle("게시판이 심심하세요");
//		board2.setBoardContent("노예처럼 일하세요.");
//		board2.setBoardPasswd("1234");
//		log.info(board2.toString());		
//		repository.save(board2);
//		
//		Board board3 = new Board();
//		board3.setBoardTitle("게시판이 가볍군요");
//		board3.setBoardContent("더 일하세요.");
//		board3.setBoardPasswd("1234");
//		log.info(board3.toString());		
//		repository.save(board3);
//		
//		Board board4 = new Board();
//		board4.setBoardTitle("게시판이 그 냥게시판이잖아!");
//		board4.setBoardContent("알아서 일하세요.");
//		board4.setBoardPasswd("1234");
//		log.info(board4.toString());		
//		repository.save(board4);		
//	}
//	@Test
//	public void boardListTests() {
//		List<Board> list = repository.findAll();
//		for(Board board : list) {
//			log.info(board.toString());
//		}
//	}
//	@Test
//	public void boardDetailTest() {
//		Optional<Board> Boop = repository.findById(100001L);
//		if(Boop.isPresent()) {
//			Board board = Boop.get();
//			log.info(board.toString());
//		}
//	}
//	@Test
//	public void boardUpdateTest() {
//		Optional<Board> Boop = repository.findById(100001L);
//		if(Boop.isPresent()) {
//			Board board = Boop.get();
//			board.setBoardTitle("게시판을 부셔라!");
//			board.setBoardContent("골통도 같이 부셔줘라!");
//			log.info(board.toString());
//			
//			repository.save(board);
//		}
//	}
//	@Test
//	public void boardDeleteTest() {
//		repository.deleteById(100001L);
//			
//	}
}
