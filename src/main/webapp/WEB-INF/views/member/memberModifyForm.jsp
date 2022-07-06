<%@page import="kr.co.studyproject.Member.MemberDTO"%>
<%@page import="kr.co.studyproject.Member.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../ssi.jsp" %>
<%@ include file="../header.jsp" %>

<!-- 본문 시작 template.jsp -->

<div class="container">
	<h3> 회원정보 수정 </h3>
<%
	dto=dao.read((String)session.getAttribute("s_userid"));//로그인한 세션변수(전역변수/아이디)를 형변환(String)해서 넘김
													   //auth.jsp 에서 s_userid 끌어와 쓰는 것도 가능
	if(dto==null){
		out.print("회원정보 없음");
	}else{
		
%>
	<form name="modifrm" id="modifrm" method="post" action="memberModifyProc.do" onsubmit="return memberCheck()"><!-- myscript.js -->
	<span style="color:red; font-weight: bold">* 필수입력</span>
	<br>
	<table class="table">
	<tr>
	    <th> 아이디 </th>
	    <td style="text-align: left">
		      <input type="text" name="userid" id="userid" size="15" value="<%=(String)session.getAttribute("s_userid") %>" readonly> ID수정불가
		      <!-- id는 수정 불가능-->
	    </td>
	</tr>
	<tr>
	    <th>*비밀번호</th>
	    <td style="text-align: left"><input type="password" value="<%=dto.getUpw() %>" name="upw" id="upw" size="15" required></td>
	</tr>
	<tr>
	    <th>*이름</th>
	    <td style="text-align: left"><input type="text" value="<%=dto.getUname() %>" name="uname" id="uname" size="15" maxlength="20" required></td>
	</tr>
	<tr>
	    <th>이메일</th>
	    <td style="text-align: left">
	      <input type="email" value="<%=dto.getEmail() %>" name="email" id="email" size="30" placeholder=" ' @ ' 포함해서 입력" readonly required> E-mail수정불가
	    </td>
	</tr>
	<tr>
	    <th>*전화번호</th>
	    <td style="text-align: left"><input type="text" value="<%=dto.getPhnum() %>" name="phnum" id="phnum" size="15" placeholder="' - ' 없이 입력" maxlength="11" required></td>
	</tr>
	<tr>
	    <th>한 줄 소개</th>
	    <td style="text-align: left">
	      <input type="text"  value="<%=dto.getIntrod() %>" name="introd" id="introd" size="40">
	    </td>
	</tr>
	<tr>
	    <td colspan="2">
	    	<!-- <input type="button" onclick="memberWithdrawForm.do"  value="회원탈퇴"   class="btn btn-primary"/>
	    	     호출하려는 명령어 문법이 틀렸습니다
	    	 -->
	    	<input type="button" onclick="location.href='memberWithdrawForm.do'"  value="회원탈퇴"   class="btn btn-primary"/>
	    	
	        <input type="reset"  value="취소"       class="btn btn-primary"/>
	        <input type="submit" value="회원정보수정" class="btn btn-primary"/>
	    </td>
	</tr>
	</table>
	

	
	</form>
	
		
<%
		
	}//if end
%>

</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>