<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

    <div class="title">게시글 등록</div>
	<form name="frm" method="post" action="create.do" >
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;' name='lcode'>
  					<option selected>Select Category</option>
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
	      <td><input type='text' name='wtitle' size='50'></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea name='wcontent' class="form-control" placeholder="Leave a comment here" id="floatingTextarea" cols='30' rows='15'></textarea></td>
	    </tr>  
	    <tr>
	    	<th>비밀번호</th>
	    	<td style='text-align:left;'>
	    		<input type='password' name='wpasswd' size='10' style='font-size:15pt; height:30px;'>
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




