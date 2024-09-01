package com.javaOrder.common.util.vo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

// 받아옴
/* @SuperBuilder는 빌더 패턴으로 간편하게 구현할 수 있으며 빌더 패턴은 객체를 생성하기 위한
 * 여러 속성을 가진 클래스를 생성하고, 이를 이용해 객체를 생성하는 방식이다.
 * 빌더 패턴은 객체 생성을 보다 유연하고 가독성 높은 방식으로 구현할 수 있어,
 * 객체 생성 로직이 복잡한 경우 유용하다.
 * */

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
	
	@Builder.Default
	private int page = 1;
	
	@Builder.Default
	private int size = 10;
	
	// 검색 조건 필드 추가
    private String searchType;   // 검색타입 (회원번호, 주문상태, 날짜)
    private String keyword; 	 // 검색어 (회원번호)
    private String status;		 // 검색할 주문상태
    private LocalDate startDate; // 검색 시작 날짜
    private LocalDate endDate;   // 검색 종료 날짜
}
