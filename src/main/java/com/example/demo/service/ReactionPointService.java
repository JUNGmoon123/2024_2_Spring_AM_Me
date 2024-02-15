package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.ReactionPointRepository;
import com.example.demo.vo.ResultData;

@Service
public class ReactionPointService {

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ReactionPointRepository reactionPointRepository;

	public ReactionPointService(ReactionPointRepository reactionPointRepository) {
		this.reactionPointRepository = reactionPointRepository;
	}

	public int usersReaction(int loginedMemberId, String relTypeCode, int relId) {

		// 로그인 x
		if (loginedMemberId == 0) {
			return -2;
		}

		return reactionPointRepository.getSumReactionPoint(loginedMemberId, relTypeCode, relId);
	}

	public ResultData increaseReactionPoint(int loginedMemberId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.increaseReactionPoint(loginedMemberId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-2", "좋아요 실패");
		}

		switch (relTypeCode) {
		case "article":
			articleService.increaseGoodReactionPoint(relId);
			break;
		}

		return ResultData.from("S-1", "좋아요!");
	}
	// increaseReactionPoint는 어차피 좋아요, 싫어요 2개다 공통인 부분같아서 똑같이 사용함
	// 게시글에 좋아요, 싫어요를 누른 회원을 저장해서 중복해서 못누르게 하는듯.
	public ResultData decreaseReactionPoint(int loginedMemberId, String relTypeCode, int relId) {

		int affectedRow = reactionPointRepository.increaseReactionPoint(loginedMemberId, relTypeCode, relId);

		if (affectedRow != 1) {
			return ResultData.from("F-2", "싫어요 실패");
		}

		switch (relTypeCode) {
		case "article":
			articleService.decreaseBadReactionPoint(relId);
			break;
		}

		return ResultData.from("S-1", "싫어요!");
	}


}