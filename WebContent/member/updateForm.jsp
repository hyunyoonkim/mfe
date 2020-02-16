<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script>
$(function() {
	var preferences = "${memberinfo.preference}";
	var preTEMP = preferences.split(',');
	preTEMP.forEach(function(item) {
		var temp = "#updateMember_preference_"+item;
		$(temp).attr('checked', 'checked');
	})
	var gender = ${memberinfo.gender}
	if(gender == 1) {
		$('#male').attr('checked', 'checked');
	} else {
		$('#female').attr('checked', 'checked');
	}
	$(".cancelbtn").click(function() {
		history.back();
	})
});
</script>

<div class="container">
   <form action="updateProcess.net" name = "joinform" method="post">
	 <fieldset>
		<div class = "form-group">
			<label for ="updateMember_id">아이디</label>
			<input type="text" class="form-control" id="updateMember_id"
				   placeholder = "Enter id" name="updateMember_id" readOnly value = "${memberinfo.id}">
		</div>
		<div class = "form-group">
			<label for ="updateMember_pass">비밀번호</label>
			<input type="password" class="form-control" id="updateMember_pass"
				   placeholder = "Enter password" name="updateMember_pass" required value="${memberinfo.password}" maxLength="12">
		</div>
		<div class = "form-group">
			<label for = "updateMember_name" >이름</label>
			<input type="text" id = "updateMember_name" class="form-control" name = "updateMember_name" value="${memberinfo.name}" placeholder="Enter name" required maxLength="15">
		</div>
		<div class = "form-group">
			<label for = "updateMember_address">주소</label>
			<input type="text" id = "updateMember_address"  class="form-control" name = "updateMember_address" value="${memberinfo.address}" placeholder="Enter address" required>
		</div>
		<div class = "form-group">
			<label for = "updateMember_phone_number">전화번호</label>
			<input type="text" id = "updateMember_phone_number" class="form-control" name = "updateMember_phone_number" value="${memberinfo.phone_number}" placeholder="Enter birthday" required>
		</div>
		<div class = "form-group">
			<label >선호장르</label>
			<input type="checkbox" id="updateMember_preference_balad" name="updateMember_preference" value = "balad">
			<label for="updateMember_preference_balad">발라드</label>
			<input type="checkbox" id="updateMember_preference_rock" name="updateMember_preference" value = "rock">
			<label for="updateMember_preference_rock">락/메탈</label>
			<input type="checkbox" id="updateMember_preference_rap" name="updateMember_preference" value ="rap">
			<label for="updateMember_preference_rap">랩/힙합</label>
			<input type="checkbox" id="updateMember_preference_jazz" name="updateMember_preference" value = "jazz">
			<label for="updateMember_preference_jazz">재즈/소울</label>
			<input type="checkbox" id="updateMember_preference_classic" name="updateMember_preference" value = "classic">
			<label for="updateMember_preference_classic">클래식</label>
			<input type="checkbox" id="updateMember_preference_pop" name="updateMember_preference" value="pop">
			<label for="updateMember_preference_pop">팝</label>
			<input type="checkbox" id="updateMember_preference_edm" name="updateMember_preference" value="edm">
			<label for="updateMember_preference_edm">EDM</label>
		</div>
		<div class="u$(small)">
			<div><label for="male">성별</label></div>
			<input type="radio" id = "male" name="updateMember_gender" value="1">
			<label for="male" >남</label>
			<input type="radio" id = "female" name="updateMember_gender" value="2">
			<label for="female">여</label>
		</div>
		
		<button type="submit">수정</button>
		<button class = "cancelbtn" type="button">돌아가기</button>
	  </fieldset>
	 </form>
	</div>