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
									<td>신청</td>
								<tr>
							</thead>
							<tbody>
								<tr>
									<td>${dto.sgname}</td>
									<td>${dto.sgintro}</td>
									<td>${dto.sgleader}</td>
									<td>${dto.sgmaxuserno}</td>
									<td>${dto.sgselectlang}</td>
									<td><button type="button" onClick="location.href='/studygroup/groupJoin.do?sgno=${dto.sgno}'">그룹 신청</button>
								</tr>								
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
