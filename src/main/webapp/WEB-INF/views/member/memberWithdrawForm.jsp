<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<h4>회원 탈퇴</h4>
<form action="memberWithdraw.do" method="post">
<p><strong>비밀번호를 입력하세요.</strong>
<input type="password" id="upw" name="upw">
<input type="submit" value="확인">
</form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>