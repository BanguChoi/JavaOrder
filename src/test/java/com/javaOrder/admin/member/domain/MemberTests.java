package com.javaOrder.admin.member.domain;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.javaOrder.common.util.service.IdGenerationService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.repository.CartRepository;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.repository.MemberRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class MemberTests {
	
	@Setter(onMethod_ = @Autowired)
	private MemberRepository memberRepository;
	
	@Setter(onMethod_ = @Autowired)
	private CartRepository cartRepository;
	
	@Setter(onMethod_ = @Autowired)
	private IdGenerationService idGenerationService;
	
    @PersistenceContext
    private EntityManager entityManager;
	
	/**/
	@Test
	@Transactional
	public void createMemberTest() {

		String memberCode = idGenerationService.generateId("M", "member_seq",4);
		
		Member member = new Member();
	    member.setMemberCode(memberCode);
		member.setMemberName("테스트3");
		member.setMemberId("test3");
		member.setMemberPasswd("1234");
		member.setMemberEmail("test@naver.com");
		member.setMemberPhone("010-1234-5678");
		member.setMemberBirth(LocalDate.now());
		member.setMemberLast(null);
		member.setMemberDate(LocalDate.now());
		member.setMemberStatus("M");
		
		memberRepository.save(member);
	    entityManager.flush();
	    entityManager.clear();

	    Cart cart = new Cart();
	    cart.setMember(member);

	    cartRepository.save(cart);
	    entityManager.flush();

	}
	
}
