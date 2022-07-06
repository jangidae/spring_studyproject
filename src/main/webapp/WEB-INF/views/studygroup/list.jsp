<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<style>
.bbsfree-bt{
	position:relative;
	width:230px;
	left:800px;
}
</style>
<title>studyList</title>
</head>
<body>
<div class="page-wrapper">
    <div class="container-fluid">
        <div class="col-lg-12"><!--게시판 넓이 -->
            <div class="col-lg-12">
                <h3 class="page-header">팀 구인</h3>
            </div>
            <div class="row">
                  <div class="col-lg-12">
                   </div>
              </div>
            <div class="panel panel-default">
                <div class="panel-heading">팀 구인</div>
                <div class="panel-body">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th>글 번호</th>
                                <th>아이디</th>
                                <th>제목</th>
                                <th>글 작성일</th>
                                <th>게시물 내용</th>
                                <th>조회수</th>
                                
					            <tr>
					            <!-- studyCont의 list()함수에서 mav.addObject("list")를 가리킴 -->
					            
					            
						<c:forEach var="dto" items="${list}">
							<tr>
								<td>${dto.wno}</td>	
								<td><a href="../studygroup/updateForm.do?wno=${dto.wno}">${dto.wid}</a></td>
								<td>${dto.title}</td>
								<td>${dto.wdate}</td>
								<td>${dto.wcontent}</td>
								<td>${dto.wviewcount}</td>
							</tr>
						</c:forEach>
                           
                         </thead>        
                    </table>
						<input type="button" value="팀 구인" onclick="location.href='create.do'">
						
						<td>
							<input type="button" value="수정" onclick="location.href='update.do?wno=${dto.wno}'">
							<input type="button" value="삭제" onclick="location.href='delete.do?wno=${dto.wno}'">
						</td>
   
                </div>
            </div>
        </div>
    </div>
</div>
 
</body>
</html>
<%@ include file="../footer.jsp" %>   