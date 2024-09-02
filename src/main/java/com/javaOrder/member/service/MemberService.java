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
	
	// 현재 로그인한 회원 정보 가져오는 메서드
	//public Member getCurrentMember();
}