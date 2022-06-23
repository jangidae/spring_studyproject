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
		<h3> 이메일 중복확인 </h3>
		<form action="emailCheckProc.jsp" onsubmit="return blankCheck1()">
			이메일 : <input type="text" name="email" id="email" autofocus>
				   <input type="submit" value="중복확인">
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
			return true;
	}// blankCheck1() end
		
	</script>

</body>
</html>