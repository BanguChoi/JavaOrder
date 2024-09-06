package com.javaOrder.admin.board.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaOrder.admin.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Optional<Board> findByBoardNoAndBoardPasswd(Long boardNo, String boardPasswd);
	Board findByBoardNo(Long boardNo);
}
