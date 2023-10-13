<%@page import="com.kh.notice.model.vo.Notice"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Notice n = (Notice)request.getAttribute("n");
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
    } /* 쓰기 귀찮으면 메뉴바에 추가하면 됨*/

    #update-form input, #update-form textarea{
        width: 100%;
        box-sizing: border-box;
    }

</style>


</head>
<body>
    <%@include file="../common/menubar.jsp" %>
	<div class="outer" align="center">
        <br>
        <h2>공지사항 수정하기</h2>
        <br>

        <form action="<%=contextPath%>/update.no" id="update-form" method="post">
        <input type="hidden" name="num" value="<%=n.getNoticeNo()%>">
            <table>
                <tr>
                    <th width="50">제목</th>
                    <td width="450"><input type="text" name="title" required value="<%=n.getNoticeTitle()%>"></td>
                </tr>

                <tr>
                    <th>내용</th>
                    <td></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <textarea name="content" rows="10" style="resize: none;" required><%=n.getNoticeContent() %></textarea>
                    </td>
                </tr>

            </table>

            <br><br>

            <div>
                <button type="submit">수정하기</button>
                <button type="button" onclick="history.back();">뒤로가기</button>
            </div>
           <!-- onclick="history.back(); => 뒤로가기 -->
        </form>

    </div>
</body>
</html>