<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../mainActivity/navbar.jsp" />
<style>
.container {
   padding-top: 55px;
}

.select-wrapper {
   float: right;
}

#addBoard_Button {
   float: right;
}

#search_select {
   width: 10%;
   display: inline-block;
   height: 32px;
}

#search_text {
   width: 25%;
   display: inline-block;
   height: 32px;
}

.center-block {
   display: flex;
      justify-content: center; /* 가운데 정렬 */
}
</style>
<script src="js/boardlist.js"></script>

<meta charset="UTF-8">
<title>게시판 목록</title>
</head>
<body>
	<div class="container">
		<div class="12u$">
			<div class="select-wrapper">
				<span>목록 갯수</span> <select class="form-control" id="viewcount">
					<option value="1">1</option>
					<option value="3">3</option>
					<option value="5">5</option>
					<option value="7">7</option>
					<option value="10" selected>10</option>
				</select>
			</div>
		</div>



		<table class="table">
			<thead>
				<tr>
					<th colspan="3">자유게시판</th>
					<th colspan="2"><font size=3>글 개수 : ${listcount }</font></th>
				</tr>
				<tr>
					<th width="8%"><div>번호</div></th>
					<th width="50%"><div>제목</div></th>
					<th width="14%"><div>작성자</div></th>
					<th width="17%"><div>날짜</div></th>
					<th width="11%"><div>조회수</div></th>
				</tr>
			</thead>

			<tbody>
				<c:set var="num" value="${listcount-(page-1)*10}" />

				<!--  게시글이 없는 경우 -->
				<c:if test="${listcount == 0 }">
					<tr>
						<td colspan="5" style="text-align: center">등록된 글이 없습니다.</td>
					</tr>
				</c:if>

				<%-- 게시글이 있는 경우 --%>
				<c:if test="${listcount > 0 }">
					<c:forEach var="b" items="${boardlist}">
						<tr>
							<td><c:out value="${num }" /> <c:set var="num"
									value="${num-1}" /></td>
							<td>
								<div>
									<c:if test="${b.BOARD_RE_LEV != 0}">
										<!-- 답글인 경우 -->
										<c:forEach var="a" begin="0" end="${b.BOARD_RE_LEV*2}"
											step="1">&nbsp;
						 				</c:forEach>
										<img src='images/AnswerLine.gif'>
									</c:if>
									<c:if test="${b.BOARD_RE_LEV ==0}">
										<!-- 원문인 경우 -->
						 				&nbsp;
						 			</c:if>
									<a href="BoardDetailAction.bo?num=${b.BOARD_NUM }">${b.BOARD_SUBJECT}
									</a>
								</div>
							</td>
							<td>
								<div>${b.BOARD_NAME}</div>
							</td>
							<td>
								<div>${b.BOARD_DATE}</div>
							</td>
							<td>
								<div>${b.BOARD_READCOUNT}</div>
							</td>
						</tr>
					</c:forEach>
				</c:if>

			</tbody>
		</table>

		<button id="addBoard_Button" type="button"
			class="btn btn-info float-right">글쓰기</button>

		<c:if test="${listcount > 0 }">
			<p>
				<select name="search_select" size="1" id="search_select">
					<option value="board_name">작성자</option>
					<option value="board_subject">제목</option>
					<option value="board_content">내용</option>
				</select> 
				<input type="text" id="search_text" name="search_text"
					placeholder="검색할 내용을 입력하세요.">
				<button type="submit" id="search_btn">검색</button>
			</p>

			<div class="center-block">
				<div class="row">
					<div class="col">
						<ul class="pagination">
							<c:if test="${page <= 1 }">
								<li class="page-item"><a class="page-link" href="#">이전&nbsp;</a>
								</li>
							</c:if>
							<c:if test="${page > 1 }">
								<li class="page-item"><a
									href="BoardList.bo?page=${page-1 }" class="page-link">이전</a>
									&nbsp;</li>
							</c:if>

							<c:forEach var="a" begin="${startpage }" end="${endpage }">
								<c:if test="${a == page }">
									<li class="page-item"><a class="page-link" href="#">${a }</a>
									</li>
								</c:if>
								<c:if test="${a != page }">
									<li class="page-item"><a href="BoardList.bo?page=${a }"
										class="page-link">${a }</a></li>
								</c:if>
							</c:forEach>

							<c:if test="${page >= maxpage }">
								<li class="page-item"><a class="page-link" href="#">&nbsp;다음</a>
								</li>
							</c:if>
							<c:if test="${page < maxpage }">
								<li class="page-item"><a
									href="BoardList.bo?page=${page+1 }" class="page-link">&nbsp;다음</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</div>
		</c:if>
	</div>
</body>
</html>