package com.javaorder.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaorder.board.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

}
