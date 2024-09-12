package com.javaOrder.member.board.service;

import java.util.List;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

public interface BoardMemberService {

	List<Board> boardList(Board board);
	PageResponseDTO<Board> list(PageRequestDTO pageRequestDTO);	
	void boardInsert(Board board);
	Board boardDetail(Board board);
	Board getBoard(Long boardNo);
	void boardUpdate(Board board);
	void boardDelete(Board board);
	int pwdConfirm(Long boardNo, String passwd);
}