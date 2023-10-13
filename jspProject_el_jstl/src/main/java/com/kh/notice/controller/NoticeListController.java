package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 목록가기
	 * 공지사항 게시글 목록 화면 => 게시글이 없을 경우 한개 있을 경우, 여러개 있을 경우 가 있어 배열에 저장한는 건가???
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//[1,2) 요청시 전달 값 받기]
		
		//3)요청 처리(응답페이지에 필요한 데이터을 조회)
		ArrayList<Notice> list = new NoticeService().selectNoticeList();
		
		
		//4)응답 뷰 지정 => 공지사항 목록 페이지
		//	응답뷰 필요한 데이터는 request attribute 영역에 담기(포워딩) //한번도 들어간적이 없기 때문에 url재요청 방식 불가
		request.setAttribute("list", list);
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
