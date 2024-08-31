package com.javaOrder.admin.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.admin.member.domain.Member;
import com.javaOrder.admin.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/member/*")
@RequiredArgsConstructor
public class MemberController {
	
	private final MemberService memberService;

	
	// 로그인 페이지
	@GetMapping("/signin")
	public String memberSignInPage() {
		// 로그인 페이지 뷰로 이동
		return "/member/signin"; 
	}
	
	// 로그인 처리
	@PostMapping("/signin")
	public String memberSignIn(String memberId, String memberPassword, HttpSession session, Model model) {
		Member member = memberService.Login(memberId, memberPassword);
		
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			session.setAttribute("member", member);
			return "redirect:/main"; // 넘겨줄 화면
		}else {
			model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/member/signin"; // 실패할 경우 로그인 페이지로
		}
	}
	
	@GetMapping("/signout")
	public String signout(HttpSession session) {
	    session.invalidate(); // 세션 무효화
	    return "redirect:/main"; // 메인 페이지로 리다이렉트
	}
	
	// 회원가입 페이지
	@GetMapping("/signup")
	public String memberSignUp() {
		return "/member/signup";
	}
	
	// 회원가입 -> 로그인페이지
	
	
	// 회원 mypage
	@GetMapping("/mypage")
	public String myPage(HttpSession session, Model model) {
	    Member member = (Member) session.getAttribute("member");
	    if (member == null) {
	        // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
	        return "redirect:/signin";
	    }
	    model.addAttribute("member", member);
	    return "/member/mypage";
	}
	
	// 회원 목록 페이지
    @GetMapping("/admin/member")
    public String getAllMembers(Model model) {
        // 모든 회원 데이터 조회
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);
        return "/admin/memberList"; // 회원 목록을 보여줄 뷰 이름
    }
}