<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../header.jsp"%>
<!-- 본문 시작 template.jsp -->

<div class="w3-content w3-container w3-margin-top">
	<div class="w3-container w3-card-4">
		<form id=findIDfrm name=findIDfrm action="findID.do" method="post" onsubmit="return findIDCheck()"><!-- myscript.js -->
			<div class="w3-center w3-large w3-margin-top">
				<h4>아이디/비밀번호 찾기</h4><!-- 입력받은 이름과 이메일이 서버 상의 것과 일치하면 메일로 등록된 아이디, 임시비밀번호 발급하여 보내주기 -->
			</div>
			<div>
				<p>
					<label>이름</label>
					<input class="w3-input" type="text" id="uname" name="uname" required>
				</p>
				<p>
					<label>Email</label>
					<input class="w3-input" type="text" id="email" name="email" required>
				</p>
				<p class="w3-center">
					<button type="button" onclick="history.go(-1);"
						class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-margin-bottom w3-round">취소</button>
					<button type="submit" id=findBtn
						class="w3-button w3-block w3-black w3-ripple w3-margin-top w3-round">아이디/비밀번호 찾기</button>
				</p>
			</div>
		</form>
	</div>
</div>


<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>