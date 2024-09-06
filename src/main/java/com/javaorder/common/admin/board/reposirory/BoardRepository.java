package com.javaorder.common.admin.board.reposirory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaorder.common.admin.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	Optional<Board> findByBoardNoAndBoardPasswd(Long boardNo, String boardPasswd);
	Board findByBoardNo(Long boardNo);
}
