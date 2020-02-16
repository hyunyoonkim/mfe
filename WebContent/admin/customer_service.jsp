<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<style>
#messageTextArea {background:#ebecf0; color:black;
font-family :'나눔고딕', 'Malgun Gothic', sans-serif;
border-radius: 7px;
}
#_chatbox { position:fixed; right:0 ; bottom:3em ; z-index:999 !important;
width:300px;
    height:400px;
}
.chat{margin-left:85% !important;}
#chatbar {background:#2565ae; color:white;
border-radius: 7px;
font-family :'나눔고딕', 'Malgun Gothic', sans-serif; text-align:center;
}
#textMessage {background:white; color:black;
border-radius: 7px;
font-family :'나눔고딕', 'Malgun Gothic', sans-serif; }
#sendbox {float:right;}
#send{background:#2565ae; color:white;  }
#send:hover {background:#4fc3f7}
#dis{background:red; color:white; }
</style>
<c:if test="${listcount5 > 0 }">
	

	<%-- 회원이 있는 경우 --%>
	<div class="container container5">

		<table class="table t5">
			<caption>회원 목록</caption>
			<thead>
				<tr>
					<th width="20%">번호</th>
					<th width="35%">아이디</th>
					<th></th>
					<th width="45%">상담선택</th>
				</tr>
			</thead>
			<tbody class="tb5">
				<c:set var="num" value="${listcount5}" />
				<%-- listAction에 limit 변경시 곱하는 값도 같이 변경해야함 --%>
				<c:forEach var="c" items="${ServiceList}">
					<tr>
						<td><c:out value="${num }" />
							<%-- num 출력 --%> <c:set var="num" value="${num-1}" /> <%-- num = num-1 의미함 --%>
						</td>
						<td><c:if test="${c.id != 'admin@mfe.com'}">
								<div>${c.id}</div>
								
							</c:if></td>
						<td></td>
						<td>
							<button class="chat_btn btn-primary small" value="${c.id}" onclick="">상담하기</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>

	
		<div id="_chatbox" style="visibility: hidden">
			<!-- 결과 메시지 보여주는 창 -->
			<div id="chatbar">고객 상담</div>
			<textarea id="messageTextArea" class="idc" rows="10" cols="50"></textarea>						
			<div><input id="textMessage" type="text" ></div>			
			<div id="sendbox">
				<input onclick="sendMessage()" id="send" value="보내기" type="button">			
				</div>

		</div>

	
</c:if>
<script>
$("#textMessage").keydown(function(key) {
    if (key.keyCode == 13) {
       sendMessage();
       return false;

    }
 });
$(".chat_btn").on({
    "click" : function() {
        if ($("#_chatbox").css("visibility") == "hidden") {
            $("#_chatbox").css("visibility", "visible");
          sendid= $(this).val();
          console.log(sendid);
         webSocket = new WebSocket("ws://localhost:8088/te/websocket?"+ '${id}' +"&"+sendid );  //sendid 달아주기
         // 이 id 는 admin
       
          
         
       messageTextArea = document.getElementById("messageTextArea");
     
     //웹 소켓이 연결되었을 때 호출되는 이벤트
     webSocket.onopen = function(message) {
        
        messageTextArea.value += "상담이 시작됩니다..\n";
     };
     //웹 소켓이 닫혔을 때 호출되는 이벤트
     webSocket.onclose = function(message) {
        messageTextArea.value += "상담이 종료되었습니다.\n";                     
     };
     //웹 소켓이 에러가 났을 때 호출되는 이벤트
     webSocket.onerror = function(message) {
        messageTextArea.value += "error...\n";
     };
     //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
     webSocket.onmessage = function(message) {
         if(message.data.substring(6)!="start=")
        messageTextArea.value +=  message.data + "\n";

     };
           
          
            
            
        } else if ($("#_chatbox").css("visibility") == "visible") {
            $("#_chatbox").css("visibility", "hidden");
            disconnect();
        }
    }
});

	function sendMessage() {
		
		var message = document.getElementById("textMessage");
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
</script>

<!--  회원이 없는 경우 -->
<c:if test="${listcount == 0 }">
	<font size=5>등록된 회원이 없습니다.</font>
</c:if>
<br>



