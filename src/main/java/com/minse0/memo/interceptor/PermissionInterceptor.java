package com.minse0.memo.interceptor;

import java.io.IOException;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class PermissionInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(
			HttpServletRequest request
			, HttpServletResponse response
			, Object handler) throws IOException {
		
		
		HttpSession session = request.getSession();
		
		Long userId = (Long) session.getAttribute("userId");
		
		// /post/list-view
		String uri = request.getRequestURI();
		
		
		if(userId == null) {
			// 로그인이 안된경우 리스트 페이지, 글작성 페이지, 상세 페이지를 접근하지 못하도록 한다.
			// /post 로 시작하는 url path
			if(uri.startsWith("/post")) {
				
				// 그 경우 로그인 페이지로 다시 요청하도록 한다.
				// 로그인 페이지 리다이렉트 정보를 response에 담기
				response.sendRedirect("/user/login-view");
				
				// 요청이 더 이상 진행되지 않도록 한다.
				return false;
			}
			// 그 경우 로그인 페이지로 다시 요청하도록 한다.
			
		} else {
			// 로그인이 된 경우 로그인 페이지, 회원가입 페이지 접근하지 못하도록 한다.
			// /user 로 시작하는 url path
			if(uri.startsWith("/user")) {
				// 그 경우 리스트 페이지로 다시 요청하도록 한다.
				// 리스트 페이지로 리다이렉트 한다.
				response.sendRedirect("/post/list-view");
				return false;
			}
			// 그 경우 리스트 페이지로 다시 요청하도록 한다.
			
			
		}
		
		return true;
	}
	
}
