package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.vo.Member;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인코딩 (post방식에 한글이 있을 수 있기 때문에 꼭 필요)
		request.setCharacterEncoding("UTF-8");
		
		String noticeTitle = request.getParameter("title");
		String noticeContent = request.getParameter("content");
		
		//로그인한 회원 정보를 얻어내는 방법
		//1. input type = "hidden" 으로 애초에 요청시 숨겨서 전달하기
		//2. session에 담겨있는 loginMember를 활용하는 방법 -> 이용
		
		HttpSession session = request.getSession();
		int userNo = ((Member)session.getAttribute("loginMember")).getUserNo();
		//session.getAttribute("loginMember") => 자료형 : object , loginMember 로 인해 자료형 Member 로 받고, loginMember안에 있는 userNo이 필요하기 때문에 한번더 묶어줘야한다.
		
		Notice n = new Notice();
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		//n.setNoticeWriter(userNo + ""); //방법1 형변환(int ->  String)
		n.setNoticeWriter(String.valueOf(userNo)); //방법2
		
		int result = new NoticeService().insertNotice(n);
		
		if(result > 0) {//성공 => alert 띄우면서 목록조회 화면으로 url 재요청
			session.setAttribute("alertMsg","성공적으로 공지사항 등록이 되었습니다.");
			response.sendRedirect(request.getContextPath()+"/list.no");
		}else {//실패 => 에러문구 담아서 에러 페이지 포워딩
			request.setAttribute("errorMsg", "공지사항 등록에 실패했습니다.");
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
