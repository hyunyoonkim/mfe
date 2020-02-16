<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
	$(function() {
		var sel = "${search_field}";
		$("#viewcount").val(sel).prop("selected", true);
	})
</script>
<c:if test="${listcount > 0 }">
	<div class="center-block">
		<form class="search_member size" action="adminPage.net">
			<div class="input-group select-wrapper">

				<select id="viewcount" name="search_field">
					<option value="0">ID</option>
					<option value="1" selected>이름</option>
					<option value="2">주소</option>
					<option value="3">성별</option>
				</select> <input id="search_word" name="search_word" type="text"
					class="form-control" placeholder="Search" value="${search_word}">
				<button id="search_btn" class="btn btn-primary small" type="submit">검색</button>

			</div>
		</form>
	</div>

	<%-- 회원이 있는 경우 --%>
	<div class="container container1">

		<table class="table t1">
			<caption>회원 목록</caption>
			<thead>
				<tr>
					<th colspan="2"><select class="form-control" id="viewcount3">
							<option value="1">1</option>
							<option value="3">3</option>
							<option value="5">5</option>
							<option value="7">7</option>
							<option value="10" selected>10</option>
					</select></th>
					<th colspan="1"></th>
					<th colspan="1"><font size=2>${listcount }명</font></th>
				</tr>
				<tr>
					<th width="20%">번호</th>
					<th width="35%">아이디</th>
					<th width="25%">이름</th>
					<th width="20%">삭제</th>
				</tr>
			</thead>
			<tbody class="tb1">
				<c:set var="num" value="${listcount-(page-1)*10}" />
				<%-- listAction에 limit 변경시 곱하는 값도 같이 변경해야함 --%>
				<c:forEach var="m" items="${memberlist}">
					<tr>
						<td><c:out value="${num }" /> <%-- num 출력 --%> <c:set
								var="num" value="${num-1}" /> <%-- num = num-1 의미함 --%></td>
						<td>
							<div>
								<a><button type="button" class="btn memberDetail"
										data-toggle="modal" data-target="#member_view_Modal">${m.id}</button></a>
								<%--
					 <a href="member_info.net?id=${m.id }">
					 	${m.id}
					 </a>
					  --%>
							</div>
						</td>
						<td>
							<div>${m.name}</div>
						</td>
						<td><c:if test="${m.id != 'admin@mfe.com'}">
								<a href="member_delete.net?id=${m.id}" style="color: red">삭제</a>
								<%-- onclick = "delchk(); 붙여도 가능 --%>
							</c:if></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="center-block block1">
		<div class="row">
			<div class="col huWidth">
				<ul class="pagination pa1 huWidth">
					<c:if test="${page <= 1 }">
						<li class="page-item"><a class="page-link" href="#">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${page > 1 }">
						<li class="page-item"><a
							href="adminPage.net?page=${page-1}&search_field=${search_field}&search_word=${search_word}"
							class="page-link">이전</a>&nbsp;</li>
					</c:if>

					<c:forEach var="a" begin="${startpage }" end="${endpage }">
						<c:if test="${a == page }">
							<li class="page-item"><a class="page-link current"
								href="adminPage.net?page=${a }&search_field=${search_field}&search_word=${search_word}">${a }</a>
							</li>
						</c:if>
						<c:if test="${a != page }">
							<li class="page-item"><a
								href="adminPage.net?page=${a }&search_field=${search_field}&search_word=${search_word}"
								class="page-link">${a }</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${page >= maxpage }">
						<li class="page-item"><a class="page-link" href="#">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${page < maxpage }">
						<li class="page-item"><a
							href="adminPage.net?page=${page+1}&search_field=${search_field}&search_word=${search_word}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

</c:if>
<!--  회원이 없는 경우 -->
<c:if test="${listcount == 0 }">
	<font size=5>등록된 회원이 없습니다.</font>
</c:if>
<br>

<!--  회원 상세정보 -->
<div class="modal" id="member_view_Modal" tabindex="-1" role="dialog"
	aria-labelledby="viewLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header" style="text-align: center">
				<h4 class="modal-title" id="viewLabel" style="color: black">회원
					상세 정보</h4>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form id = "updateMember_form" name="joinform" method="post">
					<fieldset>
						<div class="form-group">
							<label for="updateMember_id">아이디</label> <input type="text"
								class="form-control" id="updateMember_id" placeholder="Enter id"
								name="updateMember_id" readOnly>
						</div>
						<div class="form-group">
							<label for="updateMember_pass">비밀번호</label> <input
								type="password" class="form-control" id="updateMember_pass"
								placeholder="Enter password" name="updateMember_pass" required
								maxLength="20">
						</div>
						<div class="form-group">
							<label for="updateMember_name">이름</label> <input type="text"
								id="updateMember_name" class="form-control"
								name="updateMember_name" placeholder="Enter name" required maxLength="15">
						</div>
						<div class="form-group">
							<label for="updateMember_address">주소</label> <input type="text"
								id="updateMember_address" class="form-control"
								name="updateMember_address" value=""
								placeholder="Enter address" required>
						</div>
						<div class="form-group">
							<label for="updateMember_phone_number">전화번호</label> <input
								type="text" id="updateMember_phone_number" class="form-control"
								name="updateMember_phone_number"
								placeholder="Enter birthday"
								required>
						</div>
						<div class="form-group">
							<label>선호장르</label> <input type="checkbox"
								id="updateMember_preference_balad"
								name="updateMember_preference" value="balad"> <label
								for="updateMember_preference_balad">발라드</label> <input
								type="checkbox" id="updateMember_preference_rock"
								name="updateMember_preference" value="rock"> <label
								for="updateMember_preference_rock">락/메탈</label> <input
								type="checkbox" id="updateMember_preference_rap"
								name="updateMember_preference" value="rap"> <label
								for="updateMember_preference_rap">랩/힙합</label> <input
								type="checkbox" id="updateMember_preference_jazz"
								name="updateMember_preference" value="jazz"> <label
								for="updateMember_preference_jazz">재즈/소울</label> <input
								type="checkbox" id="updateMember_preference_classic"
								name="updateMember_preference" value="classic"> <label
								for="updateMember_preference_classic">클래식</label> <input
								type="checkbox" id="updateMember_preference_pop"
								name="updateMember_preference" value="pop"> <label
								for="updateMember_preference_pop">팝</label> <input
								type="checkbox" id="updateMember_preference_edm"
								name="updateMember_preference" value="edm"> <label
								for="updateMember_preference_edm">EDM</label>
						</div>
						<div class="6u$ 12u$(small)">
							<div>
								<label for="male">성별</label>
							</div>
							<input type="radio" id="Umale" name="updateMember_gender"
								value="1"> <label for="male">남</label> <input
								type="radio" id="Ufemale" name="updateMember_gender" value="2">
							<label for="female">여</label>
						</div>

						<button class = "update_button"id = "updateMember_button"type="button">수정</button>
						<button class="update_button cancelbtn" type="button">돌아가기</button>
					</fieldset>
				</form>

			</div>
		</div>
	</div>
</div>

