<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>emailCheckForm.jsp</title>
</head>
<body>
	<div style="text-align: center">
		<h4> 이메일 중복확인 </h4>
		<form id="emailChkForm" method="post" action="/member/emailCheckproc.do">
			이메일 : <input type="text" name="email" id="email" autofocus>
				   <input type="submit" onClick="blankCheck1()" value="중복확인">
		</form>
	
	</div>
	
	<script>
	function blankCheck1() {
		var email=document.getElementById("email").value;
		email=email.trim();                               
		if(email.length<1){
		    alert("이메일을 올바르게 입력해주세요");   
	    	return false;                                 
		}//if end
			
		document.getElementById("emailChkForm").submit();
		
	}// blankCheck1() end
		
	</script>

</body>
</html>