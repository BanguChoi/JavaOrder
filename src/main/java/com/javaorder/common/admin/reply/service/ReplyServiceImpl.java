package com.javaorder.common.admin.reply.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.javaorder.common.admin.reply.domain.Reply;
import com.javaorder.common.admin.reply.repository.ReplyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	//생성자주입
	private final ReplyRepository replyRepository;
	
	@Override
	public List<Reply> replyList(Reply reply) {
		List<Reply> replyList = replyRepository.findByBoardNo(reply.getBoard().getBoardNo());
		
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
		Reply updateReply = replyOptional.get();
		updateReply.setReplyContent(reply.getReplyContent());
		Reply result = replyRepository.save(updateReply);
		return result;
	}
	
	@Override
	public void replyDelete(Reply reply) {
		replyRepository.deleteById(reply.getReplyId());
	}
}
