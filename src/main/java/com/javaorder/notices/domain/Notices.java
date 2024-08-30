package com.javaorder.notices.domain;

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
@Table(name = "notices")
@SequenceGenerator(name="notices_code_seq", initialValue = 100001,  allocationSize = 50)
public class Notices {

		@Id
		@Column(name="Notices_no")
		@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notices_code_seq")
		private	Long NoticesNo;
		
		@Column(name="Notices_title", nullable = false)
		private String NoticesTitle;
		
		@Column(name="Notices_content", nullable = false)
		private String NoticesContent;

		@Column(name="Notices_name", nullable = false)
		private String NoticesName;				
		
		@CreationTimestamp
		@ColumnDefault(value="sysDate")
		private LocalDateTime regDate;	//작성일
		
		

//		private String adminCode; //작성자코드 추후 연결

}