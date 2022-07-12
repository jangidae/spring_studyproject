<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

    <div class="title"><h4>댓글 등록</h4></div>
	<form name="frm" method="post" action="reply.do">
	<input type='hidden' name='wnum' 	value='${read.wno}'>            <!-- 부모글번호 -->
	<input type='hidden' name='windent' value='${read.windent + 1}'> 	<!-- 부모글들여쓰기0, 자식글들여쓰기1-->
	<input type='hidden' name='wnum' 	value='${read.wnum}'> 			<!-- 그룹번호 -->
	<input type='hidden' name='wcode' 	value="${read.wcode.split("-")[1] }">
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<input value='${read.wcode.split("-")[0]}' readonly>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' id='wtitle' name='wtitle' size='50'></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea name='wcontent' class="form-control" placeholder="내용을 입력해주세요" id="wcontent" cols='30' rows='15'></textarea></td>
	    </tr>  
	    <tr>
	    	<th>비밀번호</th>
	    	<td style='text-align:left;'>
	    		<input type='password' id='wpasswd' name='wpasswd' size='10' style='font-size:15pt; height:30px;'>
	    	</td>
	    </tr>
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	      <input type='submit' value='작성완료' class="btn btn-primary">
		  <input  class="btn btn-primary" type="button" value="목록" onclick="location.href='list.do';">
		  <input  class="btn btn-primary" type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  
	</form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>




