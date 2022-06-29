<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date, net.utility.Utility"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->
<style>
.bbsfree-bt{
	position:relative;
	width:700px;
	left:300px;
}
</style>
<div class="title">게시판 목록</div>
	<div class="content" style='margin-bottom:10px;'>
		<div class='bbsfree-bt'>
		<input type="button" value="게시물 등록" onclick="location.href='create.do';" class='btn btn-secondary'>
		<input type="button" value="HOME"   onclick="location.href='/home.do'"  class='btn btn-secondary'>
		</div>
	</div>
	
	<table class='table table-sm' style='width:1100px'>
	<thead  class='table-dark'>
	<tr class='table-active'>
		<th>번호</th>
		<th>카테고리</th>
		<th>제목</th>
		<th>작성자</th>
		<th>최초작성일</th>
		<th>최근수정일</th>
		<th>조회수</th>
		
	</tr>
	</thead>
	<c:if test="${list==null}">
		<tr><td colspan='7'>
	<p> 등록된 글이 없습니다. </p> </td>
		</tr>
	</c:if>
	<c:set var="now" value="<%=new Date()%>"/>

	<c:forEach var="dto" items="${list}">
	
		<tr>	
			<td>${dto.ccode}</td>
			<td><a href="read.do?wno=${dto.wno}">${dto.wtitle}</a></td>
			<td>${dto.userid}</td>
			<td>${fn:substring(dto.wdate,0,10)}</td>
			<td>${fn:substring(dto.mdate,0,10)}</td>
			<td>${dto.wview}</td> 
		</tr>
	</c:forEach>
	
	</table>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>






