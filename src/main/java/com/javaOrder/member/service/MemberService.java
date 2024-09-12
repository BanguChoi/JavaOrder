package com.javaOrder.member.service;

import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.domain.Member;

public interface MemberService {
	// 회원 추가 메서드
	public void insertMemberCode(Member member);
	
	// 회원 목록 메서드
//	public List<Member> memberList(PageRequestDTO pageRequestDTO);
	public PageResponseDTO<Member> memberList(PageRequestDTO pageRequestDTO);
	// 로그인 메서드
	public Member Login(String memberId, String memberPassword);
	
	// 중복 아이디 확인
	public boolean checkMemberId(String memberId);
	
	// 회원 수정 메서드
	boolean updateMemberField(String memberCode, String fieldId, String newValue);
	
	// 회원 수정 정보 추가 메서드
	public Member getMemberByCode(String memberCode);
	
	// 비밀번호 변경
	boolean changePassword(String memberCode, String currentPassword, String newPassword);
	
	// 현재 비밀번호 확인
	boolean validateCurrentPassword(String memberCode, String currentPassword);
	
	// 회원 탈퇴
	public boolean deleteMember(String memberCode);
	
	// 현재 로그인한 회원 정보 가져오는 메서드
	//public Member getCurrentMember();
}