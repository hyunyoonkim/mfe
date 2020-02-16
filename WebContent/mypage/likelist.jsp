<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	.likeimg {
		width : 100px;
		height : 200px
	}
</style>
<c:forEach var="l"  items="${likelist}">

	<div class="video col ii">
		<div class="image fit">
			<div class="item">
				<img src='concertupload/${l.concert_image }' class="likeimg"/>
				<button type="button" class="btn btn-secondary heart rec_update">
					<span class="glyphicon glyphicon-heart rec_count " aria-hidden="true"></span>
				</button>
				<input type="hidden" value="${l.concert_id }" name="concert_id">
			</div>
			<div>${l.concert_name }</div>
		</div>
	</div>
</c:forEach>

<script>
$(document).ready(function(){
	$(".rec_update").click(
			function() {
				
				$(this).children('span').removeClass('glyphicon-heart');
				$(this).children('span').addClass('glyphicon-heart-empty');
				deleteHeart();
				
				$(this).parent().parent().parent().remove();
				
			});
})


function deleteHeart() {
	var concert_id = $('input[name="concert_id"]').val();
	
	$.ajax({
		type : "post",
		url : 'likeyDelete.lo',
		data : {
			"concert_id" : concert_id
		},
		success : function() {
			
		},
		error : function() {
			
		}
	});
};
</script>