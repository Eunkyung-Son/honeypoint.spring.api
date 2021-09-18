package com.ek.honeypoint.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ek.honeypoint.member.model.vo.Member;

import org.springframework.web.servlet.AsyncHandlerInterceptor;

// 공지사항, 게시판을 메뉴바에서 클릭했을 때 로그인 되어 있지 않으면
// 요청을 수용하지 않는 인터셉터의 기능 구현하기

// 1. servlet-context.xml에 interceptor 등록
// 2. logger 등록 후 logger 객체 필드로 설정
// 3. 요청 전에 로그인 여부를 확인할 수 있는 전처리 메소드를 오버라이딩하여 구현함
public class LoginInterceptor implements AsyncHandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		// Member loginUser = (Member)session.getAttribute("loginUser");
		
		
		
		// if(loginUser == null) {
		// 	String rNo = request.getParameter("rNo");
			
		// 	session.setAttribute("msg", "로그인 후 이용하세요");
    //         response.sendRedirect("detail.do?rNo=" + rNo);
		// 	return false;	// 컨트롤러로 요청이 전달 되지 않게 false 리턴함
		// }
		
		return true;
	}
}
