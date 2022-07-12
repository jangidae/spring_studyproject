<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->

<style>
	.answer {
		display: none;
	}
	
	.answer.on {
		display: block;
	}
	
	.qBtn {
		margin-top : 10px;
	}
</style>

<h4>F A Q</h4>

	<div class="box">
		<button class="qBtn" type="button">스터디 멤버들과 불화나 분쟁이 발생하였을 경우</button>
		<div class="answer">
			<div class="title">현재 스터디하시2조 운영방침 상 유저 간 발생하는 분쟁에 관련하여 적극적으로 개입하거나
	 조치할 의무는 없기에 따로 조치를 취해 드리지 못하는 점 양해 부탁드립니다.</div>
		</div>
	</div>
	
	<div class="box">	
		<button class="qBtn" type="button">스터디하시2조 계정 탈퇴</button>
		<div class="answer">
			<div class="title">탈퇴하시게 되면 그 동안 스터디하시2조에 제공한 모든 정보가 삭제됩니다.
	한번 탈퇴가 진행되면 복구는 절대 불가능하며  신중하게 진행 부탁드립니다.</div>
		</div>
	</div>
	<div class="box">	
		<button class="qBtn" type="button">스터디하시2조 스터디원을 모집해 보세요.</button>
		<div class="answer">
			<div class="title">스터디하시2조 스터디그룹 게시판에서 스터디원을 모집할 수 있습니다.</div>
		</div>
</div>

<script>
	$(document).ready(function() {
		$(".qBtn").click(function() {
			
					//this = 버튼 siblings=형제 on값을 갖고 있는지 물어보는거
			if ($(this).siblings(".answer").hasClass("on")) {
				// hasClass -> 클래스명을 가지고 있냐? 물어보는거
				$(".answer").removeClass("on");   //있으면 지우ㅝ버림 리무브클래스
			} else {
				$(".answer").removeClass("on");
				$(this).siblings(".answer").addClass("on");	
			}
			// qbtn이 이걸 클릭하는거
			// if this =qbtn 이걸 클릭하면, 형제인 답값 찾아, 그래서 
			
			
		});
	});
</script>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
