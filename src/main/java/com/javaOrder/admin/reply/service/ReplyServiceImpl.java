package com.javaOrder.admin.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaOrder.admin.reply.domain.Reply;
import com.javaOrder.admin.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	//생성자주입
	private final ReplyRepository replyRepository;
	
	
	@Override
	public List<Reply> replyList(Long boardNo) {
		List<Reply> replyList = replyRepository.findByBoardNo(boardNo);		
		return replyList;
	}
	
	@Override
	public Reply replyInsert(Reply reply) {		
		Reply result = replyRepository.save(reply);
		return result;
	}
	

	@Override
	public Reply replyUpdate(Reply reply) {
		Optional<Reply> replyOptional = replyRepository.findById(reply.getReplyId());
		Reply replyUpdate = replyOptional.get();
		reply.setReplyId(reply.getReplyId());
		replyUpdate.setReplyContent(reply.getReplyContent());
		Reply result = replyRepository.save(replyUpdate);
		return result;
	}
	
	@Override
	public void replyDelete(Long replyId) {
		replyRepository.deleteById(replyId);
	}
}
