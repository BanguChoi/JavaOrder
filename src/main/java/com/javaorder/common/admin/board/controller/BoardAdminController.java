package com.javaorder.common.admin.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaorder.common.admin.board.domain.Board;
import com.javaorder.common.admin.board.service.BoardAdminService;
import com.javaorder.common.util.vo.PageRequestDTO;
import com.javaorder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Controller
@RequestMapping("/adminBoard/*")
public class BoardAdminController {
	

	@Setter(onMethod_=@Autowired)
	private BoardAdminService service;
	
	
	@GetMapping("/boardList")
	public String boardList(Board board, PageRequestDTO pageRequestDTO, Model model) {
		PageResponseDTO<Board> boardList = service.list(pageRequestDTO);
		model.addAttribute("boardList", boardList);
		
		return "admin/board/boardList";
	}
	
	@GetMapping("/insertForm")
	public String insertForm(Board board) {
		return "admin/board/insertForm";
	}
	@PostMapping("/boardInsert")
	public String boardInsert(Board board) {
		service.boardInsert(board);
		return "redirect:/adminBoard/boardList";
	}
	
	@GetMapping("/{boardNo}")
	public String boardDetail(@PathVariable Long boardNo, Board board, Model model) {
		board.setBoardNo(boardNo);
		Board detail = service.boardDetail(board);
		model.addAttribute("detail",detail);
		
		String newLine = System.getProperty("line.separator").toString();
		model.addAttribute("newLine", newLine);
		return "admin/board/boardDetail";
	}
		
	@PostMapping("/updateForm")
	public String updateForm(Board board, Model model) {
		Board  updateData= service.getBoard(board.getBoardNo());
		model.addAttribute("updateData", updateData);
		return "admin/board/updateForm";
	}

	
	@PostMapping("/boardDelete")
	public String boardDelete(Board board) {
		service.boardDelete(board);
		return "redirect:/adminBoard/boardList";
	}
	
	@PostMapping("/boardUpdate")
	public String boardUpdate(@ModelAttribute Board board, Model model) {		
		try {
			service.boardUpdate(board);			     
			return "redirect:/adminBoard/" + board.getBoardNo();
			
	  	}catch (IllegalArgumentException e){ 
	  		model.addAttribute("updateData", board);
	  		model.addAttribute("error", e.getMessage());
	      return "admin/board/updateForm"; 
	  	}
  	}

	@ResponseBody
	@PostMapping(value = "/pwdConfirm", produces = "text/plain; charset=UTF-8")
	public String pwdConfirm(Long boardNo, String passwd) {
		int result = service.pwdConfirm(boardNo, passwd);
		
		return (result == 1) ? "일치" : "불일치";
	}
}