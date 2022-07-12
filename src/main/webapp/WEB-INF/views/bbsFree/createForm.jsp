<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

    
    <h4>게시글 등록</h4>
	<form name="frm" method="post" action="create.do" enctype="multipart/form-data" onsubmit="return bbsCheck2()">
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;' id='ccode' name='ccode'>
  					<option value="0">카테고리를 선택해주세요</option>
  					<option value="HU001">유머</option>
  					<option value="ST001">공부</option>
  					<option value="RE001">후기</option>
  					<option value="LI001">자격증</option>
  					<option value="SH001">자료공유</option>
				</select>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' id='wtitle' name='wtitle' size='50'></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="내용을 입력해주세요" id="wcontent" cols='30' rows='15' name='wcontent'></textarea></td>
	    </tr>
	    <tr>
	      <th>사진</th>
	      <td><input class='form-control' id='formpic' type='file' name='file' size='50'></td>    
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




