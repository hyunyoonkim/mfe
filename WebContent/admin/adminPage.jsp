<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix ="c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page ="../mainActivity/navbar.jsp"/>
<title>관리자 페이지</title>
<style>
	.style2 {
		padding-top : 5%;
	}
	.container { 
		width : 100% !important;
		margin :0 !important;
		padding : 0 !important;
	}
	.center-block {
		display : flex;
		justify-content:center; /* 가운데 정렬 */
	}
	li .current {
		background: #bfbebe !important;
	}
	.search_member, .search_concert, .search_board {
		margin-top : 5%;
	}
	.size {
		width : 100%;
		margin :0;
	}
	#viewcount, #concert_search_field, #board_search_field {
		float : left;
		width : 100px;
		height : 40px;
		font-size : 10pt;
	}
	#search_word, #concert_search_word, #board_search_word {
		float : left;
		width : 40%;
		height : 40px;
		margin-left : 1%;
		
	}
	#search_btn, #concert_search_btn, #board_search_btn {
		float : left;
		width : 50px;
		height : 40px;
		margin-left : 1%;
		
	}
	table caption{
		caption-side:top;
		text-align:center;  
		font-size : 30pt;
		font-weight:bold;
		color : white;
	}
	td, th {
		text-align: center !important; 
		line-height : 3em !important;
	}
	.huWidth {
		width : 100% !important;
	}
	.memberDetail:hover, .concertDetail:hover {
		background-color : transparent !important;
		color : #5426f3 !important;
	}
	.rows {
		margin-top : 2%;
		width : 24%;
		float : right;
		margin-bottom : 2%;
	}
	#viewcount2 {
		width : 80%;
	}
	.sp {
		width : 70px;
		display : inline;
	}
	.gray { 
		background : gray;
	}
	#viewcount3 {
		width : 80%;
	}
	.modalTable {
		color : black;
	}
	.modal-body {
		color : black !important;
	}
	label, #concert_add_form select {
		color: black !important;
	}
	#concert_add_form input,  #concert_add_form select, #updateMember_form input {
		border-color : gray !important;
		color : black  !important;
	}
	#updateMember_form input[type="checkbox"] {
		border-color : gray !important;
	}
	
	#upfile{display:none}
	img{width:20px;}
	.remove {
		display : none;
	}
	#upfile_Label {
		display : inline;
	}
	.update_button{
		background-color : dimgray;
	}
	
</style>
 <script src="js/adminJS.js"></script>
</head>
<body>
<script>

</script>
<div id="main">
<section class="wrapper style2">
					<div class="inner">
						<header>
							<h2>MFE 관리자 페이지</h2>
						</header>
						<!-- Tabbed Video Section -->
							<div class="flex flex-tabs">
								<ul class="tab-list">
								<c:if test="${page != null}"> 
									<li><a href="#" data-tab="tab-1" class="active">회원 관리</a></li>
								</c:if>
								
									<li><a  id = "boardTab" href="#" data-tab="tab-2">자유게시판 관리</a></li>
									<li><a  id = "concertTab" href="#" data-tab="tab-3">콘서트 관리</a></li>
									<li><a href="#" data-tab="tab-4">고객센터(상담)</a></li>
								</ul>
								<div class="tabs">

									<!-- Tab 1 -->
										<div class="tab tab-1 flex flex-3 active">
											<jsp:include page ="./member_list.jsp"/>
										</div>

									<!-- Tab 2 -->
										<div class="tab tab-2 flex flex-3">
											<jsp:include page ="./admin_board.jsp"/>
										</div>
									<!-- Tab 3 -->
										<div class="tab tab-3 flex flex-3">
											<jsp:include page ="./admin_concert.jsp"/>
										</div>
									<!-- Tab 4 -->
										<div class="tab tab-4 flex flex-3">
										  						<jsp:include page ="./customer_service.jsp"/>					
										</div>

								</div>
							</div>
					</div>
				</section>
</div>


</body>
</html>