<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title">게시물</div>

		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				${read.ccode}
			</td>
		</tr>
		<tr>
	      <th>작성자</th>
	      <td>${read.userid}</td>
	    </tr>
	    <tr>
	      <th>제목</th>
	      <td>${read.wtitle}</td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" cols='30' rows='15' style='background:white; border:0;' readonly>
	      ${read.wcontent}</textarea></td>
	    </tr>
	    <c:if test="${read.filename !=null}">
	    <tr>
	      <th>사진</th>
	      <td>
	      <img src="../storage/${read.filename}" width="100"><br>
	      
	    </tr>
	    </c:if>
	   	<tr>
	      <th>작성일</th>
	      <td>${fn:substring(read.wdate,0,10)}</td>
	     </tr>
	     <tr>
	      <th>수정일</th>
	      <td>${fn:substring(read.mdate,0,10)}</td>
	    </tr>
	     <tr>
	      <th>조회수</th>
	      <td>${read.wview}</td>
	    </tr>
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	    <input type='button' value='게시물 답글' onclick="location.href='reply.do?wno=${read.wno}';">
	       <input type='button' value='게시물 수정' onclick="location.href='update.do?wno=${read.wno}';">
	       <input type='button' value='게시물 삭제' onclick="location.href='delete.do?wno=${read.wno}';">
		  <input type="button" value="게시판 목록" onclick="location.href='list.do';">
		  <input type="button" value="HOME"   onclick="location.href='/home.do';">	
	    </div>    

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
