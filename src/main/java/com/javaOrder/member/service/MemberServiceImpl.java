package com.javaOrder.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.javaOrder.common.service.IdGenerationService;
import com.javaOrder.member.repository.MemberRepository;
import com.javaOrder.member.vo.Member;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	
	private final MemberRepository memberRepository;
	private final IdGenerationService idGenerationService;
	
	@Transactional
	@Override
	public void insertMemberCode(Member member) {
		String memberCode = idGenerationService.generateId("M", "member_seq");
		member.setMemberCode(memberCode);
		memberRepository.save(member);
	}
	
	// 회원 목록
	@Override
	public List<Member> memberList() {
		List<Member> memberList = memberRepository.findAll();
		return memberList;
	}
	
	// 회원 로그인
	@Override
	public Member Login(String memberId, String memberPassword) {
		Member member = memberRepository.findByMemberId(memberId);
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			return member;
		}
		return null;
	}
	
	// 회원 정보 수정
	@Transactional
    @Override
    public boolean updateMemberField(String memberCode, String fieldId, String newValue) {
        Member member = memberRepository.findByMemberCode(memberCode);
        if (member == null) {
            return false; // 회원이 존재하지 않으면 실패
        }

        // 필드에 따라 값 업데이트
        switch (fieldId) {
            case "memberName":
                member.setMemberName(newValue);
                break;
            case "memberEmail":
                member.setMemberEmail(newValue);
                break;
            case "memberPhone":
                member.setMemberPhone(newValue);
                break;
            case "memberAddress":
                member.setMemberAddress(newValue);
                break;
            default:
                return false; // 유효하지 않은 필드
        }

        memberRepository.save(member); // 변경 사항 저장
        return true;
    }
	
	@Override
    public Member getMemberByCode(String memberCode) {
        return memberRepository.findByMemberCode(memberCode);
    }
}
