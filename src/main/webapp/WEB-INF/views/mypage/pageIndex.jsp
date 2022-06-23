<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  본문 시작 pageForm.jsp -->    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>고객센터</title>
 <style>
 	.side {float: left; width: 180px; backgrou-color:#666:}
 	.content {float: left; width: 300px;}
 	
 </style>

</head>
</html>
<h2>고객센터</h2>

<p><a href="pageForm.jsp">[글쓰기]</a></p>

<div class="container">
	<table class="table table-hover">

<div class="main">
	<div class="side">
    		<a href="/mapge/pagefrom" class="left_item" onclick="clickcr" align="left">
                    <div class="menu_text on">건의사항</div>
            </a>
			<a href="/mapge/pagefrom" class="left_item" onclick="clickcr" align="left">
                    <div class="menu_text ">자주 묻는 질문</div>
            </a>
			<a href="/mapge/pagefrom" class="left_item" align="left" onclick="clickcr"
                   aria-current=>
                    <div class="menu_text ">신고하기</div>
            </a>
       </div>      
 </div>            
	
	    
	    <div class="content">
	<tr>
		<table class="table table-hover">
	
		<tr class="success">	
		 	 	<th>글번호</th>
		 	 	<th>카테고리</th>
		 	 	<th>제목</th>
		 	 	<th>작성자</th>
		 	 	<th>작성일</th>
		 	 	<th>조회수</th>
		 </tr>
	
	 
	
	 <c:forEach var="dto" items="${list}">
    	<tr>
    		<td>${dto.wno}</td>
    		<td>${dto.wcode}</td>
    	</tr>
    </c:forEach>



</table>
</tr>
</div>



	

<!--   본문 끝 -->
<%@ include file="../footer.jsp" %>
