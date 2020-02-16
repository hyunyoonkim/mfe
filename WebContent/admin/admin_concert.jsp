<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	$(function() {
		var check = 0;
		var sel = "${concert_search_field}";
		$("#concert_search_field").val(sel).prop("selected", true);
		
		function show() {
			if($('#filevalue').text() == '') {
				//파일 이름이 있는 경우 remove 이미지를 보이게 하고 없는 경우는 보이지 않게 한다.
				$(".remove").css('display', 'none');
			} else {
				$(".remove").css('display' , 'inline-block');
			}
		}
		$('#upfile').change(function() {
			check++;		
			var inputfile=$(this).val().split('\\');
			$('#filevalue').text(inputfile[inputfile.length-1]);
			show();
		});
		
		$('.remove').click(function() {
			$('#filevalue').text('');
			$(this).css('display', 'none');
		});
	})
</script>
<c:if test="${concert_count > 0 }">
	<div class="center-block">
		<form class="search_concert size" action="adminPage.net">
			<div class="input-group select-wrapper">

				<select id="concert_search_field" name="concert_search_field">
					<option value="0" selected>ID</option>
					<option value="1">이름</option>
					<option value="2">가수</option>
					<option value="3">장르</option>
				</select> <input id="concert_search_word" name="concert_search_word"
					type="text" class="form-control" placeholder="Search"
					value="${concert_search_word}">
				<button id="concert_search_btn" class="btn btn-primary small"
					type="submit">검색</button>

			</div>
		</form>
	</div>
	<%-- 회원이 있는 경우 --%>
	<div class="container container3">

		<table class="table t3">
			<caption>콘서트 목록</caption>
			<thead>
				<tr>
					<th colspan="2"><select class="form-control"
						id="concert_viewcount">
							<option value="1">1</option>
							<option value="3">3</option>
							<option value="5">5</option>
							<option value="7">7</option>
							<option value="10" selected>10</option>
					</select></th>
					<th colspan="1"></th>
					<th colspan="1"><font size=2>${concert_count }개</font></th>
				</tr>
				<tr>
					<th width="20%">번호</th>
					<th width="20%">ID</th>
					<th width="40%">이름</th>
					<th width="20%">삭제</th>
				</tr>
			</thead>
			<tbody class="tb3">
				<c:set var="num" value="${concert_count-(concert_page-1)*10}" />
				<%-- listAction에 limit 변경시 곱하는 값도 같이 변경해야함 --%>
				<c:forEach var="concert" items="${concert_list}">
					<tr>
						<td><c:out value="${num}" /> <%-- num 출력 --%> <c:set
								var="num" value="${num-1}" /> <%-- num = num-1 의미함 --%></td>
						<td>
							<div>
								<a><button type="button" class="btn concertDetail"
										data-toggle="modal" data-target="#concert_view_Modal">${concert.concert_id}</button></a>
								<%--
					 <a href="member_info.net?id=${m.id }">
					 	${m.id}
					 </a>
					  --%>
							</div>
						</td>
						<td>
							<div>${concert.concert_name}</div>
						</td>
						<td><a class="del"
							href="concert_delete.co?id=${concert.concert_id}" style="color:red">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	<div class="center-block block3">
		<div class="row">
			<div class="col huWidth">
				<ul class="pagination pa3 huWidth">
					<c:if test="${concert_page <= 1 }">
						<li class="page-item"><a class="page-link" href="#">이전&nbsp;</a>
						</li>
					</c:if>
					<c:if test="${concert_page > 1 }">
						<li class="page-item"><a
							href="adminPage.net?concert_page=${concert_page-1}&concert_search_field=${concert_search_field}&concert_search_word=${concert_search_word}"
							class="page-link">이전</a>&nbsp;</li>
					</c:if>

					<c:forEach var="a" begin="${concert_startpage }"
						end="${concert_endpage }">
						<c:if test="${a == concert_page }">
							<li class="page-item"><a class="page-link current"
								href="adminPage.net?concert_page=${a }&concert_search_field=${concert_search_field}&concert_search_word=${concert_search_word}">${a }</a>
							</li>
						</c:if>
						<c:if test="${a != concert_page }">
							<li class="page-item"><a
								href="adminPage.net?concert_page=${a }&concert_search_field=${concert_search_field}&concert_search_word=${concert_search_word}"
								class="page-link">${a }</a></li>
						</c:if>
					</c:forEach>

					<c:if test="${concert_page >= concert_maxpage }">
						<li class="page-item"><a class="page-link" href="#">&nbsp;다음</a>
						</li>
					</c:if>
					<c:if test="${concert_page < concert_maxpage }">
						<li class="page-item"><a
							href="adminPage.net?concert_page=${concert_page+1}&concert_search_field=${concert_search_field}&concert_search_word=${concert_search_word}"
							class="page-link">&nbsp;다음</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>

</c:if>
<c:if test="${concert_count == 0 }">
	<font size=5>등록한 콘서트가 없습니다.</font>
</c:if>
<br>
<button id="addConcert_Button" type="button"
	class="btn btn-info float-right" data-toggle="modal"
	data-target="#concert_add_Modal">>콘서트 등록</button>

<div class="modal" id="concert_add_Modal" tabindex="-1" role="dialog"
	aria-labelledby="concert_viewLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header" style="text-align: center">
				<h4 class="modal-title" id="concert_viewLabel" style="color: black">콘서트
					등록</h4>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<form method="post" action="ConcertAddAction.co"
					enctype="multipart/form-data" name="concert_add_form" id="concert_add_form">
					<div class="row uniform">
						<div class="12u$">
							<label for="concert_add_name">콘서트명</label> <input type="text"
								name="concert_add_name" id="concert_add_name"
								placeholder="Enter Name" />
						</div>
						<div class="6u 12u$(xsmall)">
							<label for="concert_add_day">날짜</label> <input type="text"
								name="concert_add_day" id="concert_add_day"
								placeholder="Enter Day(ex. yyyy-MM-dd)" />
						</div>
						<div class="6u$ 12u$(xsmall)">
							<label for="concert_add_musician">가수</label> <input type="text"
								name="concert_add_musician" id="concert_add_musician"
								placeholder="Enter Musician" />
						</div>
						<div class="6u 12u$(xsmall)">
							<label for="concert_add_open">오픈</label> <input type="text"
								name="concert_add_open" id="concert_add_open"
								placeholder="Enter Open Time(ex. 1900)" />
						</div>
						<div class="6u$ 12u$(xsmall)">
							<label for="concert_add_close">종료</label> <input type="text"
								name="concert_add_close" id="concert_add_close"
								placeholder="Enter Close Time(ex. 2300)" />
						</div>
						<div class="6u 12u$(xsmall)">
							<label for="upfile">이미지</label> 
							<label id = "upfile_Label"for="upfile">
								<img id=ig src="./images/attach.png" alt="사막">
								<input type="file" id="upfile" name="CONCERT_FILE" value =""> 
								<span id="filevalue"></span>
							</label> <img src = "images/remove.png" alt="파일삭제" width="10px" class = "remove">
							
							
						</div>
						<div class="6u$ 12u$(xsmall)">
							<label for="concert_price">가격</label> <input type="text"
								name="concert_price" id="concert_price"
								placeholder="Enter price(ex. 80000)" />
						</div>

						<div class="12u$">
							<div class="select-wrapper">
								<label>장르</label> <select name="concert_add_genre_id"
									id="concert_add_genre_id">
									<option value="1">발라드</option>
									<option value="2">록/메탈</option>
									<option value="3">랩/힙합</option>
									<option value="4">R&amp;B/Soul</option>
									<option value="5">jazz</option>
									<option value="6">classic</option>
									<option value="7">pop</option>
									<option value="8">EDM</option>
								</select>
							</div>
						</div>
						<div class="12u$">
							<div class="select-wrapper">
								<label>지역</label> <select name="concert_add_local_id"
									id="concert_add_local_id">
									<option value="1">서울</option>
									<option value="2">경기</option>
									<option value="3">인천</option>
									<option value="4">부산</option>
									<option value="5">대구</option>
									<option value="6">대전</option>
									<option value="7">경남</option>
									<option value="8">전남</option>
									<option value="9">충남</option>
									<option value="10">광주</option>
									<option value="11">울산</option>
									<option value="12">경북</option>
									<option value="13">전북</option>
									<option value="14">충북</option>
									<option value="15">강원</option>
									<option value="16">세종</option>
								</select>
							</div>
						</div>

						<div class="12u$">
							<ul class="actions">
								<li><input type="submit" value="Send Message" /></li>
								<li><input type="reset" value="Reset" class="alt" /></li>
								<li><input type="button" value="취소" data-dismiss="modal"/></li>
							</ul>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>



