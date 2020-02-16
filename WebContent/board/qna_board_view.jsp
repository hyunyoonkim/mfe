<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../mainActivity/navbar.jsp" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
span {
	width: 20px;
}

.no {
	display: none;
}

#count {
	position: relative;
	top: -10px;
	left: -10px;
	background: orange;
	color: white;
	border-radius: 30%;
}

.container {
	padding-top: 55px;
}
</style>
<script>
	$(function() {
		$("#comment table").hide();
		$("#write").click(function() {
			buttonText = $("#write").text();
			content = $("#content").val();
			$(".float-left").text('총 50자까지 가능합니다.');
			if (buttonText == "등록") { //추가 하는 경우
				url = "CommentAdd.bo";
				data = {
					"content" : content,
					"id" : $("#loginid").val(),
					"BOARD_RE_REF" : $("#BOARD_RE_REF").val()
				};
			} else { // 수정하는 경우
				url = "CommentUpdate.bo";
				data = {
					"num" : num,
					"content" : content
				};
				$("#write").text("등록");
			}
			$.ajax({
				type : "post",
				url : url,
				data : data,
				success : function(result) {
					$("#content").val('');
					if (result == 1) {
						getList();
					}
				}
			})

		})
		function getList() {
			$
					.ajax({
						type : "post",
						url : "CommentList.bo",
						data : {
							"BOARD_RE_REF" : $("#BOARD_RE_REF").val()
						},
						dataType : "json",
						success : function(rdata) {
							if (rdata.length > 0) {
								$("#comment table").show(); //문서 로딩될 때 숨긴게 나온다.
								$("#comment thead").show();
								$("#comment tbody").empty();
								$("#message").text('');
								output = '';
								$(rdata)
										.each(
												function() {
													img = '';
													if ($("#loginid").val() == this.member_id) {
														img = "<img src='images/pencil2.png' width = '15px' class='update'>"
																+ "<img src='images/remove.png' width = '15px' class='remove'>"
																+ "<input type='hidden' value='" +this.reply_id + "'>";
													}
													output += "<tr><td>"
															+ this.member_id
															+ "</td>";
													output += "<td>"
															+ this.reply_content
															+ "</td>";
													output += "<td>"
															+ this.reg_date
															+ img
															+ "</td></tr>";
												});
								$("#comment tbody").append(output);
							} else {
								$("#message").text("등록된 댓글이 없다.");
								$("#comment thead").hide();
								$("#comment tbody").empty();
							}
							$("#count").text(rdata.length);
						}
					})
		}
		$(".start").click(function() {
			getList();
		});
		$("#content").on('input', function() {
			length = $(this).val().length;
			if (length > 50) {
				length = 50;
				content = content.substring(0, length);
			}
			$(".float-left").text(length + "/50");
		});
		// 수정버튼 클릭경우(댓글)
		$("#comment").on('click', '.update', function() {
			before = $(this).parent().prev().text(); 
			$("#content").focus().val(before); 
			num = $(this).next().next().val(); 
			$("#write").text("수정완료"); 
			$(this).parent().parent().css('background', 'lightgray');
		});

		// 삭제버튼 클릭경우(댓글)
		$("#comment").on('click', '.remove', function() {
			var num = $(this).next().val();//댓글번호
			$.ajax({
				type : "post",
				url : "CommentDelete.bo",
				data : {
					"num" : num
				},
				success : function(result) {
					if (result == 1)
						getList();
				}
			})
		})

	})
</script>
</head>
<body>
	<input type="hidden" id="loginid" value="${id}" name="loginid">
	<div class="container">

		<table class="table table-bordered">
			<tr>
				<th colspan="2">자유게시판 - view 페이지</th>
			</tr>
			<tr>
				<td><div>글쓴이</div></td>
				<td><div>${boarddata.BOARD_NAME}</div></td>
			</tr>
			<tr>
				<td><div>제목</div></td>
				<td><div>${boarddata.BOARD_SUBJECT}</div></td>
			</tr>
			<tr>
				<td><div>내용</div></td>
				<td><textarea class="form-control" rows="5" readOnly
						style="width: 102%">${boarddata.BOARD_CONTENT}</textarea></td>
			</tr>

			<tr>
				<td><div>첨부파일</div></td>
				<td><c:if test="${!empty boarddata.BOARD_FILE}">
						<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
						<a href="BoardFileDown.bo?filename=${boarddata.BOARD_FILE}">
							${boarddata.BOARD_FILE}</a>
					</c:if></td>
			</tr>

			<tr>
				<td colspan="2" class="center">
					<button class="btn btn-primary start">댓글</button> <span id="count">${count}</span>

					<a href="BoardReplyView.bo?num=${boarddata.BOARD_NUM}">
						<button class="btn btn-primary">답변</button>
				</a> <c:if test="${boarddata.BOARD_NAME == id || id == 'admin@mfe.com'}">
						<a href="BoardModifyView.bo?num=${boarddata.BOARD_NUM}">
							<button class="btn btn-info">수정</button>
						</a>

						<a href="BoardDelete.bo?num=${boarddata.BOARD_NUM}">
							<button class="btn btn-danger" data-toggle="modal"
								data-target="#myModal">삭제</button>
						</a>
						<%--앵커로 넘기는 방법
				<a href ="BoardDelete.bo?num=${boarddata.BOARD_NUM}">
					<button class= " btn btn-danger">삭제</button>
				</a>
				 --%>
					</c:if> <a href="BoardList.bo">
						<button class="btn btn-primary">목록</button>
				</a> <span id="no">${boarddata.BOARD_PASS}</span>
				</td>
			</tr>
		</table>
		<div id="comment">
			<button class="btn btn-info float-left">총 50자까지 가능합니다.</button>
			<button id="write" class="btn btn-info float-right">등록</button>
			<textarea rows=3 class="form-control" name="content" id="content"
				maxLength="50"></textarea>
			<table class="table table_striped">
				<thead>
					<tr>
						<td>아이디</td>
						<td>내용</td>
						<td>날짜</td>
					</tr>
				</thead>
				<tbody>
				</tbody>
			</table>
			<div id="message"></div>
		</div>
		<%-- delete 모달 --%>
	<div class="modal" id="myModal">
			<div class="modal-dialog">
				<div class="modal-content">


					<!-- Modal body -->
					<div class="modal-body">
						<form name="deleteForm" action="BoardDelete.bo"
							method="post">
							<input type="hidden" name="num" value="${param.num}"
								id="BOARD_RE_REF"> <input type="hidden"
								name="BOARD_FILE" value="${boarddata.BOARD_FILE}">

							<div class="form-group">
								<label for="pwd">비밀번호</label> <input type="password"
									class="form-control" placeholder="Enter password"
									name="BOARD_PASS" id="board_pass">
							</div>
							<button type="submit" class="btn btn-primary">Submit</button>
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
						</form>
					</div>
				</div>
			</div>
		</div> 
	</div>
</body>
</html>