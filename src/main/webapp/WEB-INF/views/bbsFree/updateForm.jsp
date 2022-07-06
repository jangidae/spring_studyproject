<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title">게시물 수정</div>
<form name="frm" method="post" action="update.do" enctype="multipart/form-data">
		<input type="hidden" name="wno"      value="${update.wno}">    
		<input type="hidden" name="tmpfile"      value="${update.filename}"> 
		<input type="hidden" name="tmpsize"      value="${update.filesize}"> 
		<table class='table'>
		<tr>
			<th> 카테고리 </th>
			<td> 
				<select class="form-select" aria-label="Default select example" style='width:710px;' name='ccode'>
  					<option selected>카테고리를 선택해주세요</option>
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
	      <td><input type='text' name='wtitle' size='50' value="${update.wtitle}"></td>
	    </tr>
	    <tr>
	      <th>내용</th>
	      <td><textarea class="form-control" placeholder="내용을 입력해주세요" id="floatingTextarea" cols='30' rows='15' name='wcontent'>
	      ${update.wcontent}</textarea></td>
	    </tr>
	     <c:if test="${update.filename !=null }">
	    <tr>
	      <th>사진</th>
	      <td>
	      <img src="../storage/${update.filename}" width="100"><br>
	      <input class='form-control' id='formpic' type='file' name='file' size='50'></td>    
	    </tr>
	    </c:if>
	    <tr>
	    	<th>비밀번호</th>
	    	<td style='text-align:left;'>
	    		<input type='password' name='wpasswd' size='10' style='font-size:15pt; height:30px;'>
	    	</td>
	    </tr>
	    </table>

	    <div class="btn-group" role="group" aria-label="Basic example">
	       <input type="button" value="수정 완료" onclick="chk()">
		  <input type="button" value="목록" onclick="location.href='list.do'">
		  <input type="button" value="HOME"   onclick="location.href='/home.do'">	
	    </div>  
	</form>
<script>
	function chk(){
		let flag = confirm("수정 하시겠습니까? ");
		if(flag) document.frm.submit();
	}
</script>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
<script>
	let sel_code = document.querySelectorAll(".form-select")[0];
	let code='${update.ccode}'.toLowerCase();
	let scode = ["hu","st","re","li","sh"];
	i=0;
	for(i=0; i<scode.length; i++){
		if(code.indexOf(scode[i])!=-1 )
			break;
	}
	sel_code[i].selected=true;
</script>