package com.javaorder.board.service;

import java.util.List;

import com.javaorder.board.domain.Board;

public interface BoardService {

	List<Board> boardList(Board board);
	void boardInsert(Board board);
	Board boardDetail(Board board);
	Board getBoard(Long boardNo);
	void boardUpdate(Board board);
	void boardDelete(Board board);
}