<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  
  <title>스터디 그룹</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <link href="https://fonts.googleapis.com/css?family=Lato" rel="stylesheet" type="text/css">
  <link href="https://fonts.googleapis.com/css?family=Montserrat" rel="stylesheet" type="text/css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   <link rel="stylesheet" href="../css/project.css">
   <!-- Bootstrap -->
   <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"> <!-- 0708 적용-->
   <script src="../js/myscript_member.js"></script> <!-- 6/20 적용했습니다 -->
   <script src="../js/myscript.js"></script> <!-- 7/11 적용했습니다 -->
   <script src="../js/jquery-3.6.0.min.js"></script> <!-- 6/20 적용했습니다 -->
</head>
<body id="myPage" data-spy="scroll" data-target=".navbar" data-offset="50">

<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container-fluid">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
     
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="/home.do">메인</a></li>
         
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >공지사항<span class="caret"></span></a>
        <ul class="dropdown-menu">
        	<li><a href="/Notice/noticeList.do">공지사항</a></li>
        </ul>
       </li>
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >게시판<span class="caret"></span></a>
        <ul class="dropdown-menu">
       		<li><a href="/bbsLang/list.do">외국어 스터디 게시판</a></li>
            <li><a href="/bbsFree/list.do">자유 게시판</a></li> 
         </ul>
         </li>
          <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >스터디 그룹 게시판<span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li><a href="/studygroup/insert.do">그룹 만들기</a></li>
       		<li><a href="/studygroup/groupList.do">그룹 모집 </a></li>
            <li><a href="/studygroup/myGroupList.do">내 그룹 상세보기</a></li>  
         </ul>
         </li>
         <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >마이페이지<span class="caret"></span></a>
        <ul class="dropdown-menu">
       		<li><a href='../member/memberModifyForm.do'>내 정보 수정</a></li>
         </ul>
         </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">고객센터 <span class="caret"></span></a>
          <ul class="dropdown-menu">
          	<li><a href="/center/list.do">신고하기</a></li>
            <li><a href="/fqa/ansForm.do">FAQ</a></li>
       
          </ul>
        </li>
<li>

<%
		//로그인하면 헤더의 로그인 버튼이 로그아웃 버튼으로 바뀌도록 함
        if (session.getAttribute("s_userid")==null||session.getAttribute("s_upw")==null||session.getAttribute("s_ulevel")==null){ 	
			out.println("<a href='../member/loginform.do'>로그인</a>");
        }else {
        	out.println("<a href='../member/logout.do'>로그아웃</a>");
        }//if end
%>
     	</li>
        
      </ul>
    </div>
  </div>
</nav>

<!-- Container (The Band Section) -->
<div id="band" class="container text-center">
  <div class="row">
  
  
  