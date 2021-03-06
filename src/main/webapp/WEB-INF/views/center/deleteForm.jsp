<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title"><h4>게시물 삭제</h4></div>
    <form name="frm" method="post" action="delete.do">
    	<input type="hidden" name="wno" value="${param.wno}">
		<div class="content">
			<p>게시물을 삭제하시겠습니까?</p>
			<p>※ 관련 첨부파일도 전부 삭제됩니다</p>
		</div>
		<div class="content">
	    	<b>비밀번호</b>
	    	<input type='password' name='wpasswd' size='10' style='font-size:15pt; height:30px;'>
	    </div>
				
	    <div class='bottom'>
	      <input type='button' value='삭제 완료' onclick="chk()">
	      <input type='button' value='HOME' onclick="location.href='/home.do'">
	    </div>   
    </form>
<script>
function chk(){
	let flag = confirm("삭제 하시겠습니까? ");
	if(flag) document.frm.submit();
}
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>