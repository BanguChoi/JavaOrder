package com.javaorder.board.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaorder.board.domain.Board;
import com.javaorder.board.repository.BoardRepository;

import lombok.Setter;

@Service
public class BoardServiceImpl implements BoardService {

	@Setter(onMethod_=@Autowired)
	private BoardRepository repository;

	@Override
	public List<Board> boardList(Board board) {
		List<Board> list = repository.findAll();
		return list;
	}

	@Override
	public void boardInsert(Board board) {
		repository.save(board);		
	}

	@Override
	public Board boardDetail(Board board) {
		Optional<Board> boardOptional = repository.findById(board.getBoardNo());
		Board Detail = boardOptional.get();
		return Detail;
	}

	@Override
	public Board getBoard(Long boardNo) {
		
		return null;
	}

	@Override
	public void boardUpdate(Board board) {
		Optional<Board> boardOptional = repository.findById(board.getBoardNo());
		Board updateBoard = boardOptional.get();
		updateBoard.setBoardTitle();
		
		
	}

	@Override
	public void boardDelete(Board board) {
		// TODO Auto-generated method stub
		
	}
	 
}
