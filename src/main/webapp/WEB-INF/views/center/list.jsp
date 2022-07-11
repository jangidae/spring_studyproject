<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->
<style>
.bbsfree-bt{
	position:relative;
	width:230px;
	left:800px;
}
</style>
<div class="title"><h4>고객센터</h4></div>
	<div class="content" style='margin-bottom:10px;'>
		<div class='bbsfree-bt'>
		<input type="button" value="게시물 등록" onclick="location.href='create.do?'" class='btn btn-secondary'>
		<input type="button" value="HOME"   onclick="location.href='/home.do'"  class='btn btn-secondary'>
		</div>
	</div>
	
	<table class='table table-sm'>
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
	
	
	
	<c:forEach var="dto" items="${list}">
    	<tr>
    		<td>${dto.wno}</td>
    		<td>${dto.wcode}</td>
    		<td>${dto.wtitle}</td>
    		<td>${dto.userid}</td>
	    	<td>${fn:substring(dto.wdate,0,10)}</td>
	         <td>${fn:substring(dto.mdate,0,10)}</td>
    		<td>${dto.wview}</td>
    	</tr>
    </c:forEach>
	
	</table>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
