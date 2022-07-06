<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>idCheckForm.jsp</title>
</head>
<body>
	<div style="text-align: center">
		<h3> 아이디 중복확인 </h3>
		<form id="idChkForm" method="post" action="/member/idCheckproc.do">
			아이디 : <input type="text" name="userid" id="userid" maxlength="10" autofocus>
				   <input type="button" onClick="blankCheck()" value="중복확인">
		</form>
	
	</div>
	
	<script>
		function blankCheck() {
			
			var userid=document.getElementById("userid").value;
			userid=userid.trim();                               
			if(userid.length<5){
			    alert("아이디는 5~10글자 이내로 입력해주세요");   
		    	return false;                                 
			}//if end
			
			document.getElementById("idChkForm").submit();
			
		}// blankCheck() end
		
	</script>

</body>
</html>