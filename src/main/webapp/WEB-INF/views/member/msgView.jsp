<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<h2>로그인 결과</h2>
	${requestScope.message}
	<p>${requestScope.link}</p>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>