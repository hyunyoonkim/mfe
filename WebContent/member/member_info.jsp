<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page ="../mainActivity/navbar.jsp"/>
<style>
.table {
		background-color : white !important;
	}
	th, td {
		color : black !important;
		text-align : center;
	}
</style>
</head>
<body>
<div class = "container">
<table class = "table">
	<tr>
		<td>아이디</td>
		<td>${mdata.id}</td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td>${mdata.password}</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${mdata.name}</td>
	</tr>
	<tr>
		<td>주소</td>
		<td>${mdata.address}</td>
	</tr>
	<tr>
		<td>폰번호</td>
		<td>${mdata.phone_number}</td>
	</tr>
	<tr>
		<td>선호장르</td>
		<td>${mdata.preference}</td>
	</tr>
	<tr>
		<td>성별</td>
		<td>${mdata.gender}</td>
	</tr>
	<tr>
		<td colspan="2"><a href="member_list.net">리스트로 돌아가기</a></td>
	</tr>
</table>
</div>
</body>
</html>