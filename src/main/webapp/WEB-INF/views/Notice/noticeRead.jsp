<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title">게시물</div>

		<input type="hidden" name="wno" value="${dto.wno}"> 
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;'>
  					<option value="1">뉴스</option>
  					<option value="2" selected>공지</option>

				</select>
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td><input type='text' name='title' size='50' value="${dto.wtitle}"></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="Leave a comment here" id="floatingTextarea" cols='30' rows='15'>
	      ${dto.wcontent}</textarea></td>
	    </tr>
	    <tr>
	      <th>사진</th>
	      <td>
	      <img src="../storage/${dto.pictureMF}" width="100"><br>
	      <input class='form-control' id='formFile' type='file' name='pictureMF' size='50'></td>    
	    </tr>
	    <tr>
	      <th>미디어 파일</th>
	      <td>등록된 파일명 : ${dto.filename}<br>
	      <input  class='form-control' id='formFile' type='file' name='filenameMF' size='50'></td>    
	    </tr>  
	   
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	      <input type='button' value='게시물 수정' onclick="location.href='/Notice/noticeUpdate.do'">  
	      <input type='button' value='게시물 삭제' onclick="location.href='/Notice/noticeDel.do'">
		  <input type="button" value="게시판 목록" onclick="location.href='/Notice/noticeList.do?wno=${dto.wno}'">
		  <input type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
