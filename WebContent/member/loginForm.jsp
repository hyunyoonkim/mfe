<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
    .container{width:50%;border:1px solid lightgray;
               padding:20px;margin-top:20px}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script>
	$(function() {
		
			
		
		$(".join").click(function() {
			location.href="join.net";
		});
		
	});
	
	
	
</script>
</head>
<body>
   <div class ="container">
  <form method ="post" action = "loginProcess.net" name = "loginform" id ="loginform"> 
 
    <fieldset>
     <legend>로그인</legend>
     <div class = "form-group">
     	<label for ="id"><b>ID</b></label><br>
     	<input class = "form-control" type = "text" size="10px" name = "id" id="id" placeholder ="Enter id" maxlength ="10">
     </div>	
     <div class = "form-group">	
     	<label for ="pass"><b>비밀번호</b></label><br>
     	<input class = "form-control" type = "password" name ="pass" id="pass" placeholder = "Enter Password" >
     </div>
     <div class = "form-group">
     	<input = type = "checkbox" id = "remember" value = "remember" name = "remember" >
     	<label for = "remember">Remember me</label>
     </div>		
     	<button class = "btn btn-default join" type = "button" value ="회원가입">회원가입</button>
     	<button class = "btn btn-default" id = "button1" type = "reset" value ="취소">Cancel</button>
     	<button class = "btn btn-default" class = "submitbtn" type = "submit" value ="로그인">로그인</button>
     	
    </fieldset>

    </form>
    </div>
</body>
</html>