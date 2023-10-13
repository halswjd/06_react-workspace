package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//-----------페이징 처리-----------------
		//핵 어려움 주의 + 원리를 파악하자 => 결국은 공식을 외우면 됨
		
		int listCount; //현재 총 게시글 개수
		int currentPage; //현재 페이지(즉, 사용자가 요청한 페이지)
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 개수(몇개 단위씩)
		int boardLimit; // 한페이지 내에 보여질 게시글 최대 개수(몇개 단위씩)
		
		//위의 4개를 가지고 아래 3개의 값을 구해낼꺼임
		int maxPage; // 가장 마지막 페이지(총 페이지 수)
		int startPage; // 페이징바의 시작수
		int endPage;//페이징바의 끝수
		
		// listCount : 총 게시글 개수 => db에서 갖고와야 됨
		listCount = new BoardService().selectListCount();
		
		// currentPage : 현재 페이지(즉, 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage")); //currentPage의 자료형은 int, getParameter의 자료형은 String
																	//request.getParameter("cpage") =>String but currentPage가 int이기 때문에 형변환
		
		// pageLimit : 페이징바의 페이지 최대 개수, 내가 정할 수 있다.
		pageLimit = 10;
		
		// boardLimit : 한 페이지 내에서 보여줄 최대 게시물, 내가 정할 수 있다.
		boardLimit = 10;
		
		/*
		 *  maxPage : 제일 마지막 페이지 수(총 페이지 수)
		 *  
		 *  listCount, boardLimit 에 영향을 받음 => 전체 게시물 개수와 한 페이지에서 볼 수 있는 게시물의 개수이기 때문에
		 *  
		 *  ex)게시글이 10개 단위씩 보여진다는 가정하에
		 *  
		 *    listCount     boardLimit        maxPage
		 *  	100.0	/	10 =>10.0			10
		 *  	101.0	/	10 =>10.1			11
		 *  	105.0	/	10 =>10.5			11
		 *  	110.0	/	10 =>11.0			11
		 *  
		 *  ex)게시글이 5개 단위씩 보여진다는 가정하에
		 *  
		 *   listCount     boardLimit        maxPage(가장 마지막 페이지)
		 *   	10.0	/	   5	=>2.0	    2
		 *   	11.0	/	   5 	=>2.2		3
		 *   	14.0	/	   5	=>2.8		3
		 *   
		 *   총 게시글개수(실수형) / boardLimit => 올림처리
		 */
		
		maxPage = (int)Math.ceil((double)listCount / boardLimit);//listCount를 실수로 바꾸기 위해 double , maxPage는 int형 자료형이기 때문에 반올림한 값 int로 형변환
		
		/*
		 * startPage : 페이징바의 시작 수
		 * 
		 * pageLimit, currentPage에 영향을 받음 (페이징 바의 페이지수, 현재 페이지)
		 * 
		 * ex) 페이징바의 목록이 10개 단위씩(pageLimit) 이라는 가정하에...
		 * 		startPage : 1, 11 ,21, 31 ....
		 * 					=> n * pageLimit +1
		 * 
		 * currentPage  	startPage
		 * 		1				1		=>0 * pageLimit +1 => n=0
		 * 		5				1		=>0 * pageLimit +1 => n=0
		 * 		10				1		=>0 * pageLimit +1 => n=0
		 * 
		 * 		11				11 		=>1 * pageLimit +1 => n=1
		 * 		15				11		=>1 * pageLimit +1 => n=1
		 * 		21				21		=>2	* pageLimit +1 => n=2
		 * 
		 * 1~10 	=> n=0
		 * 11~20	=> n=1
		 * 21~30	=> n=2
		 * 
		 * (currentPate - 1) / pageLimit   => n
		 * 0~9		/ pageLimit
		 * 10~19	/ pageLimit
		 * 20~29	/ pageLimit
		 *	
		 *	startPage =   n 						* pageLimit + 1  => / : 자바에서 저 기호는 몫만 나머지는 생각안함
		 *			  = (currentPage-1) / pageLimit * pageLimit + 1
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit +1;
		
		/*
		 * endPage : 페이징바의 끝 수
		 * 
		 * startPage, pageLimit에 영향을 받음(단, maxPage에도 영향을 받긴함)
		 * 
		 * ex)pageLimit : 10이라는 가정하에 
		 * 
		 * startPage : 1  => endPage : 10
		 * startPage : 11 => endPage : 20
		 * startPage : 21 => endPage : 30
		 * 
		 * endPage = startPage + pageLimit - 1
		 */
		endPage = startPage + pageLimit - 1;
		
		//startPage가 11이면 endPage는 20으로 됨(근데 maxPage가 고작 13밖에 안되는데)
		if(endPage > maxPage) { //페이징 바의 마지막 페이지보다 가장 마지막 페이지 보다 크면 마지막 페이징바는 마지막 페이지하고 같아야 한다.
			endPage = maxPage;
		}
		
		//com.kh.common.model.vo.PageInfo
		//*패이징바를 만들 때 필요한 객체 => 어디서나 필요한 객체는 common에 흔히 만든다.
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit,boardLimit,maxPage,startPage,endPage);
		
		//현재 요청한 페이지(currentPage)에 보여질 게시글 리스트 boardLimit 수 만큼 조회하기
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		request.setAttribute("pi", pi); //페이징 바의 변경을 위해 pi를 가져가야한다.
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
