package com.javaOrder.admin.reply.service;

import java.util.List;

import com.javaOrder.admin.reply.domain.Reply;

public interface ReplyService {
	public List<Reply> replyList(Long boardNo);
	public Reply replyInsert(Reply reply );
	public Reply replyUpdate(Reply reply);
	public void replyDelete(Long replyId);
}
