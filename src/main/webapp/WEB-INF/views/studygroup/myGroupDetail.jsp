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
					<div class="panel-heading">그룹 상세</div>
					<div class="panel-body">
						<table class="table table-hover">
							<thead>
								<tr>
									<td>그룹명</td>
									<td>그룹소개</td>
									<td>그룹리더</td>
									<td>그룹정원</td>
									<td>카테고리</td>
								<tr>
							</thead>
							<tbody>
								<tr>
									<td>${dto.sgname}</td>
									<td>${dto.sgintro}</td>
									<td>${dto.sgleader}</td>
									<td>${dto.sgmaxuserno}</td>
									<td>${dto.sgselectlang}</td>
								</tr>								
							</tbody>
						</table>
						
						멤버들
						<table>
							<thead>
								<tr>
									<td>아이디</td>
									<td>가입일</td>
									<td>등급</td>
									<td>승인여부</td>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="list" items="${list}">
									<tr>
										<td>${list.userid}</td>
										<td>${fn:substring(list.sgjoind,0,10)}</td>
										<td>
											<c:if test="${list.sglevel eq 'L'}">리더</c:if>
											<c:if test="${list.sglevel eq 'T'}">팀원</c:if>
											<c:if test="${list.sglevel eq 'W'}">대기</c:if>										
										</td>
										<td>
											<c:if test="${mySglevel eq 'L'}">
												<c:if test="${list.sglevel eq 'L' || list.sglevel eq 'T'}">승인완료</c:if>
												<c:if test="${list.sglevel eq 'W' }">
													<button type="button" onClick="location.href='/studygroup/groupConfirm.do?sgno=${dto.sgno}&userid=${list.userid}'">승인</button>
												</c:if>
											</c:if>
											
											<c:if test="${mySglevel ne 'L'}">
												<c:if test="${list.sglevel eq 'L' || list.sglevel eq 'T'}">승인완료</c:if>
												<c:if test="${list.sglevel eq 'W' }">승인대기중</c:if>
											</c:if>
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
