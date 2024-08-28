package com.javaOrder.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.vo.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	public Member findByMemberId(String memberId);
}