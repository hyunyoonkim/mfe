<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix ="c"  uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page ="../mainActivity/navbar.jsp"/>
<meta charset="UTF-8">
<title>리뷰 상세보기</title>
<style>
	span{
		width:20px;
	}
	.no {
		display : none;
	}
	img {
		width : 300px;
	}
	.container {
		margin-top : 100px;
	}
</style>

</head>
<body>
<div class="container">
	
	<table class= "table table-bordered">
		<tr>
			<th colspan="2">리뷰 상세보기</th>
		</tr>
		<tr>
			<td><div>글쓴이</div></td>
			<td><div>${reviewbean.member_id}</div></td>
		</tr>
		<tr>
			<td><div>제목</div></td>
			<td><div>${reviewbean.review_title}</div></td>
		</tr>
		
		<tr>
			<td><div>공연명</div></td>
			<td>
				<img src='concertupload/${reviewbean.concert_image }'/>
				<div>${reviewbean.concert_name}</div>
				
			</td>
		</tr>
		<tr>
			<td><div>내용</div></td>
			<td><textarea class = "form-control" rows="5" readOnly style = "width:100%">${reviewbean.review_content}</textarea></td>
		</tr>
		<tr>
			<td><div>첨부파일</div></td>
			<td>
				<c:if test="${!empty reviewbean.review_file}">
					<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
					<a href="ReviewFileDown.rv?filename=${reviewbean.review_file}">
						${reviewbean.review_file}</a>
				</c:if>
			</td>
		</tr>
		
		<tr>
			<td colspan ="2" class="center">
			<c:if test="${reviewbean.member_id == id || id == 'admin'}">	
				<a href="ReviewModifyView.rv?num=${reviewbean.review_id}">
					<button class = "btn btn-info">수정</button>
				</a>
				<a href = "#">
					<button class = "btn btn-danger" data-toggle = "modal" data-target = "#myModal">삭제</button>
				</a>
			</c:if>	
				<a href="ReviewList.rv">
					<button class="btn btn-primary">목록</button>
				</a>
				<span id = "no">${reviewbean.review_pass}</span>
			</td>
		</tr>
	</table>
	
	<%-- delete 모달 --%>
<div class="modal" id="myModal">
      <div class="modal-dialog">
         <div class="modal-content">


            <!-- Modal body -->
            <div class="modal-body">
               <form name="deleteForm" action="ReviewDeleteAction.rv"
                  method="post">
                  <input type="hidden" name="num" value="${param.num}">

                  <div class="form-group">
                     <label for="pwd">비밀번호</label> 
                        <input type="password"
                        class="form-control" placeholder="Enter password"
                        name="review_pass" id="review_pass">
                  </div>
                  <button type="submit" class="btn btn-primary" >Submit</button>
                   <button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
               </form>
            </div>
         </div>
      </div>
   </div>		
</div>
</body>
</html>