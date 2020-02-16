<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../mainActivity/navbar.jsp" />
<link rel="stylesheet" href="assets/css/purchase.css">

<style>
.gray {
	color: #a9afb4;
}
h2, h3, strong{
	color: black;
}

h2{

	width: 100%
}
#order {
	overflow: hidden;
	width: 550px;
	margin: 0 auto;
	margin-top: 53px;
	padding-bottom: 40px;
	background: #fff;
	padding-bottom: 40px;
}

#order .btn_back {
	padding: 15px;
}

#order .btn_back a {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	padding-left: 20px;
	background: url('images/_img/order/btn_back.png') no-repeat left center/16px auto;
}
#order .btn_back a:hover {
	background-image: url('images/_img/order/btn_back_hover.png');
}

#order .purchasetotal {
	padding: 30px 15px;
}

#order .purchasetotal h2 {
	margin-bottom: 20px;
	font-size: 24px;
	font-weight: bold;
}

#order .purchasetotal .pur:after {
	display: block;
	clear: both;
	content: '';
}

#order .purchasetotal .pur .describe {
	float: left;
	width: 65%;
	padding-right: 25px;
}

#order .purchasetotal .pur .describe>p {
	margin: 5px 0;
	padding-left: 25px;
	background-size: 17px auto;
	background-repeat: no-repeat;
	background-position: left center;
	font-size: 16px;
}

#order .purchasetotal .pur .describe .date {
	background-image: url('images/_img/order/calendar.png');
}

#order .purchasetotal .pur .describe .prc {
	background-image: url('images/_img/order/price.png');
}

#order .purchasetotal .pur .describe .prc .price {
	float: right;
	font-size: 16px;
}

#order .purchasetotal .pur .describe .total_prc {
	padding-left: 0;
	font-size: 20px;
}

#order .purchasetotal .pur .describe .total_prc strong {
	font-size: 24px;
}

#order .purchasetotal .pur .image {
	float: right;
	width: 35%;
}

#order .purchasetotal .pur .image img {
	max-width: 100%;
	height: auto;
}

#order .save>a {
	height: 10px;
}

#order .method {
	padding: 30px 15px;
	border-top: 1px dashed #eee;
}

#order .method h3 {
	margin-bottom: 20px;
	font-size: 16px;
}

#order .method .pur_method .title {
	padding: 15px 15px 15px 70px;
	border: 1px solid #b5b5b5;
	background-repeat: no-repeat;
	background-size: 30px auto;
	background-position: left 20px center;
	font-size: 16px;
}

#order .method .pur_method:last-of-type {
	margin-top: -1px;
}

#order .method .pur_method .pur_cont {
	display: none;
	padding: 15px;
	border: 1px solid #b5b5b5;
	border-top: 0;
}

#order .method .pur_card .pur_cont {
	display: block;
}

#order .method .pur_card .title {
	background-image: url('images/_img/order/card.png');
}

#order .method .pur_card .input-box {
	display: flex;
	width: 100%;
}

#order .method .pur_card .input-box input {
	height: 50px;
	padding: 0 10px;
	font-size: 14px;
	line-height: 50px;
}

#order .method .pur_card .input-box input#cardnumber {
	width: 70%;
}
#order .method .pur_card .input-box input#cardmmyy {
	width: 70px;
	margin: 0 10px;
}

#order .method .pur_card .input-box input#cardcvv {
	width: 70px;
}

#order .method .pur_paypal .title {
	background-image: url('images/_img/order/paypal.png');
}

#order .agree {
	padding-bottom: 50px;
	text-align: center;
}

#order .agree li a {
	font-size: 14px;
}

#order .reserverbtn {
	position: fixed;
	left: 50%;
	bottom: 0;
	width: 550px;
	margin-left: -275px;
}

#order .reserverbtn button {
	width: 100%;
	height: 40px;
	padding: 0;
	border-radius: 0;
	line-height: 40px;
}

#order .layer_agree {
	display: none;
	position: fixed;
	left: 50%;
	top: 50%;
	z-index: 50;
	width: 500px;
	height: 250px;
	margin: -125px 0 0 -250px;
	background: #fff;
}

#order .layer_agree h3 {
	height: 50px;
	border-bottom: 1px solid #eee;
	font-size: 20px;
	text-align: center;
	line-height: 50px;
}

#order .layer_agree .close {
	display: block;
	position: absolute;
	right: 0;
	top: 0;
	width: 50px;
	height: 50px;
	background: url('images/_img/button/close.png') no-repeat center/20px auto;
	color: transparent;
	font-size: 0;
	opacity: 1;
}

#order .layer_agree .inner {
	overflow-y: auto;
	height: 200px;
	padding: 10px;
	background:white;
}

#dimmed {
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	bottom: 0;
	z-index: 10;
	width: 100%;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.5);
}

@media ( min-width : 320px) and (max-width: 767px) {
	.container {
		padding: 0;
	}
	#order {
		width: 100%;
	}
	#order .reserverbtn {
		left: 0;
		width: 100%;
		margin-left: 0;
	}
	#order .layer_agree {
		left: 0;
		top: 0;
		width: 100vw;
		height: 100vh;
		margin: 0;
	}
	#order .layer_agree .inner {
		height: 90vh;
	}
}
</style>

<script type="text/javascript">
	/* 확인 alert */
	$(function() {

		/* goback 클릭시 확인 alert */
		$("#goback").click(function(event) {
			var answer = confirm("결제를 취소하시겠습니까?");
			console.log(answer);//취소를 클릭한 경우 -false
			if (!answer) {//취소를 클릭한 경우
				event.preventDefault();
			}
		})

		/* pay 클릭시 확인 alert */
		$(".reserverbtn .sub").click(function(event) {
			var answer = confirm("결제를 진행하시겠습니까?");
			console.log(answer);//취소를 클릭한 경우 -false
			if (!answer) {//취소를 클릭한 경우
				event.preventDefault();
			}
		})

		/* 카드 유효성 검사 */
		$("#payment").click(function() {
			if ($.trim($("#card_id").val()) == '') {
				alert('카드번호를 입력해주세요.');
				$("#card_id").focus();
				return false;
			}

			if ($.trim($("#card_mmyy").val()) == '') {
				alert('카드 유효기간을 입력해주세요.');
				$("#card_mmyy").focus();
				return false;
			}

			if ($.trim($("#card_cvv").val()) == '') {
				alert('카드뒷면 CVV를 입력해주세요.');
				$("#card_cvv").focus();
				return false;
			}
		})

		/* 총 가격 저장=  (콘서트 가격 + 텍스값 * 티켓수량)*/
		$("#book_payment").val($("#paytotal").text());

		/* 카드번호 저장 */
		$(".save_credit").click(
				function() {
					var CARD_ID = $("#card_id").val();
					var CARD_MMYY = $("#card_mmyy").val();
					var CARD_CVV = $("#card_cvv").val();
					$("#card_number_form").val(CARD_ID);
					senddata = "card_id=" + CARD_ID + "&card_mmyy=" + CARD_MMYY
							+ "&card_cvv=" + CARD_CVV + "&id=${id}";
					alert(senddata)
					$.ajax({
						url : "CardAddAction.bk",
						data : senddata,
						type : "post",
						success : function(rdata) {
							if (rdata == 1) {
								alert("카드 등록 완료");
							} else if (rdata == 2) {
								alert("카드 번호 중복");
							} else {
								alert("카드 등록 실패");
							}
						}
					})
				});
	})

	function layer_agree(layer_name) {
		var layer_name = $('#layer_' + layer_name);
		if (layer_name.css('display') == 'none') {
			layer_name.show();
			$('#dimmed').show();
		} else {
			layer_name.hide();
			$('#dimmed').hide();
		}
	}
</script>
<c:set var="c" value="${cdata}" />
<form method="post" action="BookAddAction.bk">

	<div id="order">
		<div class="btn_back">
			<a onclick="history.go(-1)" id="goback">Go back</a>
		</div>
		<div class="purchasetotal">
			<h2>${c.concert_name}</h2>
			<!-- 공연명 -->
			<div class="pur">
				<!-- 결제detail -->
				<div class="describe">
					<p class="date gray">${c.concert_day}  ${c.concert_open} ~ ${c.concert_close}</p>
					<!-- 공연일시 -->
					<p class="prc gray">
						Ticket price<span class="price pricetag gray">￦${c.concert_price}(x${amount})</span>
					</p>
					<!-- 공연금액(수량) -->
					<p class="prc gray">
						Ticket booking fee<span class="price booingtag gray">￦4,000(x${amount})</span>
					</p>
					<!-- 공연금액 수수료(수량) -->
					<p class="total_prc">
						TOTAL : <strong>￦</strong><strong id="paytotal">${total + 4000*amount}</strong>
					</p>
					<!-- 공연총금액 -->
				</div>
				<!-- //결제detail -->


				<!-- 결제detail_image 그림 end -->


				<div class="image">
					<img src="concertupload/${c.concert_image}">

				</div>
				<!-- 공연이미지 -->
				<!-- //결제detail_image 그림 end -->
			</div>
		</div>
		<div class="method">
			<h3>Choose a way to pay</h3>
			<!-- credit -->
			<div class="pur_card pur_method">
				<p class="title">Card</p>
				<div class="credit pur_cont">
					<div id="input-box" class="input-box">
						<input type="text" id="card_id" class="creditcard number" name="card_id" placeholder="Card Number" required> &nbsp;
						<input type="text" id="card_mmyy" class="creditcard mmyy" name="card_mmyy" placeholder="MM/YY" required> 
						<input type="text" id="card_cvv" class="creditcard cvv" name="card_cvv" placeholder="CVV" required>
					</div>
					<div id="card_message" class="card_message"></div>
					<div class="save">
						<a class="save_credit">Save Credit Card</a>
					</div>
					
				</div>
			</div>
			<!-- //credit -->
			<!-- paypal -->
			<!--
		<div class="pur_paypal pur_method">
			<p class="title">Paypal</p>
			<div class="paypal pur_cont"></div>
		</div>
		-->
			<!-- //paypal -->
		</div>
		<ul class="agree">
			<li><a href="#" onclick="layer_agree('terms'); return false;">Terms and conditions of use</a></li>
			<li><a href="#" onclick="layer_agree('privacy'); return false;">Privacy policy</a></li>
		</ul>
		<!-- layer_agree -->
		<div id="layer_terms" class="layer_terms layer_agree" style="color:black;">
			<h3>Terms and conditions of use</h3>
			<a href="#" onclick="layer_agree('terms'); return false;"
				class="close">close</a>
			<div class="inner">[제 1장 총칙] 제1조 (목적) 이 약관은 ㈜인터파크(이하 "회사"라고
				합니다)가 운영하는 인터넷사이트 및 오프라인 영업장을 통하여 제공하는 전자상거래 관련 서비스(이하 "서비스"라고 합니다)를
				이용함에 있어 회사와 이용자의 권리, 의무 및 책임사항을 정하는 것을 목적으로 합니다. 제2조 (정의) ① "인터파크"라
				함은 회사가 재화 또는 용역을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 또는 용역을 거래할 수
				있도록 설정한 가상의 영업장(www.interpark.com 또는 www.interpark.co.kr)을 말하며, 아울러
				사이버 몰을 운영하는 사업자의 의미로도 사용합니다. 단, 아이포인트몰, 투어, 도서, 공연/영화 및 쇼핑에 한하며 다른
				탭의 영업장 및 www.playdb.co.kr은 제외합니다. ② "이용자"라 함은 회사에 접속하여 이 약관에 따라 회사가
				제공하는 서비스를 받는 회원 및 비회원을 말합니다. ③ "회원"이라 함은 회사에 개인정보를 제공하여 회원등록을 한 자로서,
				회사의 정보를 지속적으로 제공받으며, 회사가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 말합니다. ④ "비회원"이라
				함은 회원에 가입하지 않고 회사가 제공하는 서비스를 이용하는 자를 말합니다. ⑤ "Toping회원"이라 함은 회사의
				티켓예매/ENT서비스에 개인정보를 제공하고, 회원등록을 한 자로서 회비를 납부한 후 Toping회원 기간 내 회사의 정보를
				지속적으로 제공받으며, 회사가 제공하는 유료회원 서비스를 이용할 수 있는 회원으로 티켓부문에 한합니다. ⑥ "어린이
				회원"이라 함은 회사에 보호자의 동의를 얻어 개인정보를 제공하고 회원으로 등록한 만14세 미만의 자로서, 회사의 정보를
				제공받고, 회사가 제공하는 서비스를 계속적으로 이용할 수 있는 자를 의미합니다. ⑦ "오픈마켓"이라 함은 회사를 통하여
				판매자와 구매자간에 물품 매매 거래가 이루어질 수 있는 사이버 거래장소를 온라인으로 제공하는 서비스 및 관련 부가 서비스
				일체를 말합니다 . ⑧ "판매자"라 함은 본 약관 및 오픈마켓 판매자약관을 승인하고, 회사와 서비스 이용계약을 체결한 자를
				말하며, 물품을 판매할 의사로 해당 물품을 회사가 온라인으로 제공하는 양식에 맞추어 등록한 자의 의미로도 사용합니다. ⑨
				"구매자"라 함은 재화 또는 용역을 구입할 의사를 회사가 온라인으로 제공하는 양식에 맞추어 밝힌 이용자를 말하며, 회원 및
				비회원 구매자 모두를 의미합니다. ⑩ "I-Point"라 함은 이용자가 인터파크 및 가맹사이트(인터파크투어 등)에서 재화를
				구입하거나 용역을 이용하고 그 대가를 지급하는데 사용하기 위하여 ㈜인터파크가 발행, 관리하는 마일리지 형태의 선불식
				전자지급수단을 말합니다. 제3조 (약관의 명시와 설명 및 개정) ① 회사는 이 약관의 내용과 상호, 영업소 소재지,
				대표자의 성명, 사업자등록번호, 연락처(전화, 팩스, 전자우편주소 등) 등을 이용자가 알 수 있도록 회사의 초기
				서비스화면(전면)에 게시합니다. 다만 약관의 내용은 이용자가 연결화면을 통하여 볼 수 있도록 할 수 있습니다. ② 회사는
				약관의 규제에 관한 법률, 전자상거래 등에서의 소비자보호에 관한 법률, 소비자기본법 등 관련법을 위배하지 않는 범위 에서
				이 약관을 개정합니다. ③ 회사가 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 회사의
				초기화면이나 팝업화면에 그 적용일자 7일 이전부터 적용일자 전일까지 공지합니다. ④ 회사가 약관을 개정할 경우에는 그
				개정약관은 그 적용일자 이후에 체결되는 계약에만 적용되고 그 이전에 이미 체결된 계약에 대해서는 개정 전의 약관조항이
				그대로 적용됩니다. 다만 이미 계약을 체결한 이용자가 개정약관 조항의 적용을 받기를 원하는 뜻을 제3항에 의한 개정약관의
				공지기간 내에 회사에 송신하여 회사의 동의를 받은 경우에는 개정약관 조항이 적용됩니다. ⑤ 이용자가 가입 시 동의한 약관에
				대해서 이용자가 열람을 요구할 경우, 이용자가 가입 시 기재한 전자우편으로 링크형태로 전송됩니다. ⑥ 본 약관에 정하지
				아니한 사항과 이 약관의 해석에 관하여는 전자상거래 등에서의 소비자보호에 관한 법률 등 관련법령 및 정부기관의 해석 또는
				일반적인 상관례에 따릅니다.</div>
		</div>
		<div id="layer_privacy" class="layer_privacy layer_agree" style="color:black;">
			<h3>Privacy policy</h3>
			<a href="#" onclick="layer_agree('privacy'); return false;" class="close">close</a>
			<div class="inner">(주)인터파크 개인정보처리방침 (주)인터파크(www.interpark.com
				이하 "회사"라 함)는 "정보통신망이용촉진및정보보호등에관한법률"(이하 “정통망법”이라 함)등 모든 관련법규를 준수하며
				회원님의 개인정보가 보호받을 수 있도록 최선을 다하고 있습니다. 회사는 개인정보처리방침의 공개를 통하여 회원 여러분의
				개인정보가 어떠한 목적과 방식으로 이용되고 있으며 개인정보보호를 위해 어떠한 조치가 취해지고 있는지를 알려드립니다. 본
				개인정보처리방침은 관련법령의 개정이나 당사 내부방침에 의해 변경될 수 있으며 회원가입 시나 사이트 이용 시에 수시로
				확인하여 주시기 바랍니다. 용어의 정의 본 개인정보처리방침에서의 사용되는 용어의 정의는 다음과 같다. ① "인터파크"라
				함은 (주)인터파크가 재화 또는 용역을 이용자에게 제공하기 위하여 컴퓨터 등 정보통신설비를 이용하여 재화 또는 용역을
				거래할 수 있도록 설정한 가상의 영업장을 말하며, 아울러 사이버 몰을 운영하는 사업자들의 의미로도 사용합니다. ②
				"인터파크 사이트" (이하 "사이트"라 한다)란 "회사"에 회원으로 등록한 이용자가 다양한 정보와 서비스를 제공받을 수
				있도록 회사가 제작, 운영하는 인터넷 웹사이트를 의미합니다. ③ "인터파크 회원"(이하 "회원"이라 한다)이란 "회사"
				들에게 개인정보제공의 동의와 함께 회원등록을 하고 ID를 부여 받은 회원 또는 그 회원전체를 의미하며, 회원 등록 시 부여
				받은 One ID로 (주)인터파크를 포함한 "(주)인터파크 관계회사"의 "사이트"에 동일 ID로 자유롭게 가입할 수
				있으며, 이때의 가입은 관계사일 경우, 개인정보 수집 동의 절차만으로도 이용이 가능함을 의미합니다. ④ "이용자"라 함은
				인터파크에 접속하여 이 방침에 따라 회사가 제공하는 서비스를 받는 회원 및 비회원을 말합니다. ⑤ "회원"이라 함은
				"회사"에 개인정보를 제공하여 회원등록을 한 자로서, "인터파크 회원"에 가입한 자 를 의미합니다. ⑥ "비회원"이라 함은
				회원에 가입하지 않고 회사가 제공하는 서비스를 이용하는 자를 말합니다. 1. 개인정보의 수집항목 및 이용목적 2. 개인정보
				수집에 대한 동의 3. 수집하는 개인정보의 이용 및 보유기간 4. 개인정보의 열람, 갱신, 수정 또는 삭제 5. 동의철회
				및 파기 6. 제3자에 대한 제공 및 위탁처리업체 7. 개인정보보호를 위해 회원들이 알아야 할 사항 8. 비회원 고객의
				개인정보수집 및 보호 9. 아동의 개인정보보호 10. 쿠키의 운영 및 활용 11. 링크사이트 12. 개인정보 보호를 위한
				기술적, 관리적 대책 13. 게시물에 포함된 개인정보 14. 개인정보관련 의견수렴 및 불만처리에 관한 사항 15. 개인정보
				보호책임자 및 담당자의 소속, 성명 및 연락처 16. 방침변경등에 대한 고지</div>
		</div>


		<!-- hidden input 값 넘기기위해 사용 -->
		<input type="hidden" name="con_id" id="con_id" value="${c.concert_id}">
		<input type="hidden" name="con_name" id="con_name"value="${c.concert_name}"> 
		<input type="hidden" name="con_day" id="con_day" value="${c.concert_day}"> 
		<input type="hidden" name="con_open" id="con_open" value="${c.concert_open}">
		<input type="hidden" name="con_close" id="con_close" value="${c.concert_close}"> 
		<input type="hidden" name="amount" id="amount" value="${amount}"> 
		<input type="hidden" name="con_price" id="con_price" value="${c.concert_price}"> 
		<input type="hidden" name="member_id" id="member_id" value="${id}"> 
		<input type="hidden" name="con_image" id="con_image" value="${c.concert_image}"> 
		<input type="hidden" name="card_number" id="card_number_form"> 
		<input type="hidden" name="book_payment" id="book_payment">

		<!-- PAY 버튼  -->
		<div id="layer_fixed" class="reserverbtn">
			<button type="submit" class="btn btn-info sub" id="payment">PAY</button>
		</div>
		<!-- //PAY end-->
	</div>
</form>

</body>
</html>