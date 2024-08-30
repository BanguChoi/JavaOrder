package com.javaOrder.common.orders.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.common.orders.domain.Orders;
import com.javaOrder.common.orders.repository.OrdersRepository;
import com.javaOrder.common.vo.PageRequestDTO;
import com.javaOrder.common.vo.PageResponseDTO;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrdersServiceImpl implements OrdersService {

	private final OrdersRepository orderRepository;
	private final MemberRepository memberRepository;
	
	// 모든 회원의 주문내역 리스트 (Admin)
	@Override
	public List<Orders> orderList(Orders orders) {
		List<Orders> orderList = orderRepository.findAll(Sort.by(Sort.Direction.DESC, "orderNumber"));
		return orderList;
	}
	
	// 해당 회원의 주문내역 리스트 (Member)
	@Override
	public List<Orders> orderClientList(String mCode) {
		Optional<Member> memberOptional = memberRepository.findById(mCode);
		List<Orders> orderList = orderRepository.findByMemberCode(memberOptional.get());
		return orderList;
	}
	@Override
	public Orders getOrder(Long ord_num) {
		Optional<Orders> orderOptional = orderRepository.findById(ord_num);
		Orders order = orderOptional.get();
		return order;
	}
	
	// 결제 승인 시 주문데이터 삽입 - 결제 쪽에서 추가하는걸로..?
	/*
	public void insertOrder(Orders orders) {
		orderRepository.save(orders);
	}*/
	
	// 주문 데이터 주문 상태 수정
	@Override
	public void updateOrder(Orders orders) {
		Optional<Orders> orderOptional = orderRepository.findById(orders.getOrderNumber());
		Orders updateOrder = orderOptional.get();
		
		updateOrder.setOrderStatus(orders.getOrderStatus());
		orderRepository.save(updateOrder);
	}
	
	/* 주문 데이터 삭제 - 통계에 사용되므로 삭제 불필요 (필요한데가 있나..?)
	 * public void deleteOrder(Orders orders){
	 * 		orderRepository.deleteById(orders.getOrdNum());
	 * }
	 * */
	
	// 페이징 처리
	@Override
	public PageResponseDTO<Orders> list(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage()-1, 
				pageRequestDTO.getSize(), Sort.by("orderNumber").descending());
		
		// 검색 조건 받기
		String searchType = pageRequestDTO.getSearchType();
		String keyword = pageRequestDTO.getKeyword();
		String status = pageRequestDTO.getStatus();
		
		LocalDateTime startDate = null;
		LocalDateTime endDate = null;
		if(pageRequestDTO.getStartDate() != null)
			startDate = pageRequestDTO.getStartDate().atStartOfDay();
		if(pageRequestDTO.getEndDate() != null)
			endDate = pageRequestDTO.getEndDate().atTime(23,59,59);
		
		Page<Orders> result;
		
		if("memberCode".equals(searchType) && keyword != null) {
			Optional<Member> memberOptional = memberRepository.findById(keyword);
            if (memberOptional.isPresent()) {
                result = orderRepository.findByMemberCode(memberOptional.get(), pageable);
            } else {
                // 기본 전체 리스트 반환 (검색 결과가 없을 경우)
                result = orderRepository.findAll(pageable);
            }
		}else if("orderStatus".equals(searchType) && status != null) {
			result = orderRepository.findByOrderStatus(status, pageable);
		}else if(startDate !=null && endDate != null) {
			result = orderRepository.findByOrderDateBetween(startDate, endDate, pageable);
		}else{
			result = orderRepository.findAll(pageable);
		}
		
		List<Orders> orderList = result.getContent().stream().collect(Collectors.toList());		
		Long totalCount = result.getTotalElements();
		
		PageResponseDTO<Orders> responseDTO = PageResponseDTO.<Orders>withAll()
											.dtoList(orderList)
											.pageRequestDTO(pageRequestDTO)
											.totalCount(totalCount)
											.build();
		
		return responseDTO;
	}
	
}
