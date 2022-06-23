

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!--  본문 시작 pageForm.jsp -->    
<!DOCTYPE html>
<h2>Q * A </h2>
<html>
<head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
$(document).ready(function(){
  $("button").click(function(){
    $("p").toggle();
  });
});
</script>
</head>
<body>


<button>스터디 하시2조</button>

<p>열심히 잘 해 보겠습니다</p>

<button>스터디 하시2조</button>

<p>열심히 잘 해 보겠습니다</p>
</body>
</html>

<div></div>






	

<!--   본문 끝 -->
<%@ include file="../footer.jsp" %>