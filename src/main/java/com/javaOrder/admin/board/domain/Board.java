package com.javaOrder.admin.board.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name="board")
@SequenceGenerator(name = "board_no_seq", initialValue = 100001, allocationSize = 50 )
public class Board {	
	
	@Id
	@Column(name = "board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
	private Long boardNo;
	
	@Column(name="board_title", nullable = false)
	private String boardTitle;
	
//	@Lob 주의 커밋시 항상 적용
	@Column(name="board_content", nullable = false)
	private String boardContent;

	//커밋시에는 컬럼 wd 로 변경 ws
	@Column(name="board_passws", nullable = false)
	private String boardPasswd;
		
	@CreationTimestamp
	@ColumnDefault(value = "sysDate")
	private LocalDateTime regDate;// board date변경 regDate >> regDate 0910


	
//	게시글상태 받아오기
//	@Column(name="board_status", nullable = false)
//	private String boardStatus;	
	
//회원번호 받아오기
//	private String memberNUmber;
	
	

}
