package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) { //form 태그 안에 있는 enctype=multipart/form-data에 오타가 있는지 확인하는 과정, 저게 제대로 써있지 않으며 실행되지 않는다.
			
			//1_1. 전달되는 파일 용량 제한(int maxSize)
			int maxSize = 10*1024*1024; // 10메가바이트
			
			//1_2. 전달되는 파일을 저장시킬 서버의 폴더 물리적인 경로(String savePath)
			String savePath = request.getSession().getServletContext().getRealPath("/resource/board_upfiles/");
			
			//2.전달된 파일명 수정 작업 후 서버에 업로드
			//HttpServletRequest => MultipartRequest
			//아래 한 코드만 있어도 파일 저장이 됨 -> 값이 전달되지 않아도 파일에는 저장이 이루어진다.
			MultipartRequest multiRequest = new MultipartRequest(request,savePath,maxSize,"UTF-8",new MyFileRenamePolicy());
			
			//3. 본격적으로 sql문 실행할 때 필요한 값(요청시 전달값)뽑아서 vo에 기록
			//>>공통적으로 수행 : update board
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String category = multiRequest.getParameter("category");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			Board b = new Board(); //초기값을 설정 //매개변수로 할경우 vo에서 해당 매개변수만 가지고 있는 생성자를 만들어야 한다.(아마도) 그래서 이방법이 조금더 간단할 듯
			//아래 단계를 거치면서 값이 설정이 됨
			b.setBoardNo(boardNo);
			b.setCategory(category);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			Attachment at =null; //처음에는 null로 초기화, 넘어온 새 첨부파일이 있을 경우 그때 생성
			
			if(multiRequest.getOriginalFileName("upfile") != null) {
				//null이 아니면 새로 넘어온 첨부파일이 있다. => Attachment객체 생성
				at = new Attachment();//기본 생성자여서 초기값이 들어있다.
				//초기값으로 설정된것을 바꿔주는 과정
				at.setOriginName(multiRequest.getOriginalFileName("upfile"));
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				at.setFilePath("resource/board_upfiles");
				//여기까지가 공통적으로 처리되어야 하는것
				
				//여기부터 첨부파일이 있었는지 없었는지에 따라 구분해주는거
				if(multiRequest.getParameter("originFileNo") != null) {
					//기존에 첨부파일이 있었을 경우 => UpdateAttachment(기존의 첨부파일 번호 필요) => 다른건 위에서 다 뽑음
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
					
				}else {
					//기존에 첨부파일이 없었을 경우 => insert Attachment(현재 게시글의 번호 필요) => 다른건 위에서 다 뽑음
					at.setRefBoardNo(boardNo); //3번 단계에서 뽑은거 재활용.(앞에서 뽑은 값 잘 활용하기)
					
				}
				
			}
			
			//새로 넘어온 첨부 파일 없었다면 at는 여전히 null이다.
			
			int result = new BoardService().updateBoard(b,at);
			//새로운 첨부파일없으면 						=> (b,null)  => Board Update만 하면 된다.
			//새로운 첨부파일이 있고, 기존의 첨부파일이 있다면    => (b,fileNo이 담긴 at) => Board Update + Attachment Update 해야 된다.
			//새로운 첨부파일이 있고, 기존의 첨부파일이 없다면    => (b,RefBoardNo가 담긴 at) => Board Update + Attachment insert
			
			if(result > 0) {
				//성공 => /jsp/detail.bo?bno=해당게시글번호 url 재용청 => 기존에 봣었던 상세조회 페이지
				request.getSession().setAttribute("alertMsg", "성공적으로 수정이 되었습니다.");
				response.sendRedirect(request.getContextPath()+"/detail.bo?bno="+ boardNo);
				//변수를 미리 담아놓고 있으면 재사용을 할 수 있다. => boradNo같은 경우 이말에 적절한 예시이다.
			}else {
				//실패
				request.setAttribute("errorMsg", "일반게시판 수정에 실패했습니다.");
				RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
				view.forward(request, response);

			}
		
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
