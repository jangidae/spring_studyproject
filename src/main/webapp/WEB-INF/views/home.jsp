<%@page import="org.springframework.web.bind.annotation.SessionAttribute"%>
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
  <!-- Bootstrap -->
   <link href="bootstrap-3.3.7/css/bootstrap.min.css" rel="stylesheet"> <!-- 0708 적용-->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="./css/project.css">
  <script src="./js/myscript_member.js"></script> <!-- 6/20 적용했습니다 -->
  <script src="./js/myscript.js"></script> <!-- 7/11 적용했습니다 -->
  
  
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
        	<li><a href="Notice/noticeList.do">공지사항</a></li>
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
       		<li><a href='./member/memberModifyForm.do'>내 정보 수정</a></li> 
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
			out.println("<a href='./member/loginform.do'>로그인</a>");
        }else {
        	out.println("<a href='./member/logout.do'>로그아웃</a>");
        }//if end
%>
     	</li>
     	
      </ul>
    </div>
  </div>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
      <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
        <img src="./images/intro.jpg"  alt="intro" style="width:1500px; height:500px;">
        <div class="carousel-caption">
          <img src="./images/logo2.png" alt="logologo"style="width:400px; height:400px;">
          <h3></h3>
          <p></p>
        </div>      
      </div>

      <div class="item">
         <img src="./images/intro2.jpg"  alt="intro2" style="width:1500px; height:500px;">
         
        	<div class="carousel-caption">
        
          <p>다함께 언어 능력을 향상시켜보세요</p>
        </div>      
      </div>
    
      <div class="item">
         <img src="./images/intro3.png"  alt="intro3" style="width:1500px; height:500px;">
        <div class="carousel-caption">
          <div>
          	<p>화면 속 친구와 함께 공부해요</p>
          </div>
            
        </div>      
      </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">Next</span>
    </a>
</div>

<!-- Container (The Band Section) -->
<div id="band" class="container text-center">
  <h3></h3>
  <p><em></em></p>
  <p></p>
  <br>
  <div class="row">
    <div class="col-sm-4">
      <p class="text-center"><strong>공지사항</strong></p><br>
      <a href="Notice/noticeList.do">
        <img src="./images/languagestudy.png"  class="img-circle person" alt="Random Name" width="255" height="255">
      </a>
    </div>
    <div class="col-sm-4">
      <p class="text-center"><strong>자유게시판</strong></p><br>
      <a href="/bbsFree/list.do">
        <img src="./images/Languages.png"  class="img-circle person" alt="Random Name" width="255" height="255">
      </a>
    </div>
    <div class="col-sm-4">
      <p class="text-center"><strong>마이페이지</strong></p><br>
      <a href='./member/memberModifyForm.do'>
        <img src="./images/data.png"  class="img-circle person" alt="Random Name" width="255" height="255">
      </a>
    </div>
  </div><!-- row 끝 -->

  
  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">×</button>
          <h4><span class="glyphicon glyphicon-lock"></span> Tickets</h4>
        </div>
        <div class="modal-body">
          <form role="form">
            <div class="form-group">
              <label for="psw"><span class="glyphicon glyphicon-shopping-cart"></span> Tickets, $23 per person</label>
              <input type="number" class="form-control" id="psw" placeholder="How many?">
            </div>
            <div class="form-group">
              <label for="usrname"><span class="glyphicon glyphicon-user"></span> Send To</label>
              <input type="text" class="form-control" id="usrname" placeholder="Enter email">
            </div>
              <button type="submit" class="btn btn-block">Pay 
                <span class="glyphicon glyphicon-ok"></span>
              </button>
          </form>
        </div>
        <div class="modal-footer">
          <button type="submit" class="btn btn-danger btn-default pull-left" data-dismiss="modal">
            <span class="glyphicon glyphicon-remove"></span> Cancel
          </button>
          <p>Need <a href="#">help?</a></p>
        </div>
      </div>
    </div>
  </div><!-- Modal 끝 -->
</div><!-- band끝 -->

<!-- Footer -->
<footer class="text-center">
  <a class="up-arrow" href="#myPage" data-toggle="tooltip" title="TO TOP">
    <span class="glyphicon glyphicon-chevron-up"></span>
  </a><br><br>
  <p>스터디하시2조 이덕모 장이대 문하빈 임채린<a href="https://www.w3schools.com" data-toggle="tooltip"></a></p> 
</footer>

<script>
$(document).ready(function(){
  // Initialize Tooltip
  $('[data-toggle="tooltip"]').tooltip(); 
  
  // Add smooth scrolling to all links in navbar + footer link
  $(".navbar a, footer a[href='#myPage']").on('click', function(event) {

    // Make sure this.hash has a value before overriding default behavior
    if (this.hash !== "") {

      // Prevent default anchor click behavior
      event.preventDefault();

      // Store hash
      var hash = this.hash;

      // Using jQuery's animate() method to add smooth page scroll
      // The optional number (900) specifies the number of milliseconds it takes to scroll to the specified area
      $('html, body').animate({
        scrollTop: $(hash).offset().top
      }, 900, function(){
   
        // Add hash (#) to URL when done scrolling (default click behavior)
        window.location.hash = hash;
      });
    } // End if
  });
})
</script>

</body>
</html>