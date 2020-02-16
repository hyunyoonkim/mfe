<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Search Result</title>
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

#search_word {
	font-size: 50px;
	padding: 10px;
	margin-top: 50px;
	text-align: center;
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

#count{
	float : right;
	margin-right : 30px;
	display : block;
	margin-bottom : 30px;
}
.out{
	clear : both;
	margin : 0;	
}
.box{
width : 47%;
margin : 20px;
float : left;

}
</style>
</head>

<body>
	<!-- Header -->
	<jsp:include page="../mainActivity/navbar.jsp" />

	<!-- 검색 결과 -->
	<c:if test="${!empty list }">
		<!-- Div -->
		<div id="search_word">
			<strong>${search_word }</strong>의 검색 결과입니다
		</div>

		<span id="count"> 총 <strong>${count }</strong>개
		</span>



		<div class="out" style="box-sizing: inline-block; width: 100%;">
			<c:forEach var="list" items="${list}">
				<div class="box">
					<div class="table-wrapper">
						<input type="hidden" name="concert_id" value="${list.concert_id}">
						<img src="concertupload/${list.concert_image }" alt="" />

						<div class="deadline"></div>
						<h4 style="display: inline-block">${list.concert_name }</h4>
						<p>${list.concert_musician }</p>

						<table>
							<tr>
								<th>공연 일시</th>
								<td class='d_day'></td>
								<td class="concert_day">${list.concert_day }</td>
								<td class="concert_time">${list.concert_open }-
									${list.concert_close }</td>
							</tr>
							<tr>
								<th>공연 장소</th>
								<td colspan=3>${list.local_name }</td>
							</tr>
							<tr>
								<th>공연 가격</th>
								<td colspan=3>${list.concert_price }</td>
							</tr>
							<tr>
								<th>장르</th>
								<td colspan=3>${list.genre_name }</td>
							</tr>
						</table>

						<a href="ConcertDetailAction.co?detail_concert=${flist.concert_id }" class="button special icon fa-search fit">상세보기</a>
					</div>
				</div>
			</c:forEach>
		</div>
	</c:if>

	<c:if test="${empty list }">
		<div style="margin: 100px; text-align: center; font-size: 50px">${none }</div>
	</c:if>

</body>
</html>