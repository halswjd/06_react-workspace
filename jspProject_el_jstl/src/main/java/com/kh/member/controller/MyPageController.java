package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MyPageController
 */
@WebServlet("/myPage.me")
public class MyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyPageController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 마이페이지 폼 띄우기 => 값을 안받고 그냥 페이지 띄우는 과정
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//로그인 전에 url쳐서 직접 요청도 가능하긴 함 (마이페이지는 로그인 후에 사용할 수 있는 기능 => url을 직접쳐서 들어올수 없어야 한다.) 
		//로그인 전 요청 시 => 메인페이지 응답, alert 띄우기 => url재요청(request x)
		//로그인 후 요청 시 => 마이페이지 응답 => 포워딩
		
		HttpSession session = request.getSession(); //셰션 가져오기 => 마이페이지는 로그인 정보 있어야 한다.
		
		if(session.getAttribute("loginMember")==null) {//로그인 전 => url재요청 방법 사용
			session.setAttribute("alertMsg", "로그인 후 이용가능한 서비스 입니다.");
			response.sendRedirect(request.getContextPath()); //url재요청
		}else {//로그인 한 후
			
			RequestDispatcher view = request.getRequestDispatcher("views/member/myPage.jsp");
			view.forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
