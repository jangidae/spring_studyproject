<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ include file="../header.jsp"%>
    
<%-- ssi.jsp 공통코드--%>

<%@ page import="java.sql.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%@ page import="net.utility.*"%>
<%@ page import="kr.co.studyproject.Member.*"%>

<jsp:useBean id="dao" class="kr.co.studyproject.Member.MemberDAO" scope="page"></jsp:useBean>    
<jsp:useBean id="dto" class="kr.co.studyproject.Member.MemberDTO" scope="page"></jsp:useBean>

<%request.setCharacterEncoding("UTF-8");%>


<%@ include file="../footer.jsp"%>