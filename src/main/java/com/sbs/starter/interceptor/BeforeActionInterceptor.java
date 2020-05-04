package com.sbs.starter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sbs.starter.dto.Member;
import com.sbs.starter.service.MemberService;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	//모든 액션 수행 전에 필수 정보를 미리 세팅해 제공한다.
	@Autowired
	MemberService memberService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//테스트 용: main.jsp으로 보내서 확인하기 위한 것: 확인 됨: cy200504
		//request.setAttribute("testNumber",1);
		
		boolean isLogined = false;
		long loginedMemberId = 0;
		Member loginedMember = null;
		
		HttpSession session = request.getSession();
		if ( session.getAttribute("loginedMemberId") != null ) {
			isLogined = true;
			loginedMemberId = (long)session.getAttribute("loginedMemberId");
			loginedMember = memberService.getOne(loginedMemberId);
		}
		
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("loginedMember", loginedMember);
		
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
