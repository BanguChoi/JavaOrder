package com.javaOrder.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.domain.Member;
import com.javaOrder.member.service.MemberService;



@Controller
@RequestMapping("javaOrder/admin/*")
//@RequiredArgsConstructor
public class AdminController {
	@Autowired
	private MemberService memberService;
	
	
	@GetMapping("/")
	public String adminMain(Model model) {
		return "/admin/dashBoard/dashBoard";
	}
	
	// 회원 목록 페이지
    @GetMapping("/member")
    public String getAllMembers(Model model) {
        // 모든 회원 데이터 조회
        List<Member> members = memberService.memberList();
        model.addAttribute("members", members);
        return "/admin/member/member"; // 회원 목록을 보여줄 뷰 이름
    }
}
