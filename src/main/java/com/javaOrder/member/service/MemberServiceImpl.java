package com.javaOrder.member.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final IdGenerationService idGenerationService;
	private final CartRepository cartRepository;
	
	@Transactional
	@Override
	public void insertMemberCode(Member member) {
		String memberCode = idGenerationService.generateId("M", "member_seq", 4);
		member.setMemberCode(memberCode);
		
		Cart cart = new Cart();
	    cart.setMember(member);
	    cartRepository.save(cart);
	    
		memberRepository.save(member);
	}
	
	/*
	@Override
	public List<Member> memberList() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}*/
	// 회원 리스트 조회 (검색/페이징 기능 추가)
	@Override
	public PageResponseDTO<Member> memberList(PageRequestDTO pageRequestDTO) {
		Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage()-1, 
				pageRequestDTO.getSize(), Sort.by("memberCode").descending());
		
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
		
		Page<Member> result;
		
		if("memberCode".equals(searchType) && keyword != null) {
			Optional<Member> memberOptional = memberRepository.findById(keyword);
            if (memberOptional.isPresent()) {
                result = memberRepository.findByMemberId(memberOptional.get(), pageable);
            } else {
                // 기본 전체 리스트 반환 (검색 결과가 없을 경우)
                result = memberRepository.findAll(pageable);
            }
		}else if("name".equals(searchType) && keyword != null) {	// 검색어 포함하는 이름 검색
			result = memberRepository.findByMemberNameContaining(keyword, pageable);
		}else if("address".equals(searchType) && keyword != null) {	// 검색어 포함하는 주소 검색
			result = memberRepository.findByMemberAddressContaining(keyword, pageable);
		}else if("status".equals(searchType) && status != null) {	// 상태 검색
			result = memberRepository.findByMemberStatus(status, pageable);
		}else if("birth".equals(searchType) && startDate !=null && endDate != null) {	// 설정범위 생일 검색
			result = memberRepository.findByMemberBirthBetween(startDate, endDate, pageable);
		}else if("lastLoginDate".equals(searchType) && startDate !=null && endDate != null) {	// 설정범위 마지막 로그인일 검색
			result = memberRepository.findByMemberLastBetween(startDate, endDate, pageable);
		}else if("regDate".equals(searchType) && startDate !=null && endDate != null) {	// 설정범위 가입일 검색
			result = memberRepository.findByMemberDateBetween(startDate, endDate, pageable);
		}else{	// 전체 조회
			result = memberRepository.findAll(pageable);
		}
		
		List<Member> memberList = result.getContent().stream().collect(Collectors.toList());		
		Long totalCount = result.getTotalElements();
		
		PageResponseDTO<Member> responseDTO = PageResponseDTO.<Member>withAll()
											.dtoList(memberList)
											.pageRequestDTO(pageRequestDTO)
											.totalCount(totalCount)
											.build();
		
		return responseDTO;
	}

	// 회원 로그인
	@Override
	public Member Login(String memberId, String memberPassword) {
		Member member = memberRepository.findByMemberId(memberId);
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			return member;
		}
		return null;
	}

	
}
