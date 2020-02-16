<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../mainActivity/navbar.jsp" />
<link rel="stylesheet" href="assets/css/back.css">
<style>
.sp-quantity div {
	display: inline;
}

h3 {
	color: black;
	font-weight: bolder;
}

img {
	width: 100%;
}
</style>
<script type="text/javascript">
	$(function() {
		amount = $("#amount").val();
		price = $("#total").text();
		$('input[name=amount1]').val(amount);
		$('input[name=total1]').val(price);

		$('#myTab a').click(function(e) {
			e.preventDefault()
			$(this).tab('show')
		});

		$(".ddd").on("click", function() {
			console.log("click");
			var $button = $(this);
			var oldValue = $("#amount").val();

			if ($button.text() == "+") {
				newVal = parseInt(oldValue) + 1;
			} else {
				// Don't allow decrementing below zero
				if (oldValue > 1) {
					newVal = parseInt(oldValue) - 1;
				} else {
					newVal = 1;
				}
			}
			$("#amount").val(newVal);
			amount = $("#amount").val();
			$("#total").text(parseInt(amount) * parseInt(price));
			$('input[name=amount1]').val(amount);
			$('input[name=total1]').val(parseInt(amount) * parseInt(price));
		});
	})
</script>


<c:set var="c" value="${cdata}" />

<!-- 중단 : 공연 상세내용/공연장위치/좌석 예약안내/기타 등등 -->
<div class="container">

	<div id="detail">

		<!-- 공연 이미지:썸네일 -->
		<div class="Thumbnail">
			<div class="imageWrap">
				<img src="concertupload/${c.concert_image}">
			</div>
			<!-- //공연 이미지:썸네일 end -->
			<!-- 공연 타이틀/인기도/날짜(in썸네일)-->
			<div class="concertTitle">
				<dl>
					<dd>${c.concert_name}
						<input type="hidden" id="con_name" name="con_name"
							value="${c.concert_name }">
					</dd>
					
					<dd>${c.concert_day },${c.concert_open}~${c.concert_close}</dd>
					
					<!-- 해당 코드 번호에 맞는 장르, 지역을 구분하여 text로 표기 하는 기능 넣기 -->
				</dl>
			</div>
		</div>
		<!-- //공연 타이틀/인기도/날짜:(in썸네일) end-->


		<!-- 상세페이지 중단 : 공연 상세내용/공연장위치/좌석 예약안내/기타 등등 -->
		<div class="content">
			<div role="tabpanel">

				<!-- Nav tabs -->
				<ul class="nav nav-tabs" role="tablist">
					<li role="presentation" class="active"><a href="#concert-info"
						id="myTab" aria-controls="concert-info" role="tab"
						data-toggle="tab">공연 정보</a></li>
					<li role="presentation"><a href="#profile" id="myTab"
						aria-controls="profile" role="tab" data-toggle="tab">티켓 선택</a></li>
					<li role="presentation"><a href="#messages" id="myTab"
						aria-controls="messages" role="tab" data-toggle="tab">공연장 정보</a></li>
					<li role="presentation"><a href="#settings" id="myTab"
						aria-controls="settings" role="tab" data-toggle="tab">예매안내 및 주의사항</a></li>
				</ul>
				<!-- //Nav tabs end -->

				<!-- Tab panes -->
				<div class="tab-content">
					<div role="tabpanel" class="tab-pane active" id="concert-info">
						<div>
							<img src="concertinfo/sum1.png">
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="profile">
						<div class="concert_reserve">
							<div class="concert_date">
								<img src="concertinfo/sum2.png">
							</div>
						</div>
						<!-- 티켓 수량 변경 -->
						<h4 style="color:black; text-align:center;">티켓 수량 선택하기</h4>
						<div class="sp-quantity">
							<div class="sp-minus fff">
								<button class="ddd">-</button>
							</div>
							<div class="sp-input">
								<input type="text" class="quntity-input" name="amount" id="amount" value="1" readOnly/>
							</div>
							<div class="sp-plus fff">
								<button class="ddd">+</button>
							</div>
						</div>
						<!-- //티켓 수량 변경 -->
					</div>
					<div role="tabpanel" class="tab-pane" id="messages">
						<div>
							<div>
								<img src="concertinfo/sum3.png">
							</div>
						</div>
					</div>
					<div role="tabpanel" class="tab-pane" id="settings">
						<div>
							<img src="concertinfo/sum4.png"> 
							<img src="concertinfo/sum4-1.png">
						</div>
					</div>
				</div>
				<!-- //Tab panes end -->

				<!-- 예약하기 버튼  -->
				<form method="post" action="purchaseProcess.bk">
					<div id="layer_fixed" class="reserverbtn">
						<input type="hidden" name="amount1"> 
						<input type="hidden" id="con_id" name="con_id" value="${c.concert_id}"> 
						<input type="hidden" name="total1">
						<button type="submit" class="btn btn-info" id="total">${c.concert_price}</button>
					</div>
				</form>
				<!-- //예약하기 버튼 end-->
			</div>
		</div>
		<!-- //상세페이지 end 중단 : 공연 상세내용/공연장위치/좌석 예약안내/기타 등등 -->
	</div>
</div>

</body>
</html>