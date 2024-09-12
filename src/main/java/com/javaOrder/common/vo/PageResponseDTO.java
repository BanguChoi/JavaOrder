package com.javaOrder.common.vo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
/* 제네릭 클래스 */
public class PageResponseDTO<E> {

	private List<E> dtoList;
	private List<Integer> pageNumList;
	private PageRequestDTO pageRequestDTO;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int current;

	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

		this.dtoList = dtoList;
		this.pageRequestDTO = pageRequestDTO;
		this.totalCount = (int)totalCount;


		int end = (int)(Math.ceil(pageRequestDTO.getPage() / 10.0)) * 10;	// Math.ceil(): 주어진 숫자보다 크거나 같은 가장 작은 정수를 반환
		int start = end - 9;
		int last = (int)(Math.ceil((totalCount / (double)pageRequestDTO.getSize())));

		end = end > last ? last : end; // 계산된 end가 last보다 크면 last로 조정

		this.prev = start > 1;
		this.next = totalCount > end * pageRequestDTO.getSize();
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
						// IntStream.rangeClosed(): 시작값(포함)부터 종료값(포함)까지의 연속된 정수 스트림을 생성
						// .boxed(): 기본 타입 int 스트림을 Integer 객체 스트림으로 변환(List<Integer>). 기본 타입을 객체로 박싱(boxing)
						// Collectors.toList(): 스트림의 요소들을 List로 수집

		if(prev) {
			this.prevPage = start - 1;
		}
		if(next) {
			this.nextPage = end + 1;
		}
		this.totalPage = this.pageNumList.size();
		this.current = this.pageRequestDTO.getPage();
	}


}