<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1" />
      <link rel="stylesheet" href="assets/css/main.css" />
	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script>
$(function() {
	var idCheck =false;
	$("#addMember_id").on('keyup', function() {
		//처음에 pattern에 적합하지 않은 경우 메시지 출력 후
		// 적합한 데이터를 입력해도 계속 같은 데이터 출력하므로
		// 이벤트 시작할 때마다 비워둔다.
		$("addMember_id_message").empty();
		// \w => [A-Za-z0-9_]
		var pattern = /^\w{3,12}$/;
		//var pattern = /^\w+@\w*[.]\w{3}$/;
		var id =$("#addMember_id").val();
	
		if(!pattern.test(id)) {
			$("#addMember_id_message").css('color', 'red')
							.html("아이디 형식으로 맞춰주세요.");
			idCheck = false;
			return;
		}
		$.ajax({
			url : "idcheck.net",
			data : {"id": id},
			success : function(rdata) {
				if(rdata == -1) {
					$("#addMember_id_message").css('color', 'green').html(
							"사용 가능한 아이디 입니다.");
					idCheck=true;
				} else {
					$("#addMember_id_message").css('color', 'blue').html(
							 "사용중인 아이디 입니다.");
					idCheck=false;
				}
			}//if
		});//ajax
	});  // keyup
	
	

	$('#addMember_form').submit(function() {
		if($('#addMember_id').val()=="") {
			alert("ID를 입력하세요");
			$('#addMember_id').focus();
			return false;
		}
		if(idCheck == false) {
			alert("ID 중복되었습니다.");
			return false;
		}
		
		if($('#addMember_pass').val()=="") {
			alert("비밀번호를 입력하세요");
			$('#addMember_pass').focus();
			return false;
		}
		if($('#addMember_name').val()=="") {
			alert("이름을 입력하세요");
			$('#addMember_name').focus();
			return false;
		}
		if($('#addMember_age').val() =="") {
			alert("나이를 입력하세요.");
			$('#addMember_age').focus();
			return false;
		}
		if(!$.isNumeric($("input[name='age']").val())) {
			alert("나이는 숫자로 입력하세요");
			$("input[name='age']").val('');
			$("input[name='age']").focus();
			return false;
		}
		
		if($('input[type=radio]:checked').length==0) {
			alert("성별을 선택하세요");
			return false;
		}
		
		
		if($('#addMember_email').val()=="") {
			alert("E-mail 아이디를 입력하세요");
			$('#addMember_email').focus();
			return false;
		}
		var pattern = /^\w+@\w*[.]\w{3}$/;
		var id =$("#addMember_email").val();
		
		if(!pattern.test(id)) {
			alert("아이디 형식으로 맞춰주세요.");
			$('#addMember_email').focus();
			return;
		}
	
	});
	

});



</script>
</head>
<body>
<div class="container">
   <form action="joinProcess.net" id = "addMember_form">
	 <fieldset>
	   	<div class = "form-group">
			<label for ="addMember_id">아이디</label>
			<input type="text" class="form-control" id="addMember_id"
					   placeholder = "Enter id" name="addMember_id" maxLength="12">
			<span id = "addMember_id_message"></span>
		</div>
		<div class = "form-group">
			<label for ="addMember_pass">비밀번호</label>
			<input type="password" class="form-control" id="addMember_pass"
					   placeholder = "Enter password" name="addMember_pass">
		</div>
		<div class = "form-group">
			<label for = "addMember_name" >이름</label>
			<input type="text" id = "addMember_name" name = "addMember_name" placeholder="Enter name" maxLength="15">
		</div>
		<div class = "form-group">
			<label for = "addMember_age">나이</label>
			<input type="text" id = "addMember_age" name = "addMember_age" placeholder="Enter age" maxLength="2">
		</div>
		<div class="form-group">성별
			<input type="radio" id = "male" name="addMember_gender" value="1" checked>
			<label for="male" class ="modalBlack">남</label>
			<input type="radio" id = "female" name="addMember_gender" value="2">
			<label for="female" class ="modalBlack">여</label>
		</div>
		<div class = "form-group">
			<label for = "addMember_email">이메일</label>
			<input type="email" id = "addMember_email" name = "addMember_email" placeholder="Enter email"  >
		</div>
		<button type="submit">회원가입</button>
		<button type="button">취소</button>
	</fieldset>
   </form>
</div>
</body>
</html>