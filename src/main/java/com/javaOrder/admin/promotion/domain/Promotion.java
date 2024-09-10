package com.javaOrder.admin.promotion.domain;

import java.time.LocalDateTime;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "promotion2") //최종커밋시 2지우기 0910
@SequenceGenerator(name="prom_code_seq", initialValue = 100001,  allocationSize = 10)
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prom_code_seq")
	@Column(name="prom_code")
	private	Long promCode;
	
	@Column(name="prom_title", nullable = false)
	private String promTitle;
	
	//@Lob 푸쉬에 항상 조심
//	@Lob 이부분은 물어보고 커밋
	@Column(name="prom_content", nullable = false)
	private String promContent;

	@Column(name="prom_name", nullable = false)
	private String promName;
	
	@Column(name="prom_img", nullable = true)
	private String promImg; // 이미지  null 추후 추가
	//디테일 나중에	private String prom_detail_img; 이미지용 
	
	@Transient
	private MultipartFile file;	//파일 업로드를 위한 필드  jaka
	
	@CreationTimestamp
	@ColumnDefault(value="sysDate")
	private LocalDateTime regDate;	//이름변경 0910

	

//관리자 코드 	private String a_code;
//시작 추후추가	private LocalDate prom_startDate
//종료 추후추가	private LocalDate prom_endDate;

}