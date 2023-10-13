package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MemberEnrollFormController
 */
@WebServlet("/enrollForm.me")
public class MemberEnrollFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //회원가입 페이지 폼을 보여주기만 하면 되기 때문에 값을 받을 필요가 없다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//응답 페이지(회원 가입 페이지) - forward방식으로 => 회원가입 페이지는 회원 가입을 하기 위해 들어가는거 아니면 방문할 이유가 없기 때문에 기록이 없다.
		RequestDispatcher view = request.getRequestDispatcher("views/member/memberEnrollForm.jsp");
		view.forward(request, response); //http://localhost:8001/jsp/enrollForm.me //view.forward를 해야 완성
		
		//request.getRequestDispatcher("views/member/memberEnrollForm.jsp").forward(request, response); 위의 코드 한줄 코드 => 한줄 코드는 언제든지 사용가능인가???
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
