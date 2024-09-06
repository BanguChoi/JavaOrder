package com.javaOrder.admin.notices.domain;

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
@Table(name = "notices2")
@SequenceGenerator(name="notices_code_seq", initialValue = 100001,  allocationSize = 50)
public class Notices {//위에 시퀀스 네임 바꿔주기

		@Id
		@Column(name="notices_no")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notices_code_seq") 
		private	Long noticesNo;
		
		@Column(name="notices_title", nullable = false)
		private String noticesTitle;
		
		@Column(name="notices_content", nullable = false)
		private String noticesContent;

		@Column(name="notices_name", nullable = false)
		private String noticesName;				
		
		@CreationTimestamp
		@ColumnDefault(value="sysDate")
		private LocalDateTime regDate;	//작성일
		
		

//		private String adminCode; //작성자코드 추후 연결

}