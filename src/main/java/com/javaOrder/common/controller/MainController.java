package com.javaOrder.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/javaOrder/*")
public class MainController {
    
    // 메인 페이지
    @GetMapping("/main")
    public String mainPage(HttpSession session, Model model) {
        Member member = (Member) session.getAttribute("member");
        model.addAttribute("member", member);
        return "/javaOrder/main";
    }
    
    // 관리자 메인 페이지
    @GetMapping("/admin")
    public String adminMain() {
        return "admin/main";
    }
}
