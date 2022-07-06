<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

    <div class="title">게시글 등록</div>
	<form name="frm" method="post" action="create.do" enctype="multipart/form-data">
		<%-- <input type="hidden" name="wgrpno" value="${requestScope.wgrpno}"> --%>
		<table class='table'>
		<tr>
			<th> 신고접수 </th>
			<td> 
				<select class="form-select" name="lcode" aria-label="Default select example" style='width:710px;'>
  					<option selected>선택 하세요</option>
  					<option value="1">욕설 및 공격적 언행 신고</option>
  					<option value="2">비매너 신고</option>
  					<option value="3">성희롱 신고</option>
  					<option value="4">다른 문제가 있어요</option>
				</select>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' name='wtitle' size='50'></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" name='wcontent' placeholder="Leave a comment here" id="floatingTextarea" cols='30' rows='15'></textarea></td>
	    </tr>
	    <tr>
	    <tr>
	    	<th>비밀번호</th>
	    	<td style='text-align:left;'>
	    		<input type='password' name='' size='10' style='font-size:15pt; height:30px;'>
	    	</td>
	    </tr>
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	      <input type='submit' value='작성완료' class="btn btn-primary">
		  <input  class="btn btn-primary" type="button" value="목록" onclick="location.href='list.do?wgrpno=${requestScope.wgrpno}'">
		  <input  class="btn btn-primary" type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  
	</form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>