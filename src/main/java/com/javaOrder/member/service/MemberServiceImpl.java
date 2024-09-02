package com.javaOrder.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final IdGenerationService idGenerationService;
	
	@Transactional
	@Override
	public void insertMemberCode(Member member) {
		String memberCode = idGenerationService.generateId("M", "member_seq", 4);
		member.setMemberCode(memberCode);
		memberRepository.save(member);
	}
	@Override
	public List<Member> memberList() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
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
