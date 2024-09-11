package com.javaOrder.admin.notices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaOrder.admin.domain.Admin;
import com.javaOrder.admin.notices.domain.Notices;
import com.javaOrder.admin.notices.service.NoticesAdminService;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;


@Controller
@RequestMapping("/admin/notices/*")
public class NoticesAdminController {

		@Setter(onMethod_=@Autowired)
		private NoticesAdminService service;
	
		
		@GetMapping("/noticesList")
		public String noticesList(Notices notices, PageRequestDTO pageRequestDTO, Model model) {
			PageResponseDTO<Notices> noticesList = service.list(pageRequestDTO);
			model.addAttribute("noticesList", noticesList);
			
			return "admin/notices/noticesList";
		}
		
		@GetMapping("/insertForm")
		public String insertForm(Notices notices, HttpSession session, Model model) {
			Admin admin = (Admin) session.getAttribute("admin");
			if(admin == null) {
				model.addAttribute("admin", "관리자가 로그인하지 않았습니다.");
				return "redirect:/admin/";
			}else {
				model.addAttribute("admin", admin);
				return "admin/notices/insertForm";
			}
		}
		
		@PostMapping("/noticesInsert")
		public String noticesInsert(Notices notices) {
			service.noticesInsert(notices);
			return "redirect:/admin/notices/noticesList";
		}
		
		@GetMapping("/{noticesNo}")
		public String noticesDetail(@PathVariable Long noticesNo, Notices notices, Model model) {
			notices.setNoticesNo(noticesNo);
			Notices detail = service.noticesDetail(notices);
			model.addAttribute("detail",detail);
			
			String newLine = System.getProperty("line.separator").toString();
			model.addAttribute("newLine", newLine);
			return "admin/notices/noticesDetail";
		}
			
		@PostMapping("/updateForm")
		public String updateForm(Notices notices, Model model) {
			Notices updateData = service.getNotices(notices.getNoticesNo());
			model.addAttribute("updateData", updateData);
			return "admin/notices/updateForm";
		}
		
		@PostMapping("/noticesUpdate")
		public String noticesUpdate(Notices notices) {
			service.noticesUpdate(notices);
			return "redirect:/admin/notices/"+notices.getNoticesNo();//이동이 안될시 확인
		}
		
		
		@PostMapping("/noticesDelete")
		public String noticesDelete(Notices notices) {
			service.noticesDelete(notices);
			return "redirect:/admin/notices/noticesList"; //이동이 안될시 확인 성공
		}
		
}
