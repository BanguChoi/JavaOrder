package com.javaOrder.admin.notices.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
@Table(name = "notices")
@SequenceGenerator(name="notices_code_seq", initialValue = 1,  allocationSize = 1)
public class Notices {//위에 시퀀스 네임 바꿔주기
		@Id
		@Column(name="notices_no")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notices_code_seq") 
		private	Long noticesNo;
		
		@Column(name="notices_title", nullable = false)
		private String noticesTitle;

		@Lob
		@Column(name="notices_content", nullable = false)
		private String noticesContent;

		@Column(name="notices_name", nullable = false)
		private String noticesName;				
		
		@CreationTimestamp
		private LocalDateTime regDate;	//작성일

		@ManyToOne
		@JoinColumn(name="a_code")
		private Admin admin; //작성자코드 추후 연결

}