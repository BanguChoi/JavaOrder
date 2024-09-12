package com.javaOrder.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/*
 * @SuperBuilder는 빌더 패턴(Builder Pattern)으로 간편하게 구현할 수 있으며 빌더 패턴은 객체를 생성하기 위한 여러 속성을 가진 클래스를 생성하고,
 * 이를 이용하여 객체를 생성하는 방식이다. 빌더 패턴은 객체 생성을 보다 유연하고 가독성 높은 방식으로 구현할 수 있어, 객체 생성 로직이 복잡한 경우 유용하다.*/

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

	@Builder.Default
	private int page = 1;

	@Builder.Default
	private int size = 10;
}