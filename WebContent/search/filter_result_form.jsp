<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Filter Result</title>
<style>
.box {
	width: 42%;
	text-align: center;
	margin: 100px;
}

.table-wrapper img {
	width: 95%;
	height: 240px;
}

.deadline {
	display: inline-block;
	background: red;
	font-size: 12px;
	font-weight: bold;
	color: black;
	border-radius: 0.2em;
	padding: 0.15em;
}

.filter_btn img {
	width: 25px;
	height: 25px;
}

div .button.special {
	margin: 3px;
	padding-right: 0px;
}

td {
	text-align: left;
}

.d_day {
	width: 14%
}

.concert_day {
	padding-left: 0px;
	width: 18%
}

.concert_time {
	padding-left: 0px;
}

.deadline {
	display: inline-block;
	background: red;
	font-size: 12px;
	font-weight: bold;
	color: black;
	border-radius: 0.2em;
	padding: 0.15em;
}

#count {
	float: right;
	margin-right: 30px;
	display: block;
	margin-bottom: 30px;
}

.out {
	clear: both;
	margin: 0;
}

.box {
	width: 47%;
	margin: 20px;
	float: left;
}
</style>
</head>
<body>
	<!-- Header -->
	<jsp:include page="../mainActivity/navbar.jsp" />

	<!-- 검색 필터 -->
	<div style="text-align: center; padding: 30px; margin: 40px;">
		<c:if test="${!empty search_date }">
			<div class="button special date">
				${search_date }
				<button class="filter_btn" style="padding: 0px; float: right;">
					<img src="images/X.png">
				</button>
			</div>
			<br>
		</c:if>

		<c:if test="${!empty search_local }">
			<c:forEach var="l" items="${search_local }">
				<div class="button special local">
					${l }
					<button class="filter_btn" style="padding: 0px; float: right;">
						<img src="images/X.png">
					</button>
				</div>
			</c:forEach>
			<br>
		</c:if>

		<c:if test="${!empty search_genre }">
			<c:forEach var="g" items="${search_genre }">
				<div class="button special genre">
					${g }
					<button class="filter_btn" style="padding: 0px; float: right;">
						<img src="images/X.png">
					</button>
				</div>
			</c:forEach>
		</c:if>
	</div>



	<!-- 검색 결과 -->
	<c:if test="${!empty flist }">
		<span id="count"> 총 <strong>${flistsize }</strong>개
		</span>

		<div class="out" style="box-sizing: inline-block; width: 100%;">
			<c:forEach var="flist" items="${flist}">
				<div class="box">
					<div class="table-wrapper">
						<input type="hidden" name="concert_id" value="${flist.concert_id}">
						<img src="concertupload/${flist.concert_image }" alt="" />

						<div class="deadline"></div>
						<h4 style="display: inline-block">${flist.concert_name }</h4>
						<p>${flist.concert_musician }</p>

						<table>
							<tr>
								<th>공연 일시</th>
								<td class='d_day'></td>
								<td class="concert_day">${flist.concert_day }</td>
								<td class="concert_time">${flist.concert_open }-
									${flist.concert_close }</td>
							</tr>
							<tr>
								<th>공연 장소</th>
								<td colspan=3>${flist.local_name }</td>
							</tr>
							<tr>
								<th>공연 가격</th>
								<td colspan=3>${flist.concert_price }</td>
							</tr>
							<tr>
								<th>장르</th>
								<td colspan=3>${flist.genre_name }</td>
							</tr>
						</table>

						<a href="ConcertDetailAction.co?detail_concert=${flist.concert_id }" class="button special icon fa-search fit">상세보기</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>

	<c:if test="${empty flist }">
		<div style="margin: 100px; text-align: center; font-size: 50px">
			${none }</div>
	</c:if>

</body>
</html>