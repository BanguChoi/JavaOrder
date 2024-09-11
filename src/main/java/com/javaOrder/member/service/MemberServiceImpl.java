package com.javaOrder.member.service;

import java.time.LocalDate;
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
import com.javaOrder.common.util.service.MaskingUtils;
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
		memberRepository.save(member);
		
		/* 회원가입시 카트 자동생성 */
        Cart cart = new Cart();
        cart.setCartId("C" + memberCode);
        cart.setMember(member);   
        cartRepository.save(cart);
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
                result = memberRepository.findByMemberCode(keyword, pageable);
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
		
		List<Member> memberList = result.getContent().stream()
				.map(member -> {
			        // 마스킹 처리
			        member.setMemberName(MaskingUtils.maskName(member.getMemberName()));
			        member.setMemberId(MaskingUtils.maskId(member.getMemberId()));
			        member.setMemberEmail(MaskingUtils.maskEmail(member.getMemberEmail()));
			        member.setMemberPhone(MaskingUtils.maskPhone(member.getMemberPhone()));
			        return member;
			    })
				.collect(Collectors.toList());		
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

	// 중복 아이디 확인
	@Override
	public boolean checkMemberId(String memberId) {
		return memberRepository.existsByMemberId(memberId);
	}
	
	
	// 회원 수정 처리
	@Transactional
    @Override
    public boolean updateMemberField(String memberCode, String fieldId, String newValue) {
        // 회원 코드로 회원 정보 조회
		Member member = memberRepository.findByMemberCode(memberCode);
        // 회원이 존재하지 않는 경우 실패
		if (member == null) {
            return false; 
        }

        // 필드에 따라 값 업데이트
        switch (fieldId) {
            case "memberName":
                member.setMemberName(newValue);
                break;
            case "memberEmail":
                member.setMemberEmail(newValue);
                break;
            case "memberPhone":
                member.setMemberPhone(newValue);
                break;
            case "memberAddress":
                member.setMemberAddress(newValue);
                break;
            default:
                return false; // 유효하지 않은 필드인 경우 수정 실패
        }

        memberRepository.save(member); // 변경 사항 저장
        return true;
    }
	
	// 회원 코드 정보 조회
	@Override
    public Member getMemberByCode(String memberCode) {
		// 회원 코드로 회원 정보 조회 반한
        return memberRepository.findByMemberCode(memberCode);
    }
	
	// 현재 비밀번호 확인
	@Override
	public boolean validateCurrentPassword(String memberCode, String currentPassword) {
	    Member member = memberRepository.findByMemberCode(memberCode);
	    return member != null && member.getMemberPasswd().equals(currentPassword);
	}
		
	// 비밀번호 변경
	@Transactional
	@Override
	public boolean changePassword(String memberCode, String currentPassword, String newPassword) {
	    Member member = memberRepository.findByMemberCode(memberCode);
	    if (member != null && member.getMemberPasswd().equals(currentPassword)) {
	        // 비밀번호 변경
	    	member.setMemberPasswd(newPassword);
	        //수정일 갱신
	        member.setMemberLast(LocalDate.now());
	        memberRepository.save(member);
	        return true;
	    }
	    return false;
	}
	
	// 회원 탈퇴
	@Override
	public boolean deleteMember(String memberCode) {
		try {
            Member member = memberRepository.findByMemberCode(memberCode);

            if (member != null) {
                member.setMemberName(memberCode);
                member.setMemberId(memberCode);
                member.setMemberPasswd(memberCode);
                member.setMemberEmail(null);
                member.setMemberPhone(memberCode);
                member.setMemberAddress(null);
                member.setMemberBirth(LocalDate.of(0001, 1, 1));
                member.setMemberStatus("N");

                memberRepository.save(member);
                return true; // 성공적으로 null로 설정됨
            } else {
                return false; // 회원이 존재하지 않음
            }
        } catch (Exception e) {
            return false; // 오류 발생
        }
    }
	
}
