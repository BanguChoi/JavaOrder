package com.javaOrder.admin.board.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.javaOrder.member.domain.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
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
@SequenceGenerator(name = "board_no_seq", initialValue = 1, allocationSize = 1 )
public class Board {	
	
	@Id
	@Column(name = "board_no")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_no_seq")
	private Long boardNo;

	//회원번호 받아오기
	@ManyToOne
	@JoinColumn(name="m_code")
	private Member member;
	
	@Column(name="board_title", nullable = false)
	private String boardTitle;
	
	@Lob 
	@Column(name="board_content", nullable = false)
	private String boardContent;

	
	@Column(name="board_passwd", nullable = false)
	private String boardPasswd;
		
	@CreationTimestamp
	@ColumnDefault(value = "sysDate")
	private LocalDateTime regDate;// board date변경 regDate >> regDate 0910


	
//	게시글상태 받아오기
//	@Column(name="board_status", nullable = false)
//	private String boardStatus;	
	

	
}
