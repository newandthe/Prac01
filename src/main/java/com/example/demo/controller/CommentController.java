package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.VO.Comment;
import com.example.demo.service.CommentService;

@Controller
public class CommentController {
	
	@Autowired
	CommentService commentservice;
	
	@PostMapping("/commentwrite")
	@ResponseBody
	public String commentWrite(@RequestParam("bbsseq") int bbsseq, 
							   @RequestParam("content") String content,
							   @RequestParam("memberseq") int memberseq){
		
//		System.out.println("bbsseq: " + bbsseq);
//		System.out.println("cmtcontent: " + cmtcontent);
//		System.out.println("memberseq: " + memberseq);
		
		if((Integer)bbsseq == null || bbsseq == 0) {
			return "isErrorBbsseq";
		} else if(content == null || content.trim() == "") {
			return "contentblank";
		} else if((Integer)memberseq == null || memberseq == 0) {
			return "isErrorMemberseq";
		}
		
		Comment comment = new Comment();
		comment.setBbsseq(bbsseq);
		comment.setContent(content);
		comment.setMemberseq(memberseq);
		
		if(commentservice.commentWrite(comment)) {
			
			
			return "success";
					
		} else {
			
			return "fail";
		}
	}
	
	@PostMapping("/commentdel")
	@ResponseBody
	public String commentDel(@RequestParam("commentseq") int commentseq) {
//		System.out.println(commentseq);
		
		if((Integer)commentseq == null || commentseq == 0) {
			return "isErrorCommentseq";
		}
		
		if(commentservice.commentDel(commentseq)) {
			return "success";
		} else {
			return "fail";
		}
	}
	
	@PostMapping("/commentedit")
	@ResponseBody
	public String commentEdit(@RequestParam("commentseq") int commentseq,
							  @RequestParam("content") String content) {
//		System.out.println("commentseq: " + commentseq);
//		System.out.println("content: " + content);
		
		if((Integer)commentseq == null || commentseq == 0) {
			return "isErrorCommentseq";
		} else if (content == null || content.trim() == "") {
			return "isErrorContent";
		}
		
		if(commentservice.commentEdit(commentseq, content)) {
			return "success";
		}else {
			return "fail";
		}
	}
	
	

}
