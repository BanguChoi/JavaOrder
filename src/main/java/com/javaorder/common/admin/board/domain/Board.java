package com.javaorder.common.admin.board.domain;

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
	

//	참조후 삭제해주세요
	
//	데이터베이스에서 직접 시퀀스를 생성
//	데이터베이스에서 직접 시퀀스를 생성하여 start with 값을 설정할 수 있습니다. 예를 들어, Oracle의 경우:
//
//	sql
//	코드 복사
//	CREATE SEQUENCE board_no_seq
//	START WITH 100001
//	INCREMENT BY 50;
//	그런 다음, JPA에서 해당 시퀀스를 참조하도록 합니다.
//
//	java
//	코드 복사
//	@SequenceGenerator(name = "board_no_seq", sequenceName = "board_no_seq", allocationSize = 50)
//	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
////////////////////////////////////////
	
	
	@Id
	@Column(name = "board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
	private Long boardNo;
	
	@Column(name="board_title", nullable = false)
	private String boardTitle;
	
	@Column(name="board_content", nullable = false)
	private String boardContent;
	
	@Column(name="board_passws", nullable = false) // 수정 wd ws
	private String boardPasswd;
		
	@CreationTimestamp
	@ColumnDefault(value = "sysDate")
	private LocalDateTime regDate;

	
//	ㅇ완성 연결후에 다시 해야함 없어서 에러뜬다?? 없어도 되는듯? reply 쪽에서 설정함
//	@OneToMany(mappedBy = "board")
//	private Set<Reply> replys;	
	
//	게시글상태 받아오기
//	@Column(name="board_status", nullable = false)
//	private String boardStatus;	
	
//회원번호 받아오기
//	private String memberNUmber;
	
	

}
