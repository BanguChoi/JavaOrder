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
	public void createMember(Member member) {
		String memberCode = idGenerationService.generateId("M", "member_seq");
		member.setMembercode(memberCode);
		memberRepository.save(member);
	}

	@Override
	public List<Member> memberList(Member member) {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}

}
