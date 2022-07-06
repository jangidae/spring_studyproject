<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

    <div class="title">게시글 등록</div>
	<form name="frm" method="post" action="create.do" enctype="multipart/form-data">
		<input type="hidden" name="wno" value="${requestScope.wno}">
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;'>
  					<option selected>Select Category</option>
  					<option value="1">Category1</option>
  					<option value="2">Category2</option>
  					<option value="3">Category3</option>
  					<option value="3">Category4</option>
  					<option value="3">Category5</option>
  					<option value="3">Category6</option>
				</select>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' name='title' size='50'></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" cols='30' rows='15'></textarea></td>
	    </tr>
	    <tr>
	      <th>사진</th>
	      <td><input class='form-control' id='formFile' type='file' name='pictureMF' size='50'></td>    
	    </tr>
	    <tr>
	      <th>미디어 파일</th>
	      <td><input  class='form-control' id='formFile' type='file' name='filenameMF' size='50'></td>    
	    </tr>  
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
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	      <input type='submit' value='작성완료' class="btn btn-primary">
		  <input  class="btn btn-primary" type="button" value="목록" onclick="location.href='list.do?wno=${requestScope.wno}'">
		  <input  class="btn btn-primary" type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  
	</form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>