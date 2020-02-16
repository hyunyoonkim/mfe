<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix ="c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page ="../mainActivity/navbar.jsp"/>
<link rel="stylesheet" type="text/css"
	href="assets/css/board.css" />
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
#review_genre{
	width: 10%;
   display: inline-block;
   height: 32px;
}
</style> 

<meta charset="UTF-8">
<title>리뷰 게시판</title>
<script src="js/reviewlist.js"></script>
</head>
<body> 


<input type="hidden" name = "concert_id" value="${reviewbean.concert_id}">

<div class = "container">
	
	<div class = "rows ">
		<span class = "sp">목록 갯수</span>
		<br>
		<select class = "form-control"  id = "viewcount3" >
			<option value = "1">1</option>
			<option value = "3">3</option>
			<option value = "5">5</option>
			<option value = "7">7</option>
			<option value = "10" selected>10</option>
		</select>
	</div>
	

	<table class = "table">
		<thead>
			<tr>
				<th colspan = "3">리뷰 게시판</th>
				<th colspan = "3">
					<font size=3>글 개수 : ${listcount }</font>
				</th>
			</tr>
			<tr>
				<th><div>번호</div></th>
				<th><div>콘서트</div></th>
				<th><div>제목</div></th>
				<th><div>작성자</div></th>
				<th><div>날짜</div></th>
				<th><div>조회수</div></th>
			</tr>
		</thead>
		
		<tbody>
			<c:set var ="num" value = "${listcount-(page-1)*10}"/>
			
			<!--  게시글이 없는 경우 -->
			<c:if test ="${listcount == 0 }">
				<tr>
					<td colspan="6" style="text-align: center">등록된 리뷰가 없습니다.</td>
				</tr>
			</c:if>
					
			<%-- 게시글이 있는 경우 --%>
			<c:if test ="${listcount > 0 }">
				<c:forEach var ="r" items="${reviewlist}">	
				
					<tr>
						<td>
							<c:out value="${num }"/>
							<c:set var = "num" value ="${num-1}"/> 
						</td>
						<td>
							<div>
								<img src='concertupload/${r.concert_image }'/>
							</div>
						</td>
						<td>
							<a href="ReviewDetailAction.rv?num=${r.review_id }">
								${r.review_title}
							</a>
						</td>
						<td>
							<div>${r.member_id}</div>
						</td>
						<td>
							<div>${r.review_date}</div>
						</td>
						<td>
							<div>${r.review_readcount}</div>
						</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table> 
		
	<button id = "addReview_Button" type = "button" class = "btn btn-info">리뷰 등록</button>
	
	<c:if test="${listcount > 0 }">
	<%-- <div>
		<select name="search_select" size="1" id="search_select">
			<option value="concert_name">콘서트명</option>
			<option value="review_title">제목</option>
			<option value="concert_genre">장르</option> 
		</select>
		<input type="text" id="search_text" name="search_text" 
			placeholder="검색할 내용을 입력하세요.">
		<div id="review_genre"></div>
		<button type="submit" id="search_btn">검색</button>
	</div>
		
	<script>
		장르 선택 시 input text 사라지고 select나오게 
		 $(function(){
			 
			 $( "#review_genre" ).hide();
			 $("#search_select").click(function(){
				 
				 var search_val = $("#search_select option:selected").val();
				 
		    	 if(search_val=="concert_genre"){
		    		 $("#search_text").hide();
		    		 $( "#review_genre" ).show();
		    		
		    	 } else {
		    		 $("#search_text").show();
		    		 $( "#review_genre" ).hide();
		    	 }
		    	
			 })
			 var html = '<select name="genre_select" size="1" id="genre_select">'
	    		 html += '<option value="1">발라드</option>'
						+ '<option value="2">록/메탈</option>'
						+'<option value="3">랩/힙합</option>'
						+'<option value="4">R&amp;B/Soul</option>'
						+'<option value="5">jazz</option>'
						+'<option value="6">classic</option>'
						+'<option value="7">pop</option>'
						+'<option value="8">EDM</option>'
				html += '</select>'
				
				$( "#review_genre" ).append( html );


		  })
	</script> --%>
	
	
	<div class="center-block">
         <div class="row">
            <div class="col">
               <ul class="pagination">
                  <c:if test="${page <= 1 }">
                     <li class="page-item">
                     <a class="page-link" href="#">이전&nbsp;</a>
                     </li>
                  </c:if>
                  <c:if test="${page > 1 }">
                     <li class="page-item">
                     <a href="ReviewList.rv?page=${page-1 }"
                        class="page-link">이전</a>&nbsp;
                     </li>
                  </c:if>
                  
                  <c:forEach var="a" begin="${startpage }" end="${endpage }">
                     <c:if test="${a == page }">
                        <li class="page-item">
                           <a class="page-link" href="#">${a }</a>
                        </li>
                     </c:if>
                     <c:if test="${a != page }">
                        <li class="page-item">
                           <a href="ReviewList.rv?page=${a }"
                              class="page-link">${a }</a>
                        </li>
                     </c:if>
                  </c:forEach>
                  
                  <c:if test="${page >= maxpage }">
                     <li class="page-item">
                        <a class="page-link" href="#">&nbsp;다음</a>
                     </li>
                  </c:if>
                  <c:if test="${page < maxpage }">
                     <li class="page-item">
                        <a href="ReviewList.rv?page=${page+1 }"
                           class="page-link">&nbsp;다음</a>
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