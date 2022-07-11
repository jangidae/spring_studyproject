<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<h4>게시물</h4>
   
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				${read.lcode.split("-")[0]}
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
	      <td><textarea class="form-control" placeholder="내용을 입력해주세요" id="floatingTextarea" cols='30' rows='15' style='background:white; border:0;' readonly>
	      ${read.wcontent}</textarea></td>
	    </tr>
	     <tr>
	      <th>작성일</th>
	      <td>${fn:substring(read.wdate,0,19)}</td>
	     </tr>
	     <tr>
	      <th>수정일</th>
	      <td>${fn:substring(read.mdate,0,19)}</td>
	    </tr>
	     <tr>
	      <th>조회수</th>
	      <td>${read.wview}</td>
	    </tr>
	   		
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	    <input type='button' value='댓글' onclick="location.href='reply.do?wno=${read.wno}';">
	       <input type='button' value='수정' onclick="location.href='update.do?wno=${read.wno}';">
	       <input type='button' value='삭제' onclick="location.href='delete.do?wno=${read.wno}';">
		  <input type="button" value="목록" onclick="location.href='list.do';">
		  <input type="button" value="HOME"   onclick="location.href='/home.do';">	
	    </div>  

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>


