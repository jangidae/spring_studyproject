<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<h4>게시물 수정</h4>
<form name="frm" method="post" action="update.do" enctype="multipart/form-data" onsubmit="return bbsCheck1()">
		<input type="hidden" name="wno"      value="${update.wno}">    
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;' name='lcode'>
  					<option value="0">카테고리를 선택해주세요</option>
  					<option value="ENG001">영어</option>
  					<option value="TOE001">토익</option>
  					<option value="TOF001">토플</option>
  					<option value="JAP001">일본어</option>
  					<option value="JLP001">일본어자격증</option>
  					<option value="CHI001">중국어</option>
  					<option value="HSK001">중국어자격증</option>
  					<option value="STU001">기타</option>
				</select>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' id='wtitle' name='wtitle' size='50' value="${update.wtitle}"></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="내용을 입력해주세요" id="wcontent" cols='30' rows='15'name='wcontent'>
	      ${update.wcontent}</textarea></td>
	    </tr>
	    <tr>
	    	<th>비밀번호</th>
	    	<td style='text-align:left;'>
	    		<input type='password' id='wpasswd' name='wpasswd' size='10' style='font-size:15pt; height:30px;'>
	    	</td>
	    </tr>
	   
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	       <input type='submit' value='수정 완료'>
		  <input type="button" value="목록" onclick="location.href='list.do'">
		  <input type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  
	</form>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
