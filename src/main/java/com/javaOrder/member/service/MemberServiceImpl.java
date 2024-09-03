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
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final IdGenerationService idGenerationService;
	
	// 회원 추가
	@Transactional
	@Override
	public void insertMemberCode(Member member) {
		// 회원 코드 생성 ( "M" + "시퀀스" + "자릿수" )
		String memberCode = idGenerationService.generateId("M", "member_seq", 4);
		// 생성된 회원 코드 Member 에 설정
		member.setMemberCode(memberCode);
		// Member 객체 저장
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
		// 회원 ID 정보 조회
		Member member = memberRepository.findByMemberId(memberId);
		// 비밀번호 일치 여부 조회 후 member 객체 반환 및 null 값 반환
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			return member;
		}
		return null;
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
	        member.setMemberLast(LocalDateTime.now());
	        memberRepository.save(member);
	        return true;
	    }
	    return false;
	}
	
}
