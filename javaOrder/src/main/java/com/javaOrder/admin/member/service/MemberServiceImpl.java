package com.javaOrder.admin.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.admin.member.domain.Member;
import com.javaOrder.admin.member.repository.MemberRepository;
import com.javaOrder.common.service.IdGenerationService;
import com.javaOrder.member.cart.domain.Cart;
import com.javaOrder.member.cart.repository.CartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final IdGenerationService idGenerationService;
	private final CartRepository cartRepository;
	
	@Transactional
	@Override
	public void insertMemberCode(Member member) {
		String memberCode = idGenerationService.generateId("M", "member_seq");
		member.setMemberCode(memberCode);
		memberRepository.save(member);
		
		/* 회원가입시 카트 자동생성 */
        Cart cart = new Cart();
        cart.setMember(member);   
        cartRepository.save(cart);
	}

	@Override
	public List<Member> memberList() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}

	@Override
	public Member Login(String memberId, String memberPassword) {
		Member member = memberRepository.findByMemberId(memberId);
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			return member;
		}
		return null;
	}



}