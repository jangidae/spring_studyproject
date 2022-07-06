<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작 template.jsp -->


	<div class="section" id="make" style="margin-top:50px; margin-bottom:70px;">
		<div class="section-center">
			<div class="container" >
				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-7 col-md-push-5">
						<div class="form-group studygroupUpdate booking-form">
							<h3 class="form-label">스터디그룹 만들기</h3><br><br>
							<form action="/studyGroup/insert" method="post" enctype="multipart/form-data">
								<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
								<span class="form-label">그룹명</span> <input type="text" name="studygroup_name" class="form-control"  required="required"> <br>
								<span class="form-label">그룹 소개</span> <input type="text" name="studygroup_info" class="form-control"  required="required"> <br>
								<br><br> 
								<span class="form-label">최대 인원</span>
								<input type="text" name="max_user_number" class="form-control" required="required"> <br>
									<br><br>
								<span class="form-label">카테고리</span>
								 <select class="form-control" name="category_no" required="required">
											<option disabled="disabled" selected="selected">선택하세요.</option>
											<c:forEach var="cl" items="${categoryList}">
												<option value="${cl.category_no}">${cl.category_name}</option>
											</c:forEach>
								</select> <br>

								<div class="inputArea">
									<label for="img"><span class="form-label">이미지</span></label> <input type="file" id="img"
										name="file" class="form-control form-btn"  required="required"/>
									<div class="select_img">
										<img src="" />
									</div>

								</div>

								<input type="hidden" name="user_no"
									value="${sessionScope.user.user_no}"> <input
									type="submit" value="그룹만들기" class="submit-btn">
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>