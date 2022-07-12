<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 insert.jsp -->

<h4>스터디그룹 만들기</h4>
<%
	//dto=dao.read((String)session.getAttribute("s_userid"));//로그인한 세션변수(전역변수/아이디)를 형변환(String)해서 넘김
	if(session.getAttribute("s_userid")==null||session.getAttribute("s_upw")==null||session.getAttribute("s_ulevel")==null){
		out.print("그룹은 로그인 후 생성해주세요");
	}else{		
%>
		<form name="SGcreatefrm" id="SGcreatefrm" action="SGcreateProc.do" method="post" onsubmit=""> <!-- myscript.js -->
		<br>
		<table class="table">
		<tr>
		    <th> 그룹명 </th>
		    <td style="text-align: left">
		      <input type="text" name="sgname" id="sgname" maxlength="30" size="30">
		    </td>
		</tr>
		<tr>
			<th> 그룹장 </th>
			    <td style="text-align: left">
				<input type="text" name="sgleader" id="sgleader" size="15" value="<%=(String)session.getAttribute("s_userid") %>" readonly>
			 </td>
		</tr>
		<tr>
		    <th> 그룹 소개 </th>
		    <td style="text-align: left"><input type="text" name="sgintro" id="sgintro" size="30"></td>
		</tr>
		<tr>
		    <th> 최대 인원 </th>
		    <td style="text-align: left"><input type="text" name="sgmaxuserno" id="sgmaxuserno" maxlength="1" size="15"></td>
		</tr>
		<tr>  
		  <th> 카테고리 </th>
		  <td style="text-align: left">
		        <select name="sgselectlang"  id="sgselectlang">
		          <option value="0" selected>선택하세요.</option>
		          <option value="영어">영어</option>
		          <option value="일본어">일본어</option>
		          <option value="중국어">중국어</option>
		          <option value="기타">기타</option>
		        </select>
		  </td>
		</tr>
		<tr>
		    <td colspan="2">
		        <input type="submit" value="그룹만들기"  class="btn btn-primary"/>
		        <input type="reset"  value="취소"      class="btn btn-primary"/>
		    </td>
		</tr>
		</table>
		</form>
<%}//if end
	 %>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>