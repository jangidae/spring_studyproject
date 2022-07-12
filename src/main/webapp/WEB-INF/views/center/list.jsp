<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Date, net.utility.Utility"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->
<style>
.bbsfree-bt {
	position: relative;
	width: 230px;
	left: 800px;
}

.search {
	padding-left: 600px;
	position: reative;
}

.pagination {
	display: inline-block;
}

.center {
	text-align: center;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
}

.pagination a:active {
	background-color: black;
	color: white;
	border-radius: 5px;
}

.pagination a:hover:not(.active) {
	background-color: orange;
	border-radius: 5px;
}

</style>

<div class="title"><h4>고객센터</h4></div>
	<div class="content" style='margin-bottom:10px;'>
		<div class='bbsfree-bt'>
		<input type="button" value="게시물 등록" onclick="location.href='create.do';" class='btn btn-secondary'>
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
		<th>작성일</th>
		<th>조회수</th>
		
	</tr>
	</thead>
	
	
	
	<c:if test="${list==null}">
      <tr>
         <td colspan='7'>
            <p> 등록된 글이 없습니다. </p> 
         </td>
      </tr>
   </c:if>
   <c:set var="now" value="<%=new Date()%>"/>
   <c:set var="cnt" value="${list.size() }"/>
   <c:forEach var="dto" items="${list}" varStatus="stat">
   
      <tr>
         <td>${cnt}</td>   
         <td>${dto.wcode.split("-")[0]}</td>
         <td style='text-align:left;'>
            <c:forEach var="i" begin="1" end="${dto.windent}" step="1">&nbsp;&nbsp;
            </c:forEach>
            ${dto.windent>0 ? '┗' : ''}<a href="read.do?wno=${dto.wno}">
            ${dto.wtitle}${Utility.getNew(now,dto.wdate) ? '<img src="../images/new.gif">' : ' '  } 
            ${dto.wview>=50 ? '<img src="../images/hot.gif">' : ' '}</a>
         </td>
         <td>${dto.userid}</td>
         <td>${fn:substring(dto.wdate,0,10)}</td>
         <td>${dto.wview}</td> 
      </tr>
      <c:set var="cnt" value="${cnt-1 }"/>
   </c:forEach>
</table>
<div class='search'>
	<form method='get' action='search.do'>
		<input type='text' style='' name='search' size=25 maxlength=25>
		<button class='btn btn-secondary'>검색</button>
	</form>
</div>

	<!--  빠진부분 -->
	<!-- 페이지 리스트 -->
<c:if test="${requestScope.count>0 }">
   <c:set var="pageCount" value="${requestScope.totalPage}"/>
   <c:set var="startPage" value="${requestScope.startPage}"/>
   <c:set var="endPage"   value="${requestScope.endPage}"/>

   <div class="content">
       <c:if test="${endPage>pageCount}">
          <c:set var="endPage" value="${pageCount+1}"/>
       </c:if>
   
       <c:if test="${startPage>0}">
          <a href="./list.do?pageNum=${startPage}">[이전]</a>
       </c:if>
   
       <c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
          <a href="./list.do?pageNum=${i}">[${i}]</a>
       </c:forEach>
   
       <c:if test="${endPage<pageCount}">
          <a href="./list.do?pageNum=${startPage+11}">[다음]</a>
       </c:if>
    </div>
</c:if>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>