<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page ="../mainActivity/navbar.jsp"/>
<title>마이 페이지</title>
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
	.search_member {
		margin-top : 5%;
	}
	.size {
		width : 100%;
		margin :0;
	}
	#viewcount {
		float : left;
		width : 100px;
		height : 40px;
		font-size : 10pt;
	}
	#search_word {
		float : left;
		width : 40%;
		height : 40px;
		margin-left : 1%;
		
	}
	#search_btn {
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
	.memberDetail:hover {
		background-color : transparent !important;
		color : #5426f3 !important;
	}
	.rows {
		margin-top : 2%;
		width : 24%;
		float : right;
		margin-bottom : 2%;
	}
	#viewcount {
		display : inline;
		width : 70px;
		height : 28px;
	}
	.sp {
		width : 70px;
	
	}
	.gray { 
		background : gray;
	}
	#viewcount3 {
		width : 80%;
	}
	.admin_chat {
		clear : both;
		display : inline-block;
		float: left;
		background : grey;
		border-radius : 10px;
		margin : 10px;
		padding-left : 10px;
		padding-right : 10px;
		
	}
	.user_chat {
		clear : both;
		display : inline-block;
		float : right;
		background : #01A9DB;
		border-radius : 10px;
		margin : 10px;
		padding-left : 10px;
		padding-right : 10px;
		
	}
	
</style>
 <script src="js/mypage.js"></script>
</head>
<body>
<div id="main">
<section class="wrapper style2">
					<div class="inner">
						<header>
							<h2>마이페이지</h2>
						</header>
						<!-- Tabbed Video Section -->
							<div class="flex flex-tabs">
								<ul class="tab-list">
									<c:if test="${page != null}"> 
									<li><a href="#" data-tab="tab-1" class="active">예매내역</a></li>
								</c:if>
									<li><a  href="#" data-tab="tab-2">찜목록</a></li>
									<li><a href="#" data-tab="tab-3">정보수정</a></li>
									<li><a id = "chatTab" href="#" data-tab="tab-4">상담내역</a></li>

								</ul>
								<div class="tabs">

									<!-- Tab 1 -->
										<div class="tab tab-1 flex flex-3 active">
											<jsp:include page ="booklist.jsp"/>
										</div>

									<!-- Tab 2 -->
										<div class="tab tab-2 flex flex-3">
											<jsp:include page ="likelist.jsp"/>
										</div>

									<!-- Tab 3 -->
										<div class="tab tab-3 flex flex-3">
											<jsp:include page ="updateForm.jsp"/>
										</div>
										
									<!-- Tab 4 -->
									<div class="tab tab-4 flex flex-3">
										<jsp:include page ="chatlist.jsp"/>
									</div>

								</div>
							</div>
					</div>
				</section>
</div>


</body>
</html>