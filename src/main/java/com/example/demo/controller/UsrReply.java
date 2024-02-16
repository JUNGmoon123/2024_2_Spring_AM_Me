package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReplyService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

public class UsrReply {
	@RequestMapping("/usr/reply/doReply")
	@ResponseBody
	public String doReply(HttpServletRequest req, String content) {

		Rq rq = (Rq) req.getAttribute("rq");
		
		if(rq.isLogined() == false) {
			return Ut.jsHistoryBack("F-1", "로그인하고 이용하세요");
		}
		
		if (Ut.isNullOrEmpty(content)) {
			return Ut.jsHistoryBack("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = ReplyService.writeReply(rq.getLoginedMemberId(), body);

		int id = (int) writeArticleRd.getData1();

		Article article = ReplyService.getArticle(id);

		return Ut.jsReplace(writeArticleRd.getResultCode(), writeArticleRd.getMsg(), "../article/detail?id=" + id);

	}
}
