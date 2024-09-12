package com.javaOrder.member.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.admin.board.repository.BoardRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Service
public class BoardMemberServiceImpl implements BoardMemberService {

	@Setter(onMethod_=@Autowired)
	private BoardRepository repository;
	
	@Override
	public List<Board> boardList(Board board) {
		List<Board> list = repository.findAll();
		return list;
	}
	
	@Override
	public PageResponseDTO<Board> list(PageRequestDTO pageRequestDTO){
	Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() -1, //1페이지가 0 부터 시작
				pageRequestDTO.getSize(), Sort.by("boardNo").descending());
		
		Page<Board> result = repository.findAll(pageable);
		List<Board> boardList = result.getContent().stream().collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		PageResponseDTO<Board> responseDTO = PageResponseDTO.<Board>withAll().dtoList(boardList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
		
		return responseDTO;
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
	public Board getBoard(@PathVariable Long boardNo) {
		Optional<Board> boardOptional = repository.findById(boardNo);
		Board updateData = boardOptional.orElseThrow();
		return updateData;
	}

	
	@Override
	public void boardUpdate(Board board) {
		Board updateBoard = repository.findByBoardNo(board.getBoardNo());
		updateBoard.setBoardTitle(board.getBoardTitle());
		updateBoard.setBoardContent(board.getBoardContent());			        
 
        repository.save(updateBoard);        
	}	
		
	@Override
	public int pwdConfirm(Long boardNo, String passwd) {
		Board pwdConfirm = repository.findByBoardNo(boardNo);		
					
		if(pwdConfirm.getBoardPasswd().equals(passwd)) {				  
			return 1;
		 }else{
			return 0;
		  }
	}
	
	@Override
	public void boardDelete(Board board) {
		repository.deleteById(board.getBoardNo());
	}

}
