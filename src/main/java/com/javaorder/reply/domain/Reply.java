//package com.javaorder.reply.domain;
//
//
//import java.time.LocalDateTime;
//
//import org.hibernate.annotations.ColumnDefault;
//import org.hibernate.annotations.CreationTimestamp;
//
//import com.javaorder.board.domain.Board;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.SequenceGenerator;
//import jakarta.persistence.Table;
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//@Setter
//@Getter
//@ToString
//@Entity
//@Table(name="reply")
//@SequenceGenerator(name = "reply_no_seq", initialValue = 100001, allocationSize = 50 )
//public class Reply {
//	
//	@Id
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
//	@JoinColumn(name = "board_boardNo")
//	private Board boardNo;
//	
//	
//	
////	private String adminCode;
//}
