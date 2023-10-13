<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
        margin: auto;
        margin-top: 50px;
    }

    #myPage-form table{ margin: auto;}
    #myPage-form input{margin: 5px;}


</style>

</head>
<body>

<!-- ../ => 현재 폴더에서 하나 뒤로 가기 -> 현재 myPage.jsp 에서 ../하면 views -->
<%@ include file="../common/menubar.jsp" %> 

        <c:set var="userId" vale="${ userId }"/>
        <c:set var="userName" value="${ userName }"/>
        <c:set var="phone" value="${ phone }"/>
        <c:set var="email" value="${ email }"/>
        <c:set var="address" value="${ address }"/>
        <c:set var="interest" value="${ inerest }"/>
        
    <%
        String userName = loginMember.getUserName();
        String phone = (loginMember.getPhone()==null)?"":loginMember.getPhone(); //삼항연산식 사용 a?b:c => a가 참이면 b, 거짓이면 c
        String email  = (loginMember.getEmail()==null)?"":loginMember.getEmail();
        String address = (loginMember.getAddress()==null)?"":loginMember.getAddress();
        String interest = (loginMember.getInterest()==null)?"":loginMember.getInterest();
        //"운동,등산,영화" | ""
    %>

    <div class="outer">
        <br>

        <h2 align="center">마이페이지</h2>

        <form id="myPage-form" action="<%=contextPath %>/update.me" method="post">
            <table>
                <tr>
                    <td>* 아이디</td>
                    <td><input type="text" name="userId" maxlength="12" value="${ userId }" required></td>
                   
                </tr>
                <tr>
                    <td>* 이름</td>
                    <td><input type="text" name="userName" maxlength="6" value="${ userName }" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;전화번호</td>
                    <td><input type="text" name="phone" value="${ phone }" placeholder="- 포함해서 입력"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;이메일</td>
                    <td><input type="email" name="email" value="${ email }"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;주소</td>
                    <td><input type="text" name="address" value="${ address }"></td>
                    <td></td>
                </tr>
                <tr>
                    <td>&nbsp;&nbsp;관심분야</td>
                    <td colspan="2">
                        <input type="checkbox" name="interest" id="sports" value="운동">
                        <label for="sports">운동</label>

                        <input type="checkbox" name="interest" id="hiking" value="등산">
                        <label for="hiking">등산</label>

                        <input type="checkbox" name="interest" id="fishing" value="낚시">
                        <label for="fishing">낚시</label>
                        <br>
                        <input type="checkbox" name="interest" id="cooking" value="요리">
                        <label for="cooking">요리</label>

                        <input type="checkbox" name="interest" id="game" value="게임">
                        <label for="game">게임</label>

                        <input type="checkbox" name="interest" id="movie" value="영화">
                        <label for="movie">영화</label>
                    </td>
                </tr>
            </table>
            
            <script>
            	$(function(){
            		const interest = "${ interest }";
            		//현재 로그인한 회원의 관심 분야들
            		//"" => 아무것도 선택한한 경우 | "운동, 요리"=> 선택 항목이 있을 경우
            		//console.log(interest);
            		
            		$("input[type=checkbox]").each(function(){//input중에서 type이 checkbox인 것을 각각 접근한다.
            			//$(this) : 순차적으로 접근되는 체크박스 요소
            			//$(this).val() : 해당 체크박스의 value 값
            			if(interest.search($(this).val()) != -1){ //체크박스의 값이 있을 경우
            				$(this).attr("checked",true); //arrt => 속성 변경
            			}
            		})
            	})
            	
            </script>
            
            <br><br>

            <div align="center">
                <button type="submit" class="btn btn-sm btn-secondary">정보변경</button>
                <button type="button" class="btn btn-sm btn-warning" data-toggle="modal" data-target="#updatePwdModal">비밀번호변경</button>
                <button type="button" class="btn btn-sm btn-danger" data-toggle="modal" data-target="#deleteModal">회원탈퇴</button>
            </div>

        </form>
     </div>
     
     
     
     <!-- 비밀번호 변경 용 Modal -->
	<div class="modal" id="updatePwdModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">비밀번호 변경</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center">
	        <form action="<%=contextPath %>/updatePwd.me" method="post">
	        	<!-- 해당 form에서 받는 값은 비밀번호 관련된 값들인데 controller로 넘어가면 아이디 값도 필요 => hidden으로 숨겨서 넘겨야 한다. -->
	        	<input type="hidden" name="userId" value="${ userId }" >
	        	
                <table>
                    <tr>
                        <td>현재 비밀번호</td>
                        <td><input type="password" name="userPwd" required></td>
                    </tr>
                    <tr>
                        <td>변경할 비밀번호</td>
                        <td><input type="password" name="updatePwd" required></td>
                    </tr>
                    <tr>
                        <td>변경할 비밀번호 확인</td>
                        <td><input type="password" name="checkPwd" required></td>
                    </tr>
                </table>
                <br>

                <button type="submit" class="btn btn-sm btn-secondary" onclick="return vaildatePwd();">비밀번호 변경</button>

                <br><br>
            </form>
	      </div>
	
            <script>
                function vaildatePwd(){
                    if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()){
                        alert("변경할 비밀번호가 일치하지 않습니다.");
                        return false; 
                        //false를 반환화면 form태그 안에 있는 값이 다음으로 넘어가지 않는다.
                    }
                }
            </script>

            </div>
        </div>
    </div>
            
     <!-- 회원 탈퇴 용Modal -->
	<div class="modal" id="deleteModal">
	  <div class="modal-dialog">
	    <div class="modal-content">
	
	      <!-- Modal Header -->
	      <div class="modal-header">
	        <h4 class="modal-title">회원 탈퇴</h4>
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	      </div>
	
	      <!-- Modal body -->
	      <div class="modal-body" align="center">
            <form action="<%=contextPath %>/delete.me" method="post">
	      		<input type="hidden" name="userId" value="${ userId }" >
                <b>탈퇴 후 복구가 불가능 합니다. <br> 정말로 탈퇴하시겠습니까?</b> <br><br>

                비밀번호 : <input type="password" name="userPwd" required> <br><br>

                <button type="submit" class="btn btn-sm btn-danger">탈퇴하기</button>

				<!-- 
					회원 탈퇴 요청시 sql문
					UPDATE MEMBER
					SET STATUS = 'N'
						, MODIFY_DATE = SYSDATE
					WHERE USER_ID = 현재 로그인한 회원 아이디
					AND USER_PWD = 사용자가 입력한 비밀번호(form태그 내에 없다. 따라서 input타입으로 숨겨서 가져와야 됨)
					(form태그 안에 있는 건 controller로 넘어감 -> 회원 탈퇴시 아이디 필요, 그렇기 때문에 아이디 값 가져와야 됨)
					(정보변경, 비번 변경 처럼 갱신된 회원 다시 조회할 필요 없다.)
					
					성공했을 경우 : 메인페이지 alert(성공적으로 회원 탈퇴가 되었습니다. 그동안 이용해주셔서 감사합니다.)
							단, 로그아웃 되어 있어야 함(session에 loginMember 라는 키값에 해당하는 걸 지우기)
					실패했을 경우 : 마이페이지 alert(회원 탈퇴에 실패 했습니다.)
				 -->

            </form>


	      </div>
	      
	  
	    </div>
	  </div>
	</div>
     
     
     
     
     
     

</body>
</html>