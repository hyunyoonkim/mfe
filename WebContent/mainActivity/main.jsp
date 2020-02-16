<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="WebSocket.*"%>

<!DOCTYPE HTML>

<html>
<head>
<title>Music For Everyone</title>
<jsp:include page="navbar.jsp" />
<style>
#messageTextArea {
	background: #ebecf0;
	color: black;
	font-family: '나눔고딕', 'Malgun Gothic', sans-serif;
	font-size: 15px;
	border-radius: 7px;
}

#_chatbox {
	position: fixed;
	right: 0;
	bottom: 8em;
	z-index: 999 !important;
	width: 300px;
	height: 400px;
}

.chat {
	float: right;
	margin-right: 10%;
	position: fixed;
	right: 0;
	bottom: 5em;
	z-index: 999 !important
}

#chatbar {
	background: #2565ae;
	color: white;
	border-radius: 7px;
	font-family: '나눔고딕', 'Malgun Gothic', sans-serif;
	text-align: center;
}

#textMessage {
	background: white;
	color: black;
	border-radius: 7px;
	font-family: '나눔고딕', 'Malgun Gothic', sans-serif;
}

#sendbox {
	float: right;
}

#send {
	background: #2565ae;
	color: white;
}

#send:hover {
	background: #4fc3f7
}

#dis {
	background: red;
	color: white;
}

.main_concert_image:hover {
	-webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	-ms-transform: scale(1.1);
	-o-transform: scale(1.1);
	transform: scale(1.1);
}

.main_concert_image {
	overflow: hidden;
}
</style>
</head>
<body>
	<!-- 헤더 -->
	<section id="banner">
		<div class="inner">
			<header>
				<h1>Music For Everyone</h1>
				<p>다양한 공연을 즐기세요</p>
			</header>
		</div>
	</section>
	<div id="main">
		<form id="detail_concert_form" action="ConcertDetailAction.co" method="post"
			style="display: none">
			<input id="detail_concert" type="text" name="detail_concert">
		</form>
		<section class="listWrapper">
			<c:choose>
				<c:when test="${!empty id}">
					<!--  로그인이 된 경우 -->
					<h4>맞춤형 콘서트</h4>
					<div class="slider">
						<c:forEach var="custom_concert" items="${custom_concert_list}">
							<div class="item">
								<img class="main_concert_image"
									src="concertupload/${custom_concert.concert_image}"
									alt="${custom_concert.concert_id}">
								<button type="button"
									class="btn btn-secondary heart heart_button">
									<span class="glyphicon glyphicon-heart-empty"
										aria-hidden="true"></span>
								</button>
							</div>
						</c:forEach>
					</div>
				</c:when>
				<c:otherwise>
					<!-- 로그인이 안된경우 -->
					<h4>최신 콘서트</h4>
					<div class="slider">
						<c:forEach var="last_concert" items="${last_concert_list}">
							<div class="item">
								<img class="main_concert_image"
									src="concertupload/${last_concert.concert_image}"
									alt="${last_concert.concert_id}">
								<button type="button"
									class="btn btn-secondary heart heart_button">
									<span class="glyphicon glyphicon-heart-empty"
										aria-hidden="true"></span>
								</button>
							</div>
						</c:forEach>
					</div>
				</c:otherwise>
			</c:choose>
		</section>

		<section class="listWrapper">
			<h4>발라드 콘서트</h4>
			<div class="slider">
				<c:forEach var="balad_concert" items="${balad_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${balad_concert.concert_image}"
							alt="${balad_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>Rock 콘서트</h4>
			<div class="slider">
				<c:forEach var="rock_concert" items="${rock_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${rock_concert.concert_image}"
							alt="${rock_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
			
		</section>

		<section class="listWrapper">
			<h4>랩/힙합 콘서트</h4>
			<div class="slider">
				<c:forEach var="rap_concert" items="${rap_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${rap_concert.concert_image}"
							alt="${rap_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>R&amp;B/Soul 콘서트</h4>
			<div class="slider">
				<c:forEach var="rb_concert" items="${rb_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${rb_concert.concert_image}"
							alt="${rb_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>Jazz 콘서트</h4>
			<div class="slider">
				<c:forEach var="jazz_concert" items="${jazz_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${jazz_concert.concert_image}"
							alt="${jazz_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>Classic 콘서트</h4>
			<div class="slider">
				<c:forEach var="classic_concert" items="${classic_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${classic_concert.concert_image}"
							alt="${classic_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>POP 콘서트</h4>
			<div class="slider">
				<c:forEach var="pop_concert" items="${pop_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${pop_concert.concert_image}"
							alt="${pop_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>

		<section class="listWrapper">
			<h4>EDM 콘서트</h4>
			<div class="slider">
				<c:forEach var="edm_concert" items="${edm_concert_list}">
					<div class="item">
						<img class="main_concert_image"
							src="concertupload/${edm_concert.concert_image}"
							alt="${edm_concert.concert_id}">
						<button type="button" class="btn btn-secondary heart heart_button">
							<span class="glyphicon glyphicon-heart-empty" aria-hidden="true"></span>
						</button>
					</div>
				</c:forEach>
			</div>
		</section>
	</div>


	  <c:choose>
   <c:when test="${id!=null && !id.equals('admin@mfe.com')}">
   <section>
      <!-- 채팅 부분 -->     
     
  
   <div id="_chatbox" style="visibility:hidden;" >
         <!-- 결과 메시지 보여주는 창 -->
         <div id="chatbar">고객 상담</div>
         <textarea id="messageTextArea" class="idc" rows="10" cols="50" ></textarea>                  
         <div><input id="textMessage" type="text" disabled ></div>         
         <div id="sendbox">
      
            <input onclick="sendMessage()" id="send" value="보내기" type="button" disabled >
            
            </div>
      </div>

   
  
   <img class="chat"  src="images/open1.png" />
   </section>   
   </c:when>
   </c:choose>



	<!-- Footer -->
	<footer id="footer">
		<div class="inner">
			<div class="flex flex-3">
				<div class="col">
					<h3>Contact us</h3>
					<ul class="alt">
						<li><a href="#">dkaskgkdua@mfe.com</a></li>
						<li><a href="#">press@mfe.com</a></li>
						<li><a href="#">Enjoy Your Life!</a></li>
					</ul>
				</div>
				<div class="col">
					<h3>MFE</h3>
					<ul class="alt">
						<li><a href="#">We are hiring!</a></li>
						<li><a href="#">MFE Zone</a></li>
					</ul>
				</div>
				<div class="col">
					<h3>Legal</h3>
					<ul class="alt">
						<li><a href="#">FAQ</a></li>
						<li><a href="#">Terms of Use</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="#">Cookies Policy</a></li>
						<li><a href="#">Giveaways T&C's</a></li>
						<li><a href="#">Vouchers T&C's</a></li>
					</ul>
				</div>
			</div>
		</div>
		<div class="copyright">
			<ul class="icons">
				<li><a href="#" class="icon fa-twitter"><span class="label">Twitter</span></a></li>
				<li><a href="#" class="icon fa-facebook"><span
						class="label">Facebook</span></a></li>
				<li><a href="#" class="icon fa-instagram"><span
						class="label">Instagram</span></a></li>
				<li><a href="#" class="icon fa-snapchat"><span
						class="label">Snapchat</span></a></li>
			</ul>
			&copy; Untitled. Design: <a href="https://templated.co">MFE-TEAM</a>.
			Images: <a href="https://unsplash.com">Coverr</a>. Video: <a
				href="https://coverr.co">Coverr</a>.
		</div>
	</footer>



	<script>
		//슬릭 이벤트

		$.noConflict();
		var array = new Array();
		<c:forEach var="likey" items="${likey_list}">
		array.push("${likey}");
		</c:forEach>

		$(".main_concert_image").each(
				function(index, item) {
					$.each(array, function(index2, item2) {
						if ($(item).attr("alt") == item2) {
							$(item).next().children('span').removeClass(
									'glyphicon-heart-empty');
							$(item).next().children('span').addClass(
									'glyphicon-heart');
						}
					})
				})
		$('.slider').slick({
			centerMode : true,
			slidesToShow : 10,
			infinite : true,
			slidesToscroll : 9,
			responsive : [ {
				breakpoint : 1800,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 9,
					slidesToscroll : 8
				}
			}, {
				breakpoint : 1600,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 8,
					slidesToscroll : 7
				}
			},{
				breakpoint : 1400,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 7,
					slidesToscroll : 6
				}
			}, {
				breakpoint : 1200,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 6,
					slidesToscroll : 5
				}
			}, {
				breakpoint : 1000,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 5,
					slidesToscroll : 4
				}
			}, {
				breakpoint : 850,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 4,
					slidesToscroll : 3
				}
			}, {
				breakpoint : 700,
				settings : {
					centerMode : true,
					infinite : true,
					slidesToShow : 3,
					slidesToscroll : 3
				}
			}, {
				breakpoint : 550,
				settings : {
					arrows : false,
					centerMode : true,
					infinite : true,
					slidesToShow : 2,
					slidesToscroll : 3
				}
			}, {
				breakpoint : 400,
				settings : {
					arrows : false,
					centerMode : true,
					infinite : true,
					centerPadding : '10px',
					slidesToShow : 2,
					slidesToscroll : 3
				}
			} ]
		}); //슬릭 이벤트 종료
		$("#textMessage").keydown(function(key) {
			if (key.keyCode == 13) {
				sendMessage();
				return false;

			}
		});
		function sendMessage() {
			message = document.getElementById("textMessage");
			id = "${id}";
			//messageTextArea.value += id+ "(님) : " +  message.value + "\n"; 
			//웹소켓으로 textMessage객체의 값을 보낸다.
			webSocket.send(id + "(님) : " + message.value);
			//textMessage객체의 값 초기화
			message.value = "";
		}
		//웹소켓 종료
		function disconnect() {
			webSocket.close();
		}
		//채팅내용이 많아지면 스크롤바가 자동으로 내려감
		<%--
		window.setInterval(function() {
			var elem = document.getElementById('messageTextArea');
			elem.scrollTop = elem.scrollHeight;
		}, 0);--%>
		$(".chat").on({
            "click" : function() {
                if ($(this).attr("src") == "images/open1.png") {
                    $(".chat").attr("src", "images/cancel.png");
                    $("#_chatbox").css("visibility", "visible");
                    
                 webSocket = new WebSocket("ws://localhost:8088/te/websocket?"+'${id}');
               
               messageTextArea = document.getElementById("messageTextArea");
             
             //웹 소켓이 연결되었을 때 호출되는 이벤트
             webSocket.onopen = function(message) {
                
                messageTextArea.value += "다른 고객님과의 상담이 진행 중 입니다.\n";
                messageTextArea.value += "잠시만 기다려주세요.\n";
             };
             //웹 소켓이 닫혔을 때 호출되는 이벤트
             webSocket.onclose = function(message) {
                 $("#textMessage").prop("disabled",true);
                 $("#send").prop("disabled",true);
                messageTextArea.value += "상담이 종료되었습니다.\n";                     
             };
             //웹 소켓이 에러가 났을 때 호출되는 이벤트
             webSocket.onerror = function(message) {
                messageTextArea.value += "error...\n";
             };
             //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
             webSocket.onmessage = function(message) {
                   //message.data값에서 처음부터6개 "start=" 를 잘라서 그 값이 ex)start= 이면 isConnected를 true 로 준다.
                   //사용자가 aaaa@mfe.com인경우 false->true
                   console.log(message.data.substring(6))
                   customer = message.data.substring(6);
                   if(message.data.substring(0,6)!="start="){
                messageTextArea.value +=  message.data + "\n";
                   } else  {
                      //1.customer 구하기
                      //2.customer와 ${id} 비교하기
                      if(customer=='${id}'){
                         console.log("들어왔습니다.");
                         $("#textMessage").prop("disabled",false);
                         $("#send").prop("disabled",false);
                      }
                      //3. 같으면 disabled 활성화해주기
                   }

             };
                   
                  
                    
                    
                } else if ($(this).attr("src") == "images/cancel.png") {
                    $(".chat").attr("src", "images/open1.png");
                    $("#_chatbox").css("visibility", "hidden");
                    disconnect();
                }
            }
        });
		/* 찜하기 클릭*/
		$(".heart").click(
				function() {
					if ("${id}" != "") { // id가 널이 아님
						if ($(this).children('span').hasClass(
								"glyphicon-heart-empty") === true) { // 하트모양이 비어 있을 때(찜하기)
							$(this).children('span').removeClass(
									'glyphicon-heart-empty');
							$(this).children('span')
									.addClass('glyphicon-heart');
							insertHeart($(this).prev().attr("alt"));
						} else { // 하트모양이 차 있을 때(찜 취소)
							$(this).children('span').removeClass(
									'glyphicon-heart');
							$(this).children('span').addClass(
									'glyphicon-heart-empty');
							deleteHeart($(this).prev().attr("alt"));
						}
					} else { //id가 널임(로그인 창으로)
						$('#nav_login_button').trigger('click');
					}
				});
		// 이미지 클릭시
		$(".main_concert_image").click(function() {
			// alt값을 가져와서 숨긴 input에다가 값을 넣는다.
			$("#detail_concert").val($(this).attr("alt"));
			// input이 담긴 폼을 submit 한다. (콘서트 id 값을 들고 감)
			$("#detail_concert_form").submit();
		})

		function insertHeart(concert_id) {
			$.ajax({
				type : "post",
				url : 'likeyAdd.lo',
				data : {
					"concert_id" : concert_id
				},
				success : function() {
				},
				error : function() {
					alert("에러");
				}
			});
		};
		function deleteHeart(concert_id) {
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
</body>
</html>