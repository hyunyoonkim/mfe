<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

 
   <script src="js/booklist.js"></script>
		<!-- 게시글이 있는 경우 -->
	<c:if test="${listcount > 0}">
		<div class = "rows ">
			<span class = "sp">목록 갯수</span>
			<br>
			<select class = "form-control"  id = "viewcount" >
				<option value = "1">1</option>
				<option value = "3">3</option>
				<option value = "5">5</option>
				<option value = "7">7</option>
				<option value = "10" selected>10</option>
			</select>
		</div>
			
		<div class="container container2">
		<table class="table t2">
			<thead>
				<tr>
					<th colspan = "4">예매내역</th>
					<th colspan = "3">
						<font size=3>예매 목록 개수 : ${listcount }</font>
					</th>
				</tr>
				<tr>
					<th width="10%">예매번호</th>
					<th width="20%">예매일</th>
					<th width="25%">공연명</th>
					<th width="20%">관람일시</th>
					<th width="5%">매수</th>
					<th width="10%">티켓번호</th>
					<th width="10%"></th>
				</tr>
			</thead>
			<tbody class="tb2">
				<c:set var="num" value="${listcount-(page-1)*10 }"/>
				<%-- <c:set var="today" value="<%=new java.util.Date()%>"/> --%>
				<%-- <fmt:formatDate var="now" type="date" value="${today}" pattern="yyyy-MM-dd"/> --%>
				<jsp:useBean id="now" class="java.util.Date" scope="request"/>
				<fmt:parseNumber value="${now.time/(1000*60*60*24)}" integerOnly="true" var="nowDays" scope="request"/>
				<c:forEach var="b"  items="${booklist}">
					<tr>
						<td>
							<div><c:out value="${num }"/></div>
							<c:set var="num" value="${num-1 }"/>
						</td>
						<td>
							<div>${b.book_date}</div>
						</td>
						<td>
							<div><a href="ConcertDetailAction.co?detail_concert=${b.concert_id }">${b.concert_name }</a></div> <!-- 콘서트 상세정보 모달로 띄울거임 -->	
						</td>
						<td>
							<div>${b.concert_day}</div>	
						</td>
						<td>
							<div>${b.book_amount}</div>
						</td>
						<td>
							<div>${b.book_eticket}</div>
						</td>
						<td>
							<div>
								<fmt:parseNumber value="${b.concert_day.time/(1000*60*60*24)}" integerOnly="true" var="cDay" scope="page"/>
								
								<c:if test="${cDay-nowDays<0}">
									<a href="ReviewList.rv" class="">리뷰쓰기</a>
								</c:if>
								<c:if test="${cDay-nowDays>=0}">
									<a href="BookDeleteAction.bk?book_id=${b.book_id}" class="book-cancel">예매취소</a>
								</c:if>
							</div>
						</td>
					</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
<script>
	$(function() {
		$(".book-cancel").click(function(event) {
			var answer = confirm("예매를 취소하시겠습니까?");
			if(!answer) {
				event.preventDefault();
			}
		});
	})
</script>
	<div class="center-block block2">
        <div class="row">
           <div class="col huWidth">
              <ul class="pagination huWidth pa2">
                 <c:if test="${page <= 1 }">
                    <li class="page-item">
                    <a class="page-link" href="#">이전&nbsp;</a>
                    </li>
                 </c:if>
                 <c:if test="${page > 1 }">
                    <li class="page-item">
                    <a href="mypage.bk?page=${page-1 }"
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
                          <a href="mypage.bk?page=${a }"
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
                       <a href="mypage.bk?page=${page+1 }"
                          class="page-link">&nbsp;다음</a>
                    </li>
                 </c:if>
              </ul>
           </div>
        </div>
	            </div>
		</c:if>
		
		<!-- 게시글이 없는 경우 -->
		<c:if test="${listcount == 0}">
			<font size=5>예매내역이 없습니다.</font>
		</c:if>
		<br>
		
	