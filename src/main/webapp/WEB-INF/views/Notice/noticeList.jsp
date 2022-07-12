<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
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
<div class="title">
	<h4>공지사항</h4>
</div>
<div class="content" style='margin-bottom: 10px;'>
	<div class='bbsfree-bt'>
		<input type="button" value="게시물 등록"
			onclick="location.href='/Notice/create.do?wno=${requestScope.wno}'"
			class='btn btn-secondary'> <input type="button" value="HOME"
			onclick="location.href='/home.do'" class='btn btn-secondary'>
	</div>
</div>


<table class='table table-sm'>
	<thead class='table-dark'>
		<tr class='table-active'>
			<th>번호</th>
			<td><strong>카테고리</strong></td>
			<td><strong>제목</strong></td>
			<td><strong>최초작성일</strong></td>
			<th>조회수</th>
		</tr>
	</thead>
	<c:if test="${list==null}">
		<tr>
			<td colspan='7'>
				<p>등록된 글이 없습니다.</p>
			</td>
		</tr>
	</c:if>
	<c:forEach var="dto" items="${list}">
		<tr>
			<td>${dto.wno}</td>
			<td>${dto.ncode}</td>
			<td><a href="/Notice/noticeRead.do?wno=${dto.wno}">${dto.wtitle}</a></td>
			<td>${dto.wdate}</td>
			<td>${dto.wview}</td>
		</tr>
	</c:forEach>

</table>
<div class='search'>
   <form method='get' action='/Notice/search.do'>
      <input type='text' style='' name='search' size=25 maxlength=25>
      <button class='btn btn-secondary'>검색</button>
   </form>
</div>

<!-- 페이지 리스트 -->
<c:if test="${requestScope.count>0 }">
	<c:set var="pageCount" value="${requestScope.totalPage}" />
	<c:set var="startPage" value="${requestScope.startPage}" />
	<c:set var="endPage" value="${requestScope.endPage}" />

	<div class="pagination">
		<c:if test="${endPage>pageCount}">
			<c:set var="endPage" value="${pageCount+1}" />
		</c:if>

		<c:if test="${startPage>0}">
		   <!-- 호출명령어 틀렸습니다 <a href="./list.do?pageNum=${startPage}">[이전]</a> -->
		   <a href="./noticeList.do?pageNum=${startPage}">[이전]</a>
		</c:if>

		<c:forEach var="i" begin="${startPage+1}" end="${endPage-1}">
			<a href="./noticeList.do?pageNum=${i}">${i}</a>
		</c:forEach>

		<c:if test="${endPage<pageCount}">
			<a href="./noticeList.do?pageNum=${startPage+11}">[다음]</a>
		</c:if>
	</div>
</c:if>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>