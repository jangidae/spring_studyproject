<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<h4>알림</h4>
	<div class="content">
		<dl>
			<dd>${msg != null ? img : ""} ${msg}</dd>
		</dl>
		<p>
			${link1}
			${link2}			
		</p>	
	</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>    
    
    
    
    
