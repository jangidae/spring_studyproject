<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title"><h4>게시물<h4></div>

		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				${read.ncode.split("-")[0]}
			</td>
		</tr>
	    <tr>
	      <th>제목</th>
	      <td>${read.wtitle}</td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" id="floatingTextarea" cols='30' rows='15' style='background:white; border:0;' readonly>
	      ${read.wcontent}</textarea></td>
	    </tr>
	    <!-- ${read.filename !=null} 아래와 같이 수정했습니다-->
	    <c:if test="${read.poster !=null}">
		    <tr>
		      <th><strong>사진</strong></th>
		      <td><!-- dto.poster가 아니라 read변수에 담았기 때문에 read.poster로 수정했습니다 
		               <img src="../storage/${dto.poster}" width="400">      -->
		      <img src="../storage/${read.poster}" width="400"><br>
		      </td>
		    </tr>
	    </c:if>
	   	<tr>
	      <th>작성일</th>
	      <!--  <td>${fn:substring(read.wdate,0,19)}</td>-->
	      <td>${read.wdate}</td> <!-- dto로도 불러봤는데 안떠요..  -->
	     </tr>
	   
	     <tr>
	      <th>조회수</th>
	      <td>${read.wview}</td>
	    </tr>
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	       <input type='button' value='수정' onclick="location.href='/Notice/noticeUpdate.do?wno=${read.wno}';">
	       <!-- 몇번글을 삭제할 것인지 글번호를 전달하지 않았습니다 -->
	       <input type='button' value='삭제' onclick="location.href='/Notice/noticeDel.do?wno=${read.wno}';">
		  <input type="button" value="목록" onclick="location.href='/Notice/noticeList.do';">
		  <input type="button" value="HOME"   onclick="location.href='/home.do';">	
	    </div>    

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>