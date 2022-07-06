<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- auth.jsp 공통코드 --%>
<%-- 로그인 상태 정보 확인 --%>
<%
	String s_userid="";
	String s_upw="";
	String s_ulevel="";
	
	if(session.getAttribute("s_userid")==null||session.getAttribute("s_upw")==null||session.getAttribute("s_ulevel")==null){//session.setAttribute()를 가져왔는데 null일때
		//ㄴ로그인하지않은 경우 변수에 게스트 넣기
		s_userid="guest";
		s_upw="guest";
		s_ulevel="guest";
	}else{
		//ㄴ로그인에 성공한 경우 변수에 세션값
		s_userid=(String)session.getAttribute("s_userid");
		s_upw=(String)session.getAttribute("s_upw");
		s_ulevel=(String)session.getAttribute("s_ulevel");
	}//if end










%>