package com.javaOrder.admin.reply.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaOrder.admin.domain.Admin;
import com.javaOrder.admin.reply.domain.Reply;
import com.javaOrder.admin.reply.service.ReplyService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController // if @Controller -> 각 메서드에 @ResponseBody 명시 필요
@RequestMapping("/replys")
@RequiredArgsConstructor
@Slf4j
public class ReplyController {

	private final ReplyService replyService;
	
	@GetMapping(value="/all/{boardNo}", produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<Reply> replyList(@PathVariable("no") Reply reply){
	public List<Reply> replyList(@PathVariable Long boardNo){		
		List<Reply> replyList = replyService.replyList(boardNo);

		return replyList;
	}
	
	@PostMapping(value="/replyInsert", consumes="application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public Reply replyInsert(@RequestBody Reply reply, HttpSession session) {
		log.info(reply.toString());
		Admin admin = (Admin) session.getAttribute("admin");
		if(admin == null) {
			return null;
		}else {
			reply.setAdmin(admin);
			Reply result = replyService.replyInsert(reply);
			if(result!=null)
				return result;
			else {
				return null;
			}
		}
	}
	
	@PutMapping(value="/{replyId}", consumes="application/json", produces=MediaType.APPLICATION_JSON_VALUE)
	public Reply replyUpdate(@PathVariable Long replyId, @RequestBody Reply reply) {
		reply.setReplyId(replyId);
		Reply result = replyService.replyUpdate(reply);
		return result;
	}
	
	@DeleteMapping(value="/{replyId}", produces=MediaType.TEXT_PLAIN_VALUE)
	public void replyDelete(@PathVariable Long replyId) {
		replyService.replyDelete(replyId);
	}
}
