package com.javaOrder.member.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.javaOrder.member.service.MemberService;
import com.javaOrder.member.vo.Member;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/javaOrder/member/*")
@RequiredArgsConstructor
public class MemberControll {
	
	private final MemberService memberService;
	
	// 로그인 페이지
	@GetMapping("/signin")
	public String memberSignInPage() {
		// 로그인 페이지 뷰로 이동
		return "/javaOrder/member/signin"; 
	}
	
	// 로그인 처리
	@PostMapping("/signin")
	public String memberSignIn(String memberId, String memberPassword, HttpSession session, Model model) {
		Member member = memberService.Login(memberId, memberPassword);
		
		if(member != null && member.getMemberPasswd().equals(memberPassword)) {
			session.setAttribute("member", member);
			return "redirect:/javaOrder/main"; // 넘겨줄 화면
		}else {
			model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/javaOrder/member/signin"; // 실패할 경우 로그인 페이지로
		}
	}
	
	// 로그아웃
	@GetMapping("/signout")
	public String signout(HttpSession session) {
	    session.invalidate(); // 세션 무효화
	    return "redirect:/javaOrder/main"; // 메인 페이지로 리다이렉트
	}
	
	// 회원가입 페이지
	@GetMapping("/signup")
	public String memberSignUp() {
		return "/javaOrder/member/signup";
	}
	
	// 회원가입 처리
	@PostMapping("/signup")
	public RedirectView signup(Member member, Model model) {
		try {
			memberService.insertMemberCode(member);
			return new RedirectView("/javaOrder/member/signin");
		}catch (Exception e) {
			model.addAttribute("error", "회원가입에 실패했습니다. 다시 시도해 주세요.");
			return new RedirectView("/javaOrder/member/signup");
		}
	}
	
	// 회원 mypage
	@GetMapping("/mypage")
	public String myPage(HttpSession session, Model model) {
	    Member member = (Member) session.getAttribute("member");
	    if (member == null) {
	        // 로그인하지 않은 경우 로그인 페이지로 리다이렉트
	        return "redirect:/javaOrder/member/signin";
	    }
	    model.addAttribute("member", member);
	    return "/javaOrder/member/mypage";
	}
	
	// 회원 목록 페이지
    @GetMapping("/admin/member")
    public String getAllMembers(Model model) {
        // 모든 회원 데이터 조회
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);
        return "/javaOrder/admin/memberList"; // 회원 목록을 보여줄 뷰 이름
    }
}
