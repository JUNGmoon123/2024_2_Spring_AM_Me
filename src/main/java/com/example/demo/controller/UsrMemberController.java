package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrMemberController {

	@Autowired
	private Rq rq;

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public String doLogout(HttpServletRequest req) {
		Rq rq = (Rq) req.getAttribute("rq");

		if (!rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그아웃 상태입니다");
		}

		rq.logout();

		return Ut.jsReplace("S-1", "로그아웃 되었습니다", "/");
	}

	@RequestMapping("/usr/member/login")
	public String showLogin(HttpServletRequest req) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		return "usr/member/login";
	}

	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public String doLogin(HttpServletRequest req, String loginId, String loginPw, String afterLoginUri) {

		Rq rq = (Rq) req.getAttribute("rq");

		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 함");
		}

		if (Ut.isNullOrEmpty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
		}

		Member member = memberService.getMemberByLoginId(loginId);

		if (member == null) {
			return Ut.jsHistoryBack("F-3", Ut.f("%s(은)는 존재하지 않는 아이디입니다", loginId));
		}

		if (member.getLoginPw().equals(loginPw) == false) {
			return Ut.jsHistoryBack("F-4", Ut.f("비밀번호가 일치하지 않습니다"));
		}

		rq.login(member);
		//jsp쪽 detail에서 좋아요,싫어요 시 로그인판단하고, afteruri는 원래목적인 좋아요, 싫어요를 하기위해
		//기존 페이지로 다시 보낼려고 가져왔다, Ut.replace로 웹페이지를 이동시킴.
		if (afterLoginUri.length() > 0) {
			return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다", member.getNickname()), afterLoginUri);
		}

		return Ut.jsReplace("S-1", Ut.f("%s님 환영합니다", member.getNickname()), "/");
	}

	@RequestMapping("/usr/member/join")
	public String showJoin(HttpServletRequest req) {

		return "usr/member/join";
	}

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public String doJoin(HttpServletRequest req, String loginId, String loginPw, String name, String nickname,
			String cellphoneNum, String email) {
		Rq rq = (Rq) req.getAttribute("rq");
		if (rq.isLogined()) {
			return Ut.jsHistoryBack("F-A", "이미 로그인 상태입니다");
		}

		if (Ut.isNullOrEmpty(loginId)) {
			return Ut.jsHistoryBack("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return Ut.jsHistoryBack("F-2", "비밀번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(name)) {
			return Ut.jsHistoryBack("F-3", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(nickname)) {
			return Ut.jsHistoryBack("F-4", "닉네임을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return Ut.jsHistoryBack("F-5", "전화번호를 입력해주세요");

		}
		if (Ut.isNullOrEmpty(email)) {
			return Ut.jsHistoryBack("F-6", "이메일을 입력해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return Ut.jsHistoryBack(joinRd.getResultCode(), joinRd.getMsg());
		}

		Member member = memberService.getMember(joinRd.getData1());

		return Ut.jsReplace(joinRd.getResultCode(), joinRd.getMsg(), "../member/login");
	}
}