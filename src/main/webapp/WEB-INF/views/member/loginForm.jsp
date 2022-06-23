<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="auth.jsp" %>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->


<form name="loginfrm" id="loginfrm" action="loginproc.do" method="POST" onsubmit="return loginCheck()"><!-- myscript.js에서 함수 작성 -->
	<h1>로그인1</h1>
	<hr>
	&nbsp;&nbsp;<b>아이디</b> <input type="text" id="userid" name="userid" placeholder="Enter id" maxlength="10" required>
	<br>
	<b>비밀번호</b> <input type="password" id="upw" name="upw" placeholder="Enter password" maxlength="10" required>  
	<hr>
	<div class="clearfix">
		<button type="reset" class="cancelbtn">취소</button>
		<button type="submit" class="submitbtn">로그인</button>
	</div>
	<label><input type="checkbox" name="c_id" value="SAVE">아이디 저장</label>
	&nbsp;&nbsp;
	<a href="findID.jsp">아이디/비밀번호찾기</a>
	&nbsp;&nbsp;
	<a href="join.jsp">회원가입</a>
</form>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>