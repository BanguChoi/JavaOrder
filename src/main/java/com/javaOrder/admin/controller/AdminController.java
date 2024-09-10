package com.javaOrder.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaOrder.admin.domain.Admin;
import com.javaOrder.admin.service.AdminService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.domain.Member;
import com.javaOrder.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;



@Controller
@RequestMapping("/admin/*")
@RequiredArgsConstructor
public class AdminController {
	
	private final MemberService memberService;
	private final AdminService adminService;
	
	// 관리자 메인 페이지 (로그인 페이지)
	@GetMapping("/")
	public String adminMain(Model model) {
		return "/admin/signin";
	}
	
	// 로그인 페이지
	@GetMapping("/admin/dashboard")
	public String adminDashboard() {
		return "/admin/dashboard/dashboard";
	}
	
	// 로그인 처리
	@PostMapping("/signin")
	public String adminSignIn(@RequestParam String adminId,@RequestParam String adminPassword, HttpSession session, Model model) {
		Admin admin = adminService.Login(adminId, adminPassword); 
		
		if(admin != null && admin.getAdminPasswd().equals(adminPassword)) {
			session.setAttribute("admin", admin);
			return "redirect:/admin/dashboard"; // 성공할 경우 대시보드 페이지로
		} else {
			model.addAttribute("error","아이디 또는 비밀번호가 일치하지 않습니다.");
			return "admin/signin"; // 실패할 경우 관리자 로그인 페이지
		}
	}
	
	// 로그아웃 처리
	@GetMapping("/signout")
	public String signout(HttpSession session) {
		session.invalidate();
		return "redirect:/admin/";
	}
		
	// 회원 목록 페이지
    @GetMapping("/memberList")
    public String getAllMembers(PageRequestDTO pageRequestDTO, Model model) {
    	PageResponseDTO<Member> memberList = memberService.memberList(pageRequestDTO); 
        model.addAttribute("memberList", memberList);
        return "admin/member/member"; // 회원 목록을 보여줄 뷰 이름
    }
}
