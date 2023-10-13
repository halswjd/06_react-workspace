package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//입력값에 한글이 없기 때문에 인코딩을 생략
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		Member updateMem = new MemberService().updatePwd(userId,userPwd,updatePwd);
		
		HttpSession session = request.getSession(); //if밖에 써서 전역?으로 사용 할 수 있다. if안에 썼다면 2번 사용했을 것
		
		if(updateMem == null) {//실패
			session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
			
		}else {//성공
			session.setAttribute("loginMember", updateMem); //session 바꾸기 => loginMember에 있던 값을 updateMem의 값으로
			session.setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다.");
		}
		response.sendRedirect(request.getContextPath() + "/myPage.me"); //비밀번호 없데이트 후, 실패든 성공이든 보여지는 화면에 마이페이지
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
