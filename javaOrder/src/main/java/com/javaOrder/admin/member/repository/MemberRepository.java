package com.javaOrder.admin.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.admin.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, String>{
}