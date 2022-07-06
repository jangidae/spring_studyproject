<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<div class="title">게시물 삭제</div>
    <form name="frm" method="post" action="noticeDel.do">    
    <!-- 아래 소스를 수정하겠습니다
          <input type="hidden" name="wno" value=${request.getparameter("wno")}>
     -->
     <!-- NoticeController.java에서 delete_get() 함수 참조 -->
     <input type="hidden" name="wno" value="${requestScope.wno}">
		<div class="content">
			<p>게시물을 삭제하시겠습니까?</p>
			<p>※ 관련 첨부파일도 전부 삭제됩니다</p>
		</div>
		
	    <div class='bottom'>
	      <input type='submit' value='삭제 완료' >
	      <input type='button' value='HOME' onclick="location.href='/home.do'">
	    </div>   
    </form>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
