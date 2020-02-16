<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page ="../mainActivity/navbar.jsp"/>
<meta charset="UTF-8">
<script src="js/writeform2.js" charset="UTF-8"></script>
<title>리뷰 수정 페이지</title>
<style>
.container {
	margin : 0 auto;
	width : 70%;
	height : 70%;
	background : white;
	margin-top : 100px;
}
label {
	color : black !important;
}
h1 {
	font-size : 1.5rem;
	text-align  :center;
	color : #1a92b9;
}
#upfile{display:none}

</style>
</head>
<body>
<div class = "container">
<form action="ReviewModifyAction.rv" method="post" name ="modifyform" enctype="multipart/form-data" >
	<input type="hidden" name = "review_id" value="${reviewbean.review_id}">
	<h1>리뷰 수정</h1>
	<div class="form-group">
		<label for="member_id">글쓴이</label>
		<input type="text" class="form-control" value="${reviewbean.member_id}" readOnly>
	</div>
	<div class = "form-group">
		<label for="review_pass">비밀번호</label>
		<input name = "review_pass" id = "review_pass" type="password" maxlength="30"
			   class = "form-control" placeholder ="Enter review_pass" value="">
	</div>
	<div class="form-group">
		<label for="review_title">제목</label>
		<input type="text" name="review_title" id="review_title" maxlength="100"
			   class="form_control" value="${reviewbean.review_title}">
	</div>
	<div class="form-group">
		<label for="review_content">내용</label>
		<textarea name = "review_content" id="review_content"
				  class = "form-control" rows="15">${reviewbean.review_content}</textarea>
	</div>
	<!--  파일이 첨부되어 있으면 -->
	<c:if test="${reviewbean.review_file != ''}">
		<div class="form-group read">
			<label for="review_file">파일 첨부</label> <label for="upfile">
				<img src="images/file.png" alt="파일첨부" width="20px">
			</label> <input type="file" id="upfile" name="review_file"> <span
				id="filevalue">${boarddata.BOARD_FILE}</span> <img
				src="images/remove.png" alt="파일삭제" width="10px" class="remove">
		</div>
	</c:if>
	
	<div class="form-group">
		<button type="submit" class="btn btn-primary">수정</button>
		<button type="reset" class="btn btn-danger" onClick="history.go(-1)">취소</button>
	</div>
</form>
</div>
</body>
</html>