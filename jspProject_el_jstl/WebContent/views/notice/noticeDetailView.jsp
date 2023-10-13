<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
  <%
 Notice n = (Notice)request.getAttribute("n");
//글번호, 글제목, 내용, 작성자 아이디, 작성일
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    .outer{
        background-color: black;
        color: white;
        width: 1000px;
        height: 500px;
        margin: auto;
        margin-top: 50px;
    }


</style>

</head>
<body>
	
	<%@ include file = "../common/menubar.jsp" %>

    <div class="outer" align="center">
        <br>
        <h2 align="center">공지사항 상세 보기</h2>
        <br>

        <table id="delete-area" border="1">
            <tr>
                <th width="70">제목</th>
                <td colspan="3" width="430"><%=n.getNoticeTitle() %></td>
               
            </tr>
            <tr>
                <th>작성자</th>
                <td><%=n.getNoticeWriter() %></td>
                <th>작성일</th>
                <td><%=n.getCreateDate() %></td>
            </tr>
            <tr>
                <th>내용</th>
                <td colspan="3">
                    <p style="height: 150px;"><%=n.getNoticeContent() %></p>
                </td>
            </tr>
        </table>
        <br><br>

        <div>
            <a href="<%=contextPath %>/list.no" class="btn btn-sm btn-secondary">목록가기</a>
            
            <!--현재 로그인한 사용자가 해당 글을 쓴 본인일 경우 수정및 삭제 할 수 있다. 그외 사람은 수정 및 삭제 불가-->
         	<%if(loginMember != null && n.getNoticeWriter().equals(loginMember.getUserId())) {%>
	            <a href="<%=contextPath %>/updateForm.no?num=<%=n.getNoticeNo()%>" class="btn btn-sm btn-warning">수정하기</a>
	            <a href="<%=contextPath%>/delete.no?num=<%=n.getNoticeNo()%>" class="btn btn-sm btn-danger">삭제하기</a>
	            
	            <!-- 
	            	삭제 구현해보기
	            	url - mapping 맘대로
	            	(마음대로 mapping지정 할 수 있지만 href는 주의 해서 쓰기 => 이것 때문에 계속 오류 남)
	            	
	            	요청 성공시 => 공지사항 목록페이지 alert(공지사항이 성공적으로 삭제 되었습니다.)
	            	요청 실패시 => 에러 문구 보여지는 에러 페이지
	             -->
	            
	        <%} %>
        </div>

    </div>




</body>
</html>