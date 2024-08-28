package com.javaOrder.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.common.service.IdGenerationService;
import com.javaOrder.member.repository.MemberRepository;
import com.javaOrder.member.vo.Member;

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
		String memberCode = idGenerationService.generateId("M", "member_seq");
		member.setMemberCode(memberCode);
		memberRepository.save(member);
	}

	@Override
	public List<Member> memberList() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}

	@Override
	public Member Login(String memberId, String memberPassword) {
		Member member = memberRepository.findByMemberId(memberId);
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			return member;
		}
		return null;
	}

	
}
