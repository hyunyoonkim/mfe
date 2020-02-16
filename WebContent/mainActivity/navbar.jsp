<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />

<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="http://kenwheeler.github.io/slick/slick/slick.css" />
<link rel="stylesheet" type="text/css" href="http://kenwheeler.github.io/slick/slick/slick-theme.css" />
<link rel="stylesheet" href="assets/css/main.css?ver=6" />
<link rel="stylesheet" href="css/navbar.css?ver=1" />
<script type="text/javascript" src="http://kenwheeler.github.io/slick/slick/slick.min.js"></script>	
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/jquery.scrolly.min.js"></script>
<script src="assets/js/skel.min.js"></script>
<script src="assets/js/util.js"></script>
<script src="assets/js/main.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="js/searchlist.js"></script>
<script src="js/filterlist.js"></script>
<script src="js/jsbn.js"></script>
<script src="js/rsa.js"></script>
<script src="js/prng4.js"></script>
<script src="js/rng.js"></script>
<script src="js/navbar.js"></script>
<header id="header">
	<h1>
		<a href="main.net">MFE</a>
	</h1>
	<div>
		<button type="button" class="btn btn-secondary" data-toggle="modal"
			data-target="#filter_Modal">
			<span class="glyphicon glyphicon-tasks" aria-hidden="true"></span>
		</button>
		<!--  검색 텍스트 -->
		<form class="search_Text" id="searchForm" method="post"
			action="searchword.co">
			<input class="search_Text" type="text" name="search_Text"
				id="search_Text" placeholder="search...">
		</form>
		<button id="search_Button" type="button" class="btn btn-secondary">
			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		</button>
		<c:if test="${!empty id}">
			<c:choose>
				<c:when test="${id=='admin@mfe.com'}">
					<button id="adminMode_Button" type="button"
						class="btn btn-secondary">
						<span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
					</button>
					<button type="button" class="btn btn-secondary" data-toggle="modal"
						data-target="#account_Modal">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					</button>
				</c:when>
				<c:otherwise>
					<button type="button" class="btn btn-secondary" data-toggle="modal"
						data-target="#account_Modal">
						<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
					</button>
				</c:otherwise>
			</c:choose>
		</c:if>
		<c:if test="${empty id}">
			<button id = "nav_login_button" type="button" class="btn btn-secondary" data-toggle="modal"
				data-target="#login_Modal">
				<span class="glyphicon glyphicon-user" aria-hidden="true"></span>
			</button>
		</c:if>
		<a class="global" href="#menu"><span
			class="glyphicon glyphicon-th-large" aria-hidden="true"></span></a>
	</div>
</header>
<!-- 옆으로 확장되는 네비게이션 바 -->
<nav id="menu">
	<ul class="links">
		<li><a href="BoardList.bo" class="dropdown-toggle"
			data-toggle="dropdown" role="button" aria-expanded="false">자유게시판</a></li>
		<li><a href="ReviewList.rv" class="dropdown-toggle" data-toggle="dropdown"
			role="button" aria-expanded="false">리뷰게시판</a></li>
		<li><a href="#" class="dropdown-toggle" data-toggle="dropdown"
			role="button" aria-expanded="false">고객센터 </a></li>
	</ul>
</nav>
<!--  필터 모달 -->
<div class="modal" id="filter_Modal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">조건별 검색</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<form method="post" action="searchfilter.co" id="filter_form">
				<!-- Modal body -->
				<div class="modal-body">
					<div class="container uniform">
						<fieldset>
							<div class="form-group">
								<label for="datepicker" class="modalBlack">날짜별</label> <input
									type="date" name="search_date" id="datepicker">
							</div>
							<div class="table-wrapper">
								<label for="local" class="modalBlack">지역별</label> <input
									type="hidden" name="search_local" id="search_local">
								<table class = "table_f" id="local">
									<tr>
										<td><input type='button' class='local' value="서울"></td>
										<td><input type='button' class='local' value="경기"></td>
										<td><input type='button' class='local' value="인천"></td>
										<td><input type='button' class='local' value="부산"></td>
										<td><input type='button' class='local' value="대구"></td>
										<td><input type='button' class='local' value="대전"></td>
									</tr>
									<tr>
										<td><input type='button' class='local' value="경남"></td>
										<td><input type='button' class='local' value="전남"></td>
										<td><input type='button' class='local' value="충남"></td>
										<td><input type='button' class='local' value="광주"></td>
										<td><input type='button' class='local' value="울산"></td>
										<td><input type='button' class='local' value="경북"></td>
									</tr>
									<tr>
										<td><input type='button' class='local' value="전북"></td>
										<td><input type='button' class='local' value="충북"></td>
										<td><input type='button' class='local' value="강원"></td>
										<td><input type='button' class='local' value="제주"></td>
										<td><input type='button' class='local' value="세종"></td>
										<td><input type='button' id="all_local" value="전국"></td>
									</tr>
								</table>
							</div>

							<div class="form-group">
								<label for="genre" class="modalBlack">장르별</label> <input
									type="hidden" name="search_genre" id="search_genre">
								<table class = "table_f" id="genre">
									<tr>
										<td><input type='button' class='genre' value="balad"></td>
										<td><input type='button' class='genre' value="rock"></td>
										<td><input type='button' class='genre' value="rap"></td>
									</tr>
									<tr>
										<td><input type='button' class='genre' value="R&B/Soul"></td>
										<td><input type='button' class='genre' value="jazz"></td>
										<td><input type='button' class='genre' value="classic"></td>
									</tr>
									<tr>
										<td><input type='button' class='genre' value="pop"></td>
										<td><input type='button' class='genre' value="EDM"></td>
										<td><input type='button' id="all_genre" value="모든 장르"></td>
									</tr>
								</table>
							</div>
							<!-- Modal footer -->
							<input type="submit" id="filter_btn" class="btn btn-primary"
								value='검색'> <input class="btn btn-danger"
								id="filter_cancel" data-dismiss="modal" value='취소'>

						</fieldset>
					</div>
				</div>
			</form>
		</div>
	</div>
</div>
<!--  로그인 모달 -->
<div class="modal" id="login_Modal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title logog">로그인</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="container uniform">
					<form id="login_form">
						<fieldset>
							<div class="form-group">
								<label for="login_id" class="modalBlack">아이디</label> 
								<input
									type="text" class="form-control" id="login_id"
									placeholder="Enter id" name="login_id" required maxLength="30">
									<input type="hidden" id="rsaPublicKeyModulus" value="${publicKeyModulus}" />
								<span id="login_id_message"></span>
							</div>
							<div class="form-group">
								<label for="modal_pass" class="modalBlack">비밀번호</label> 
								<input
									type="password" class="form-control" id="login_pass"
									placeholder="Enter password" name="login_pass" required>
									<input type="hidden" id="rsaPublicKeyExponent" value="${publicKeyExponent}" />
							</div>
							<div class="6u$ 12u$(small)">
								<input type="checkbox" id="login_remember" name="login_remember"
									checked> <label for="login_remember">Remember
									me</label>
							</div>
						</fieldset>
					</form>
				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<%-- <a id = "alogin"href="<%=request.getContextPath()%>/loginFailure.jsp">로그인</a>--%>
				<button id="alogin" type="button" class="btn btn-primary">로그인</button>
				<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
				<button type="button" class="btn btn-info" data-dismiss="modal"
					data-toggle="modal" data-target="#addMember_Modal">회원가입</button>
				<form id="securedLoginForm" name="securedLoginForm" action="loginProcess.net" method="post" style="display:none">
					<input type="hidden" name="secured_id" id="secured_id" value="" />
            		<input type="hidden" name="secured_pass" id="secured_pass" value="" />
				</form>
			</div>

		</div>
	</div>
</div>

<!--  계정 모달 -->
<div class="modal right fade" id="account_Modal" tabindex="-1"
	role="dialog" aria-labelledby="accountLabel">
	<div class="modal-dialog" role="document">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title user" id="accountLabel">${id }님</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="container uniform">
					<form method="post" action="#" id="account_form">
						<fieldset>
							<br>
							<div class="accountDiv">
								<a href="mypage.bk">마이페이지</a>
							</div>
							<div class="accountDiv">
								<a href="logOut.net">로그아웃</a>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- 회원가입 모달 -->
<div class="modal" id="addMember_Modal">
	<div class="modal-dialog">
		<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">회원가입</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal body -->
			<div class="modal-body">
				<div class="container">
					<form action="joinProcess.net" id="addMember_form" method="post">
						<fieldset>
							<div class="form-group">
								<label for="addMember_id" class="modalBlack">아이디</label> <input
									type="text" class="form-control" id="addMember_id"
									placeholder="Enter id" name="addMember_id" required> <span
									id="addMember_id_message"></span>
							</div>
							<div class="form-group">
								<label for="addMember_pass" class="modalBlack">비밀번호</label> <input
									type="password" class="form-control" id="addMember_pass"
									placeholder="Enter password" name="addMember_pass" required
									maxLength="12">
							</div>
							<div class="form-group">
								<label for="addMember_name" class="modalBlack">이름</label> <input
									type="text" id="addMember_name" class="form-control"
									name="addMember_name" placeholder="Enter name" required
									maxLength="15">
							</div>
							<div class="form-group">
								<label for="addMember_address" class="modalBlack">주소</label> <input
									type="text" id="addMember_address" class="form-control"
									name="addMember_address" placeholder="Enter address" required>
							</div>
							<div class="form-group">
								<label for="addMember_phone_number" class="modalBlack">전화번호</label>
								<input type="text" id="addMember_phone_number"
									class="form-control" name="addMember_phone_number"
									placeholder="Enter birthday" required>
							</div>
							<div class="form-group">
								<label class="modalBlack">선호장르</label> <input type="checkbox"
									id="addMember_preference_balad" name="addMember_preference"
									value="balad" checked> <label
									for="addMember_preference_balad">발라드</label> <input
									type="checkbox" id="addMember_preference_rock"
									name="addMember_preference" value="rock"> <label
									for="addMember_preference_rock">락/메탈</label> <input
									type="checkbox" id="addMember_preference_rap"
									name="addMember_preference" value="rap"> <label
									for="addMember_preference_rap">랩/힙합</label> <input
									type="checkbox" id="addMember_preference_jazz"
									name="addMember_preference" value="jazz"> <label
									for="addMember_preference_jazz">재즈/소울</label> <input
									type="checkbox" id="addMember_preference_classic"
									name="addMember_preference" value="classic"> <label
									for="addMember_preference_classic">클래식</label> <input
									type="checkbox" id="addMember_preference_pop"
									name="addMember_preference" value="pop"> <label
									for="addMember_preference_pop">팝</label> <input type="checkbox"
									id="addMember_preference_edm" name="addMember_preference"
									value="EDM"> <label for="addMember_preference_edm">EDM</label>
							</div>

							<div class="u$(small)">
								<div>
									<label for="male" class="modalBlack">성별</label>
								</div>
								<input type="radio" id="male" name="addMember_gender" value="1"
									checked> <label for="male" class="modalBlack">남</label>
								<input type="radio" id="female" name="addMember_gender"
									value="2"> <label for="female" class="modalBlack">여</label>
							</div>
							<button type="submit" class="btn btn-primary">회원가입</button>
						</fieldset>
					</form>
				</div>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">취소</button>
			</div>

		</div>
	</div>
</div>