package com.javaOrder.admin.promotion.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

//프로모션은ㅇ 건드리면 안됨 뭔가 잘못 됬으면 문의
@Setter
@Getter
@ToString
@Entity
@Table(name = "promotion")
@SequenceGenerator(name="prom_code_seq", initialValue = 1,  allocationSize = 1)
public class Promotion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prom_code_seq")
	@Column(name="prom_code")
	private	Long promotionCode;
	
	@Column(name="prom_title", nullable = false)
	private String promotionTitle;

	@Column(name="prom_img", nullable = true)
	private String promotionImage; // 이미지  null 추후 추가

	@CreationTimestamp
	@Column(name="prom_regdate")
	private LocalDateTime regDate;	//작성일
	
	@Column(name="a_code")
	private String adminCode;		// 작성 관리자
	
	@Column(name="prom_start")
	private LocalDate promotionStartDate;	// 시작일
	
	@Column(name="prom_end")
	private LocalDate promotionEndDate;		// 종료일

	@Column(name="prom_status")
	private String promotionStatus = "N";		// 프로모션 진행여부 N:안함 P:진행중 E:종료
}