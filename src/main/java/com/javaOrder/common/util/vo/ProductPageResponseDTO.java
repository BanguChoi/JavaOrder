package com.javaOrder.common.util.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.javaOrder.admin.product.domain.Product;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductPageResponseDTO<E> {
	private List<E> dtoList;
	private List<Integer> pageNumList;
	private List<Product> topProductList;
	private ProductPageRequestDTO productPageRequestDTO;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private int prevPage;
	private int nextPage;
	private int totalPage;
	private int current;
	private String category;
	
	
	@Builder(builderMethodName = "withAll")
	public ProductPageResponseDTO(List<E> dtoList, ProductPageRequestDTO productPageRequestDTO, long totalCount) {
		this.dtoList = dtoList;
		this.productPageRequestDTO = productPageRequestDTO;
		this.totalCount = (int) totalCount;
		this.topProductList = new ArrayList<>();
		
		int end = (int) (Math.ceil(productPageRequestDTO.getPage() / 10.0)) * 10;
		int start = end - 9;
		int last = (int) (Math.ceil((totalCount / (double) productPageRequestDTO.getSize())));
		
		end = end > last ? last : end;
		
		this.prev = start > 1;
		this.next = totalCount > end * productPageRequestDTO.getSize();
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
		/*1~10까지 반복해서 list에 담는 과정*/
		
		
		if(prev) {
			this.prevPage = start - 1;
		}
		if(next) {
			this.nextPage = end + 1;
		}
		
		this.totalPage = this.pageNumList.size();
		this.current = productPageRequestDTO.getPage();
		this.category = productPageRequestDTO.getCategory();
	}
}