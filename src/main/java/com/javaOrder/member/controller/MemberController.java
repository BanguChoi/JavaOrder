package com.javaOrder.member.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import com.javaOrder.member.domain.Member;
import com.javaOrder.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/javaOrder/member/*")
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
			return "redirect:/"; // 넘겨줄 화면
		}else {
			model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
			return "/member/signin"; // 실패할 경우 로그인 페이지로
		}
	}
	
	// 로그아웃
	@GetMapping("/signout")
	public String signout(HttpSession session) {
	    session.invalidate(); // 세션 무효화
	    return "redirect:/"; // 메인 페이지로 리다이렉트
	}
	
	// 회원가입 페이지
	@GetMapping("/signup")
	public String memberSignUp() {
		return "/member/signup";
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
	    return "/member/mypage";
	}
	
	// 회원 정보 처리
	@PostMapping("/javaOrder/member/updateField")
    @ResponseBody
    public Map<String, Object> updateField(
            @RequestParam String memberCode,
            @RequestParam String fieldId,
            @RequestParam String newValue,
            HttpSession session) {

        Map<String, Object> response = new HashMap<>();
        boolean success = memberService.updateMemberField(memberCode, fieldId, newValue);

        if (success) {
            // 세션에서 현재 회원 정보 가져오기
            Member updatedMember = memberService.getMemberByCode(memberCode);
            session.setAttribute("member", updatedMember); // 세션에 새 정보 저장
        }

        response.put("success", success);
        return response;
    }
	
	// 비밀번호 변경 페이지
	@GetMapping("/editPasswd")
	public String getMethodName() {
		return "/member/editPasswd";
	}
	
	// 비밀번호 변경
	@PostMapping("/verifyPassword")
	@ResponseBody
	public Map<String, Object> verifyPassword(
	        @RequestParam("currentPassword") String currentPassword,
	        HttpSession session) {

	    Map<String, Object> response = new HashMap<>();
	    Member member = (Member) session.getAttribute("member");

	    if (member != null && member.getMemberPasswd().equals(currentPassword)) {
	        response.put("success", true);
	    } else {
	        response.put("success", false);
	        response.put("message", "현재 비밀번호가 일치하지 않습니다.");
	    }

	    return response;
	}
	
	// 현재 비밀번호 확인
	@PostMapping("/changePassword")
	@ResponseBody
	public Map<String, Object> changePassword(
	        @RequestParam String currentPassword,
	        @RequestParam String newPassword,
	        HttpSession session) {
	    Map<String, Object> response = new HashMap<>();
	    Member member = (Member) session.getAttribute("member");
	    
	    if (member != null && memberService.validateCurrentPassword(member.getMemberCode(), currentPassword)) {
	        if (memberService.changePassword(member.getMemberCode(), currentPassword, newPassword)) {
	            response.put("success", true);
	        } else {
	            response.put("success", false);
	            response.put("message", "비밀번호 변경에 실패했습니다.");
	        }
	    } else {
	        response.put("success", false);
	        response.put("message", "현재 비밀번호가 올바르지 않습니다.");
	    }
	    
	    return response;
	}
}
	/*
	// 회원 목록 페이지
    @GetMapping("/admin/member")
    public String getAllMembers(Model model) {
        // 모든 회원 데이터 조회
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);
        return "/admin/memberList"; // 회원 목록을 보여줄 뷰 이름
    }*/
}
