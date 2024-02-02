package com.example.demo.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//기존에 controller로 바로 들어가던 요청사항을 여기로 한번 빼앗아서 처리?모음? 하게 하고 Controller로 넘겨준다.
@Component
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object handler) throws Exception {
		//여기서 new를 해주고 set을 설정해주면 기존에 Rq를 쓰던곳에서는 (Rq) req.getAttribute("rq");쓰이게된다
		Rq rq = new Rq(req);

		req.setAttribute("rq", rq);

		return HandlerInterceptor.super.preHandle(req, resp, handler);
	}
}