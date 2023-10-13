package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setCharacterEncoding("UTF-8"); //한글이 없어서 딱히 필요 없다.단, 원래는 Post방식이면 해야됨
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");//("철자도 같아야 하지만 대소문자도 똑같이")
		
		int result = new MemberService().deleteMember(userId, userPwd); //뒤에서 계속 넘어온 값 받기
		
		/*
		 * 성공 => 메인페이지 alert(성공적으로 회원 탈퇴 되었습니다.) , 로그아웃 되어야 됨(세션에 loginMember라는 키값에 해당하는 값 지우기)
		 * 실패 => 마이페이지 alert(회원 탈퇴 실패)  
		 */
		
		HttpSession session =request.getSession(); //session 가져오기
		if(result > 0) {//성공 url재요청 가능하다(한번이라도 들어간 기록이 있기 때문에)
			session.setAttribute("alertMsg","성공적으로 회원 탈퇴 되었습니다. 그동안 이용해 주셔서 감사합니다.");
			//session.invalidate(); //세션 초기화 => 모든 세션 초기화???
			session.removeAttribute("loginMember");
			response.sendRedirect(request.getContextPath()); //메인화면으로 이동
			
		}else {//실패 url재요청 가능하다.(한번이라고 들어간 기록이 있기 때문에)
			session.setAttribute("alertMsg","탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		}
	}

	/**
	 * doPost 절대 지우면 안됨
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
