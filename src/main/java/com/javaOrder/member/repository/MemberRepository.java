package com.javaOrder.member.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
	public Member findByMemberId(String memberId);
	public Member findCartByMemberCode(String memberCode);
	public Member findByMemberCode(String memberCode);
	
	// 아이디 중복 확인
	public boolean existsByMemberId(String memberId);
	
	// 회원 번호 검색
	public Page<Member> findByMemberCode(String memberCode, Pageable pageable);
	// 검색어를 포함하는 이름 검색
	public Page<Member> findByMemberNameContaining(String keyword, Pageable pageable);
	// 검색어를 포함하는 주소 검색
	public Page<Member> findByMemberAddressContaining(String keyword, Pageable pageable);
	// 회원 번호 기준 검색
	public Page<Member> findByMemberId(Member member, Pageable pageable);
	// 회원 상태 기준 검색
	public Page<Member> findByMemberStatus(String status, Pageable pageable);
	// 회원 생일 기준 검색
	public Page<Member> findByMemberBirthBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	// 회원 마지막 로그인일 기준 검색
	public Page<Member> findByMemberLastBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	// 회원 가입일 기준 검색
	public Page<Member> findByMemberDateBetween(LocalDateTime startDate, LocalDateTime endDate, Pageable pageable);
	
}