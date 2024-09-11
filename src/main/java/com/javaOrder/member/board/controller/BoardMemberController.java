package com.javaOrder.member.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;
import com.javaOrder.member.board.service.BoardMemberService;
import com.javaOrder.member.domain.Member;

import jakarta.servlet.http.HttpSession;
import lombok.Setter;

@Controller
@RequestMapping("/member/board/*")
public class BoardMemberController { 
	
	@Setter(onMethod_=@Autowired)
	private BoardMemberService service;
	
	
	@GetMapping("/boardList")
	public String boardList(Board board, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Board> boardList = service.list(pageRequestDTO);
		model.addAttribute("boardList", boardList);
		
		return "member/board/boardList";
	}
	
	@GetMapping("/insertForm")
	public String insertForm(Board board, HttpSession session, Model model) {
		Member member = (Member) session.getAttribute("member");
		if(member == null) {
			model.addAttribute("member", "로그인하지 않았습니다.");
			return "redirect:/";
		}else {
			model.addAttribute("member", member);
			return "member/board/insertForm";
		}
	}
	@PostMapping("/boardInsert")
	public String boardInsert(Board board) {
		service.boardInsert(board);
		return "redirect:/member/board/boardList";
	}
	
	@GetMapping("/{boardNo}")
	public String boardDetail(@PathVariable Long boardNo, Board board, Model model) {
		board.setBoardNo(boardNo);
		Board detail = service.boardDetail(board);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "member/board/boardDetail";
	}
		
	@PostMapping("/updateForm")
	public String updateForm(Board board, Model model) {
		Board  updateData= service.getBoard(board.getBoardNo());
		model.addAttribute("updateData", updateData);
		return "member/board/updateForm";
	}

	
	@PostMapping("/boardDelete")
	public String boardDelete(Board board) {
		service.boardDelete(board);
		return "redirect:/member/board/boardList";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute Board board, Model model) {		
		try {
			service.boardUpdate(board);			     
			return "redirect:/member/board/" + board.getBoardNo();
			
	  	}catch (IllegalArgumentException e){ 
	  		model.addAttribute("updateData", board);
	  		model.addAttribute("error", e.getMessage());
	      return "member/board/updateForm"; 
	  	}
  	}

	@ResponseBody
	@PostMapping(value = "/pwdConfirm", produces = "text/plain; charset=UTF-8")
	public String pwdConfirm(Long boardNo, String passwd) {
		int result = service.pwdConfirm(boardNo, passwd);
		
		return (result == 1) ? "일치" : "불일치";
	}
}