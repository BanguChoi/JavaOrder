package com.javaOrder.admin.reply.domain;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.javaOrder.admin.board.domain.Board;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name="reply")
@SequenceGenerator(name = "reply_id_seq", initialValue = 100001, allocationSize = 50 )
public class Reply {
	
	//댓글용 수정
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_id_seq")
	@Column(name = "reply_id", nullable = false)
	private Long replyId;//boardNo 랑 구분이 힘들어 id로 변경
	
	@Column(name = "reply_name", nullable = false)
	private String replyName;//nickname
	
	@Column(name="reply_content")
	private String replyContent;//body?
	
	@CreationTimestamp
	@ColumnDefault(value = "sysDate")
	private LocalDateTime replyDate;
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board board;

//관리자코드 	
//	private String adminCode;
}
