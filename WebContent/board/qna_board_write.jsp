<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>자유게시판 작성</title>
<jsp:include page="../mainActivity/navbar.jsp" />
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
<script src = "js/writeform.js"></script>
</head>
<body>


	<div class="container">
		<form action="BoardAddAction.bo" method="post"
			enctype="multipart/form-data" name="boardform">
			<h1>자유게시판 - write 페이지</h1>

			<div class="form-group">
				<label for="board_name">글쓴이</label> <input name="BOARD_NAME"
					id="board_name" value="${id}" readOnly type="text" size="10"
					maxlength="30" class="form-control" placeholder="Enter board_name">
			</div>

			<div class="form-group">
				<label for="board_pass">비밀번호</label> <input name="BOARD_PASS"
					id="board_pass" type="password" size="10" maxlength="30"
					class="form-control" placeholder="Enter board_pass" value="">
			</div>

			<div class="form-group">
				<label for="board_subject">제목</label> <input name="BOARD_SUBJECT"
					id="board_subject" type="text" size="50" maxlength="100"
					class="form-control" placeholder="Enter board_subject" value="">
			</div>

			<div class="form-group">
				<label for="board_content">내용</label> <span id="counter">###</span>
				<textarea name="BOARD_CONTENT" id="board_content" cols="67"
					rows="10" class="form-control" placeholder="내용을 입력해주세요.(최대 500자)"></textarea>
			</div>

			<div class="form-group">
				<label for="board_file">파일 첨부</label> <label for="upfile"> <img
					id=ig src="./images/file.png" alt="사막"></label> <input type="file"
					id="upfile" name="BOARD_FILE"> <span id="filevalue"></span>
			</div>

			<div class="form-group">
				<button type=submit class="btn btn-primary">등록</button>
				<input type='button' value="취소" class="back"
					onClick='history.back(); return false;'>
			</div>

		</form>
	</div>

</body>
</html>