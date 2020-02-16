<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    

<script src="js/chatlist.js"></script>
<input type="hidden" id="id" value=${id }>
<div class="container">
		<!-- 게시글이 있는 경우 -->
	<c:if test="${chatlistcount > 0}">
	<div class="container container2">
	
	
			<table class="table t2">
				<thead>
					<tr>
						<th>상담내역</th>
						<th>
							<font size=3>채팅 목록 개수 : ${chatlistcount }</font>
						</th>
					</tr>
					<tr>
						<th width="30%">채팅번호</th>
						<th width="70%">상세보기</th>
					</tr>
				</thead>
				<tbody class="tb2">
				
				<c:set var="num" value="1"/>
				<c:forEach var="c"  items="${chatloglist}">
											<tr>
												<td>
													<div><c:out value="${num }"/></div>
													<c:set var="num" value="${num+1 }"/>
												</td>
												<td>
													<div>
													
														<button class="chat_date" >상세보기</button>	
														<form class="chat_detail">
															 <input type="hidden" value="${c.chat_log_id2 }" name="log_id2">
															<div class="chch">
															
															</div>
														</form>
													</div>
												</td>
											</tr>
											
											 </c:forEach>
				</tbody>
			</table>
			</div>
			


		</c:if>
</div>
		<!-- 게시글이 없는 경우 -->
		<c:if test="${chatlistcount == 0}">
			<font size=5>상담내역이 없습니다.</font>
		</c:if>

