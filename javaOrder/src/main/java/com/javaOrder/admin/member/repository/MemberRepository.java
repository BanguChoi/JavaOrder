package com.javaOrder.admin.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.admin.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	Optional<Member> findByMemberCode(String memberCode);
	public Member findByMemberId(String memberId);
	Member findByMemberCode(Member memberCode);
	Member findCartByMemberCode(String memberCode);
	
}