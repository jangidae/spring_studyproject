<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../ssi.jsp" %>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 insert.jsp -->

<form name="SGcreatefrm" id="SGcreatefrm" action="SGcreateProc.do" method="post" onsubmit="return SGcreateCheck()"> <!-- myscript.js -->
	<h4>스터디그룹 만들기</h4>
<%
	dto=dao.read((String)session.getAttribute("s_userid"));//로그인한 세션변수(전역변수/아이디)를 형변환(String)해서 넘김
	if(dto==null){
		out.print("그룹은 로그인 후 생성해주세요");
	}else{
		
%>
	<span style="color:red; font-weight: bold; font-size : 13pt;">* 필수입력</span>
	<br>
<table class="table">
<tr>
    <th> *그룹명 </th>
    <td style="text-align: left">
      <input type="text" name="SGname" id="SGname" size="30" readonly>
      <input type="button" value="그룹명 중복확인" onclick="SGCheck()"><!-- myscript.js -->
    </td>
</tr>
<tr>
	<th> 그룹장 </th>
	    <td style="text-align: left">
		<input type="text" name="userid" id="userid" size="15" value="<%=(String)session.getAttribute("s_userid") %>" readonly>
	 </td>
</tr>
<tr>
    <th>그룹 소개</th>
    <td style="text-align: left"><input type="text" name="SGintro" id="SGintro" size="30"></td>
</tr>
<tr>
    <th>최대 인원</th>
    <td style="text-align: left"><input type="text" name="max_user_number" id="max_user_number" maxlength="2" size="30"></td>
</tr>
<tr>  
  <th>카테고리</th>
  <td style="text-align: left">
        <select name="select_lang"  id="select_lang">
          <option value="0" selected>선택하세요.</option>
          <option value="A01">영어</option>
          <option value="A02">일본어</option>
          <option value="A03">중국어</option>
          <option value="A05">기타</option>
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

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>