package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutController
 */
@WebServlet("/logout.me")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //로그아웃 => 세션에 저장된 기록 초기화 (폼 가져오는 것과 똑같이 값을 받을 필요가없다.)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//session을 사용하기 위해서 request의 도움을 받아야 한다.(상위버전의 값을 가져오기 위해서는 하위 버전의 도움을 받아야 한다.)
		//로그아웃 요청 처리 => session 만료 시키기 == 세션을 null로 만들어서 무효화 시키기
		
		//HttpSession session = request.getSession(); // getSession의 자료형 => HttpSession
		//session.invalidate(); // 세션 초기화 => 저장된 세션 모두 초기화 시키기??
		
		//위 두줄 한줄로 하기 
		request.getSession().invalidate();
		
		//응답페이지 => index.jsp 페이지 => url재요청(한번이라도 본 화면이여서 가능)
		response.sendRedirect(request.getContextPath());  //request.getContextPath() =>프로젝트 Path만 가져온다.
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
