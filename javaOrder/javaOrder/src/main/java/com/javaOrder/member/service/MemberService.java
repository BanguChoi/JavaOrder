package com.javaOrder.member.service;

import java.util.List;

import com.javaOrder.member.domain.Member;

public interface MemberService {
	public void createMember(Member member);
	public List<Member> memberList(Member member);
}