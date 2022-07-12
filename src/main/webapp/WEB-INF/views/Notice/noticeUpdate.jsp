<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->

<div class="title"><h4>게시물 수정</h4></div>
<form name="frm" method="post" action="/Notice/noticeUpdate.do" enctype="multipart/form-data" onsubmit="return noticeCheck()">
	<input type="hidden" name="wno" value="${read.wno}">
	<input type="hidden" name="tmpfile" value="${update.filename}"> 
	<input type="hidden" name="tmpsize" value="${update.filesize}">
	<table class='table'>
			<tr>
				<th>카테고리</th>
				<td><select class="form-select" aria-label="Default select example" style='width: 710px;' name='ncode'>
						<option value="0">카테고리를 선택해주세요</option>
						<option>공지</option>
						<option>뉴스</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>제목</th>
				<td>
					<input type='text' id='wtitle' name='wtitle' size='50' value="${read.wtitle}"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td>
					<textarea name='wcontent' class="form-control" placeholder="내용을 입력해주세요" id="wcontent" cols='30' rows='15'>${read.wcontent}</textarea>
				</td>
			</tr>
			<tr>
				<th>사진</th>
				<td>
					<img src="../storage/${read.poster}" width="100"><br>
					<input type='file' id='poster' name='poster' size='50'>
				</td>
			</tr>
			<tr>
				<th>미디어 파일</th>
				<th>등록된 파일명 : ${read.filename}<br> <input type='file' name='filenameMF' size='50'>
				</th>
			</tr>
		</table>

		<div class="btn-group" role="group" aria-label="Basic example">
			<input type='button' value='수정 완료' onclick="chk()"> 
			<input type="button" value="목록" onclick="location.href='/Notice/noticeList.do'"> 
			<input type="button" value="HOME" onclick="location.href='/home.do'">
		</div>
</form>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>