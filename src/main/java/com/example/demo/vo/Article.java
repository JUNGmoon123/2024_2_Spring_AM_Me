package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private String title;
	private String body;
	private String extra__writer;
	
	//조회수
	private int hitCount;
	//좋아요,싫어요,합
	private String extra__sumReactionPoint;
	private String extra__goodReactionPoint;
	private String extra__badReactionPoint;

	
	private boolean userCanModify;
	private boolean userCanDelete;
}