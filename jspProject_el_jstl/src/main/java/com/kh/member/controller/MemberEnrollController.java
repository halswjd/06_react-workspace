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
 * Servlet implementation class MemberEnrollController
 */
@WebServlet("/insert.me")
public class MemberEnrollController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberEnrollController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //회원 가입 (입력 값 입력 후 가입하기)  - 필요한 정보 : 아이디, 비밀버호, 이름, 전화번호, 이메일, 주소, 관심분야
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//1)인코딩 작업 post방식 에서만 사용 ->post방식이여도 한글 값이 없다면 굳이 안써도 된다.
		request.setCharacterEncoding("UTF-8");
		
		//2)요청 시 전달 값 뽑아서 변수 및 객체에 기록하기
		String userId = request.getParameter("userId"); //"user03" => 필수 입력 값
		String userPwd = request.getParameter("userPwd");//"pass03" => 필수 입력 값
		String userName = request.getParameter("userName");//"차은우" => 필수 입력 값
		String phone = request.getParameter("phone");//"01011112222" | " " => 입력 값이 있으면 입력 값 출력, 없으면 공백 
		String email = request.getParameter("email");//"adk@fkj" | " "
		String address = request.getParameter("address"); //"경기도 의정부시" | " "
		String[] interestArr = request.getParameterValues("interest"); //["운동","등산"] | null => 여러개의 값이 담기기 떄문에 배열로 받는다.
		
		//기본 생성자로 생성 후 setter 메소드 이용해서 담기(담으려고 하는게 소량일 경우 추천)
		//아싸리 매개변수 생성 자를 이용해서 생성과 동시에 담기 (담으려고 하는게 많을 경우 추천) => 사용
		
		//String[]  ----->String 변경 => Member.java에서 String[]을 사용한 값이 없기 때문에 String 변경
		//["운동","등산"] --> 운동,등산
		String interest="";  //빈 String 자료형 만들기. 여기에 String[]에서 String로 바뀐 값을 넣음
		if(interestArr != null) { //선택된 값이 있을 경우
			interest = String.join(",", interestArr); 
		}
		
		Member m = new Member(userId, userPwd, userName, phone, email, address,interest); //매개변수 7개인거 만들기(한개씩 값을 받는것 보다 양이 많으면 생성자 생성해서 담는게 좋다.) 대신 이렇게 할 경우 vo에 가서 해당 매개변수믄 가지고 있는 생성자 생성해야됨
		
		//3) 요청 처리(db에 sql문 실행) => 서비스 메소드 호출 및 결과 받기
		
		int result = new MemberService().insertMember(m); //m은 위에서 만든 값이 들어 있는 아이
		
		
		
		//4)처리결과를 가지고 사용자가 보게 될 응답 뷰 지정 후 포워딩 또는 url 재요청
		
		if(result >0) {//성공
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원가입이 되었습니다."); //request는 포워딩 방식일 때문 가능하다.
			
			//성공 => index 페이지 => url재요청 (한번이라도 봤기 때문에 가능하다) => /jsp
			response.sendRedirect(request.getContextPath());
			
		}else {//실패
			request.setAttribute("errorMsg", "회원 가입에 실패했습니다.");
			
			//forward방식
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
