package com.javaOrder.admin.member.service;

import java.util.List;

import com.javaOrder.admin.member.domain.Member;

public interface MemberService {
	public void createMember(Member member);
	public List<Member> memberList(Member member);
}