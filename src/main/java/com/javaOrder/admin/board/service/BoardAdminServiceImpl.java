package com.javaOrder.admin.board.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.javaOrder.admin.board.domain.Board;
import com.javaOrder.admin.board.repository.BoardRepository;
import com.javaOrder.common.util.vo.PageRequestDTO;
import com.javaOrder.common.util.vo.PageResponseDTO;

import lombok.Setter;

@Service
public class BoardAdminServiceImpl implements BoardAdminService {

	@Setter(onMethod_=@Autowired)
	private BoardRepository repository;

	
///// BCryptPasswordEncoder를 사용하여 비밀번호를 암호화합니다.
//	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
////////// 왜 쓰자마자 에러가..
	
	
	
	@Override
	public List<Board> boardList(Board board) {
		List<Board> list = repository.findAll();
		return list;
	}
	
	@Override
	public PageResponseDTO<Board> list(PageRequestDTO pageRequestDTO){
	Pageable pageable = PageRequest.of(
				pageRequestDTO.getPage() -1,
				pageRequestDTO.getSize(), Sort.by("boardNo").descending());
		
		Page<Board> result = repository.findAll(pageable);
		List<Board> boardList = result.getContent().stream().collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		PageResponseDTO<Board> responseDTO = PageResponseDTO.<Board>withAll().dtoList(boardList).pageRequestDTO(pageRequestDTO).totalCount(totalCount).build();
		
		return responseDTO;
	}
	
	//생성시 비밀번호 암호화 기능 추가해야함 BCrypt 안됨 추후시간이 있으면 적용
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