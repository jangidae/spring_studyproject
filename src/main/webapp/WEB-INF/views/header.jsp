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
   <script src="../js/myscript_member.js"></script> <!-- 6/20 적용했습니다 -->
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
        	<li><a href="#">공지사항</a></li>
        </ul>
       </li>
        <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >게시판<span class="caret"></span></a>
        <ul class="dropdown-menu">
       		<li><a href="#">언어별 스터디 게시판</a></li>
            <li><a href="#">자료 공유 게시판</a></li>
            <li><a href="#">자유 게시판</a></li> 
         </ul>
         </li>
         <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >스터디 그룹 게시판<span class="caret"></span></a>
        <ul class="dropdown-menu">
       		<li><a href="#">그룹 구해요</a></li>
            <li><a href="#">멤버 구해요</a></li>
            <li><a href="#">내 그룹 게시판</a></li> 
         </ul>
         </li>
         <li class="dropdown">
        <a class="dropdown-toggle" data-toggle="dropdown" href="#" >마이페이지<span class="caret"></span></a>
        <ul class="dropdown-menu">
       		<li><a href="#">내 정보</a></li>
            <li><a href="#">내 그룹정보</a></li>
            <li><a href="#">내 다이어리</a></li>
            <li><a href="#">내 활동정보</a></li> 
         </ul>
         </li>
        <li class="dropdown">
          <a class="dropdown-toggle" data-toggle="dropdown" href="#">고객센터 <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="#">건의사항</a></li>
            <li><a href="#">자주 묻는 질문</a></li>
            <li><a href="#">신고하기</a></li> 
          </ul>
        </li>
     		 <li><a href="loginform.do">로그인</a></li>
        <li><a href="#"><span class="glyphicon glyphicon-search"></span></a></li>
      </ul>
    </div>
  </div>
</nav>

<!-- Container (The Band Section) -->
<div id="band" class="container text-center">
  <div class="row">
  
  
  