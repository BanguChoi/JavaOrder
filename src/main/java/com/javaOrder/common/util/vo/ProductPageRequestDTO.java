package com.javaOrder.common.util.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class ProductPageRequestDTO {
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int size = 12;
	
	// 검색 조건 필드 추가
    private String searchType;   // 검색타입 (회원번호, 주문상태, 날짜)
    private String keyword; 	 // 검색어 (회원번호)
    private String status;		 // 검색할 주문상태
    private LocalDate startDate; // 검색 시작 날짜
    private LocalDate endDate;   // 검색 종료 날짜
    private String category;	 // 카테고리
	
}
