package com.javaOrder.admin.member.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.admin.member.repository.MemberRepository;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.repository.CartRepository;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberTests {
	
	@Setter(onMethod_ = @Autowired)
	private MemberRepository memberRepository;
	
	@Setter(onMethod_ = @Autowired)
	private CartRepository cartRepository;
	
	@Test
	public void createMemberTest() {
		Member member = new Member();
		member.setMembercode("M0002");
		member.setMembername("김후추");
		member.setMemberid("react");
		member.setMemberpasswd("1234");
		member.setMemberemail("react@naver.com");
		member.setMemberphone("010-1234-5678");
		member.setMemberbirth(LocalDate.now());
		member.setMemberlast(null);
		member.setMemberdate(LocalDate.now());
		member.setMemberstatus("M");
		
		memberRepository.save(member);

	    Cart cart = new Cart();
	    cart.setMember(member);

	    cartRepository.save(cart);

	}
	
	
	/*
	@Test
	public void memberCountTest() {
		long memberCount = memberRepository.count();
		log.info(String.valueOf(memberCount));
	}
	*/
	
	
	
	
	
}
