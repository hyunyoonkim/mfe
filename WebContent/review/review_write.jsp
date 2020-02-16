<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix ="c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰 작성</title>
<jsp:include page ="../mainActivity/navbar.jsp"/>
<style>
.container {
   padding-top: 55px;
}

#counter {
   float: right;
   position: relative;
   left: -10px;
   top: 30px;
   background: rgba(255, 0, 0, 0.5);
   border-radius: 0.5em;
   padding: 0 .5em 0 .5em;
}

#upfile {
   display: none;
}

img {
   width: 25px;
}

img:hover {
   cursor: pointer
}

</style>
<script src="js/reviewform.js"></script>
</head>
<body>
<div class="container">
		<form action="ReviewAddAction.rv" method="post"
				enctype="multipart/form-data" name="reviewform">
			<h1>리뷰게시판 - 작성</h1>
			<div class="form-group">
				<label for="member_id">글쓴이</label>
				<input name="member_id" id="member_id" value="${id }"
							readOnly
							type="text" size="10" maxlength="30"
							class="form-control">
			</div>
			<div class="form-group">
				<label for="review_pass">비밀번호</label>
				<input name="review_pass" id="review_pass"
							type="password" size="10" maxlength="30"
							class="form-control" placeholder="Enter review_pass"
							value="">
			</div>
			<div class="form-group">
				<label for="review_title">제목</label>
				<input name="review_title" id="review_title"
							type="text" size="50" maxlength="100"
							class="form-control" placeholder="Enter review_title"
							value="">
			</div>
			<div class="form-group">
				<label for="concert_name">콘서트 선택</label>
				<select name="review_concert">
					<c:forEach var="c" items="${concertlist}">
						<option value="${c.concert_id }">${c.concert_name }</option>
					</c:forEach>
				</select>
			</div>
			<div class="form-group">
				<label for="review_content">내용</label>
				<textarea name="review_content" id="review_content"
							cols="67" rows="10" class="form-control" ></textarea>
			</div>
			<div class="form-group">
				<label for="review_file">파일 첨부</label> <label for="upfile"> <img
					id=ig src="./images/file.png" alt=""></label> <input type="file"
					id="upfile" name="review_file"> <span id="filevalue"></span>
			</div>
			<div class="form-group">
				<button type="submit" class="btn btn-primary">등록</button>
				<input type='button' value="취소" class="back"
					onClick='history.back(); return false;'>
			</div>
		</form>
	</div>
</body>
</html>