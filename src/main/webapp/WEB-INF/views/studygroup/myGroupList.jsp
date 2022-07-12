<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<style>
.bbsfree-bt {
	position: relative;
	width: 230px;
	left: 800px;
}
</style>
<title>studyList</title>
</head>
<body>
	<div class="page-wrapper">
		<div class="container-fluid">
			<div class="col-lg-12">
				<!--게시판 넓이 -->
				<div class="col-lg-12"></div>
				<div class="row">
					<div class="col-lg-12"></div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">내 그룹 목록</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<td>그룹명</td>
									<td>그룹소개</td>
									<td>등급</td>
								<tr>
							</thead>
							<tbody>
								<c:forEach var="dto" items="${list}">
									<tr>
										<td>${dto.sgname}</td>
										<td><a href="/studygroup/myGroupDetail.do?sgno=${dto.sgno}&sglevel=${dto.sglevel}">${dto.sgintro}</a></td>
										<td>
											<c:if test="${dto.sglevel eq 'L'}">리더</c:if>
											<c:if test="${dto.sglevel eq 'T'}">팀원</c:if>
											<c:if test="${dto.sglevel eq 'W'}">대기</c:if>										
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>
<%@ include file="../footer.jsp"%>
