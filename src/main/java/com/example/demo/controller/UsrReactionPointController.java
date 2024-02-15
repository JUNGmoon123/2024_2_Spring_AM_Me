package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ReactionPointService;
import com.example.demo.util.Ut;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrReactionPointController {

	@Autowired
	private Rq rq;

	@Autowired
	private ReactionPointService reactionPointService;

	// 액션 메서드

	@RequestMapping("/usr/reactionPoint/doGoodReaction")
	@ResponseBody
//	public ResultData doGoodReaction(String relTypeCode, int relId, String replaceUri)
	//기존 ResultData이던걸 Object로 바꿈, 이유로는 추천을 실행하고나서 detail쪽으로 다시 돌아가기 위해서 url을 넘겨받는걸 실행하려고.
	//jsReplace로 들어가면 밑에 return Script언어로 location.replace명령어 사용됨. 싫어요도 마찬가지.
	public Object doGoodReaction(String relTypeCode, int relId, String replaceUri){

		ResultData usersReactionRd = reactionPointService.usersReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		int usersReaction = (int) usersReactionRd.getData1();

		if (usersReaction == 1) {
			ResultData rd = reactionPointService.deleteGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return Ut.jsReplace("S-1", "좋아요 취소", replaceUri);
//					ResultData.from("S-1", "좋아요 취소");
		} else if (usersReaction == -1) {
			ResultData rd = reactionPointService.deleteBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			rd = reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return Ut.jsReplace("S-2", "싫어요 눌렀잖어", replaceUri);
//					ResultData.from("S-2", "싫어요 눌렀잖어");
		}

		ResultData reactionRd = reactionPointService.addGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		if (reactionRd.isFail()) {
			return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
		}

		return  Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
//				ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
	}

	@RequestMapping("/usr/reactionPoint/doBadReaction")
	@ResponseBody
//	public ResultData doBadReaction(String relTypeCode, int relId, String replaceUri)
	public Object doBadReaction(String relTypeCode, int relId, String replaceUri){

		ResultData usersReactionRd = reactionPointService.usersReaction(rq.getLoginedMemberId(), relTypeCode, relId);

		int usersReaction = (int) usersReactionRd.getData1();

		if (usersReaction == -1) {
			ResultData rd = reactionPointService.deleteBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return Ut.jsReplace("S-1", "싫어요 취소", replaceUri);
//			ResultData.from("S-1", "싫어요 취소");
		} else if (usersReaction == 1) {
			ResultData rd = reactionPointService.deleteGoodReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			rd = reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);
			return Ut.jsReplace("S-2", "좋아요 눌렀잖어", replaceUri);
//			ResultData.from("S-2", "좋아요 눌렀잖어");
		}

		ResultData reactionRd = reactionPointService.addBadReactionPoint(rq.getLoginedMemberId(), relTypeCode, relId);

		if (reactionRd.isFail()) {
			return ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
		}

		return Ut.jsReplace(reactionRd.getResultCode(), reactionRd.getMsg(), replaceUri);
//				ResultData.from(reactionRd.getResultCode(), reactionRd.getMsg());
	}

}