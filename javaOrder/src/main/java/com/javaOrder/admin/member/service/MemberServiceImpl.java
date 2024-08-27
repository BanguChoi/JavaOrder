package com.javaOrder.admin.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.admin.member.domain.Member;
import com.javaOrder.admin.member.repository.MemberRepository;
import com.javaOrder.common.service.IdGenerationService;

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