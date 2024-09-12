package com.javaOrder.admin.reply.domain;


import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.admin.domain.Admin;

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
@Table(name="reply")
@SequenceGenerator(name = "reply_id_seq", initialValue = 1, allocationSize = 1 )
public class Reply {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reply_id_seq")
	@Column(name = "reply_id", nullable = false)
	private Long replyId;
	
	@Column(name = "reply_name", nullable = false)
	private String replyName;
	
	@Lob
	@Column(name="reply_content")
	private String replyContent;
	
	@CreationTimestamp
	@ColumnDefault(value = "sysDate")
	private LocalDateTime replyDate;
	
	@ManyToOne
	@JoinColumn(name = "boardNo")
	private Board board;

	//관리자코드
	@ManyToOne
	@JoinColumn(name="a_code")
	private Admin admin;
}
