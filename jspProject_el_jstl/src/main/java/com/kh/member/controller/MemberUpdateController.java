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
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		//요청 시 전달 값 뽑아서 변수 및 객체에 담기
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest");
		
		//interset은 값이 여러개 가능하기 때문에 => 배열을 이용해서 담는다. 
		
		String interest=""; 
		if(interestArr != null) { //interest의 선택 값이 있을 때
			interest = String.join(",",interestArr);
		}
		
		Member m = new Member(userId,userName, phone, email, address, interest); //매개변수 생성 => 위에서 받는 값들이 많기 떄문에 매개변수를 만들어서 하면 조금 편하게 값을 받을 수 있다.
		
		//서비스 호출 => 위에서 받은 m을 이용해서 서비스 호출 m=>서비스로 넘겨야 하는 값들
		Member updateMem = new MemberService().updateMember(m);
		
		if(updateMem != null) {//성공
			//update된 값이 있을 경우
			//session에 담겨있는 loginMember를 바꿔치기 작업
			
			HttpSession session = request.getSession();
			
			session.setAttribute("loginMember", updateMem);
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
			
			//마이페이지 => /jsp/mypage.me => url 재요청
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		}else {//실패
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
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
