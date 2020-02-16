<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../mainActivity/navbar.jsp" />
<script type="text/javascript" src="js/ckeditor.js"></script>

<style>
tr.center-block {
	text-align: center
}

h1 {
	font-size: 1.5rem;
	text-align: center;
	color: #1a92b9
}

.container {
	width: 60%
}

label {
	font-weight: bold
}

#upfile {
	display: none
}

img {
	width: 20px
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
</style>


<meta charset="UTF-8">
<title>자유게시판 - reply 페이지</title>
</head>
<body>
	<div class="container">
		<form action="BoardReplyAction.bo" method="post" name="boardform">

			<%--답변을 추가하기 위해서는 답변의 원문글에 대한 BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ 정보가 필요합니다 --%>
			<input type="hidden" name="BOARD_RE_REF"
				value="${boarddata.BOARD_RE_REF}"> <input type="hidden"
				name="BOARD_RE_LEV" value="${boarddata.BOARD_RE_LEV}"> <input
				type="hidden" name="BOARD_RE_SEQ" value="${boarddata.BOARD_RE_SEQ}">

			<h1>MVC 게시판-Reply</h1>
			<div class="form-group">
				<label for="board_name">글쓴이</label> <input name="BOARD_NAME"
					id="board_name" type="text" value="${id }" class="form-control"
					readOnly>
			</div>
			<div class="form-group">
				<label for="BOARD_SUBJECT">제목</label> <input name="BOARD_SUBJECT"
					id="board_subject" type="text" value="${boarddata.BOARD_SUBJECT}"
					class="form-control" readOnly>
			</div>
			<div class="form-group">
				<label for="board_content">내용</label> <span id="counter">###</span>
				<textarea name="BOARD_CONTENT" id="board_content" cols="67"
					rows="10" class="form-control" placeholder="내용을 입력해주세요.(최대 500자)"></textarea>

			</div>
			<div class="form-group">
				<label for="board_pass">비밀번호</label> <input name="BOARD_PASS"
					id="board_pass" type="password" class="form_control">
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-primary">등록</button>
				<button type="reset" class="btn btn-primary"
					onClick="history.go(-1)">취소</button>
			</div>
		</form>
	</div>



</body>
</html>