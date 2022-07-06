<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<form name="joinfrm" id="joinfrm" action="joinproc.do" method="post" onsubmit="return memberCheck()"> <!-- myscript_member.js -->
	<h4>회원가입</h4>
	<span style="color:red; font-weight: bold; font-size : 13pt;">* 필수입력</span>
	<br>
	<table class="table">
	<tr>
	    <th> *아이디 </th>
	    <td style="text-align: left">
	      <input type="text" name="userid" id="userid" size="15" readonly required>
	      <input type="button" value="ID중복확인" onclick="idCheck()"><!-- myscript_member.js -->
	    </td>
	</tr>
	<tr>
	    <th>*비밀번호</th>
	    <td style="text-align: left"><input type="password" name="upw" id="upw" size="15" required></td>
	</tr>
	<tr>
	    <th>*이름</th>
	    <td style="text-align: left"><input type="text" name="uname" id="uname" size="15" maxlength="20" required></td>
	</tr>
	<tr>
	    <th>*이메일</th>
	    <td style="text-align: left">
	      <input type="email" name="email" id="email" size="30" placeholder=" ' @ ' 포함해서 입력" readonly required>
	      <input type="button" value="Email중복확인" onclick="emailCheck()"><!-- myscript_member.js -->
	    </td>
	</tr>
	<tr>
	    <th>*전화번호</th>
	    <td style="text-align: left"><input type="text" name="phnum" id="phnum" size="15" placeholder="' - ' 없이 입력" maxlength="11" required></td>
	</tr>
	<tr>
	    <th>한 줄 소개</th>
	    <td style="text-align: left">
	      <input type="text" name="introd" id="introd" size="40">
	    </td>
	</tr>
	<tr>
    <td colspan="2">
        <input type="submit" value="회원가입"  class="btn btn-primary"/>
        <input type="reset"  value="취소"      class="btn btn-primary"/>
    </td>
	</tr>
	</table>
</form>




<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>