function validateEncryptedForm() {
    var login_id = $("#login_id").val();
    var login_pass = $("#login_pass").val();
    if (!login_id || !login_pass) {
        alert("ID/비밀번호를 입력해주세요.");
        return false;
    }
    try {
        var rsaPublicKeyModulus = $("#rsaPublicKeyModulus").val();
        var rsaPublicKeyExponent = $("#rsaPublicKeyExponent").val();
        submitEncryptedForm(login_id,login_pass, rsaPublicKeyModulus, rsaPublicKeyExponent);
    } catch(err) {
        alert(err);
    }
    return false;
}

function submitEncryptedForm(login_id, login_pass, rsaPublicKeyModulus, rsaPpublicKeyExponent) {
    var rsa = new RSAKey();
    rsa.setPublic(rsaPublicKeyModulus, rsaPpublicKeyExponent);

    // 사용자ID와 비밀번호를 RSA로 암호화한다.
    var securedlogin_id = rsa.encrypt(login_id);
    var securedlogin_pass = rsa.encrypt(login_pass);

    // POST 로그인 폼에 값을 설정하고 발행(submit) 한다.
    $("#secured_id").val(securedlogin_id);
    $("#secured_pass").val(securedlogin_pass);
    $("#securedLoginForm").submit();
}
$(function() {
		$("#alogin").click(function() {
			validateEncryptedForm();
			return false;
		})
		$('#adminMode_Button').click(function() {
			location.href = 'adminPage.net';
		});
		var idCheck = false;
		/*
		$('#login_button').click(function() {
			$('#login_form').submit();
		})
		 */
		// ID keyup 이벤트(중복 확인)
		$("#addMember_id").on(
				'keyup',
				function() {
					$("addMember_id_message").empty();
					// \w => [A-Za-z0-9_]
					var pattern = /^\w+@\w*[.]\w{3}$/;
					var id = $("#addMember_id").val();

					if (!pattern.test(id)) {
						$("#addMember_id_message").css('color', 'red').html(
								"이메일 형식으로 맞춰주세요.");
						idCheck = false;
						return;
					}
					$.ajax({
						url : "idcheck.net",
						data : {
							"id" : id
						},
						success : function(rdata) {
							if (rdata == -1) {
								$("#addMember_id_message")
										.css('color', 'green').html(
												"사용 가능한 아이디 입니다.");
								idCheck = true;
							} else {
								$("#addMember_id_message").css('color', 'blue')
										.html("사용중인 아이디 입니다.");
								idCheck = false;
							}
						}//if
					});//ajax
				});// ID keyup 이벤트 종료

		$('#login_form').submit(function() {
			location.href = 'loginProcess.net';
		});
		// 회원가입 유효성 검사
		$('#addMember_form').submit(function() {
			if ($('#addMember_id').val() == "") {
				alert("ID를 입력하세요");
				$('#addMember_id').focus();
				return false;
			}
			if (idCheck == false) {
				alert("ID 중복되었습니다.");
				return false;
			}

			if ($('#addMember_pass').val() == "") {
				alert("비밀번호를 입력하세요");
				$('#addMember_pass').focus();
				return false;
			}
			if ($('#addMember_name').val() == "") {
				alert("이름을 입력하세요");
				$('#addMember_name').focus();
				return false;
			}

			if ($('input[type=radio]:checked').length == 0) {
				alert("성별을 선택하세요");
				return false;
			}

		}); //회원가입 유효성 검사 종료

		// 검색 버튼 이벤트
		$('#search_Text').hide();

		$('#search_Button').click(function() {
			$('#search_Text').toggle();
			$('#search_Button').hide();
			$('#search_Text').focus();
		});
		$('#search_Text').blur(function() {
			$('#search_Button').show();
			$('#search_Text').val('');
			$('#search_Text').hide();
		});
		// 검색 이벤트 종료

		// 검색어 엔터시
		$("#search_Text").keydown(function(key) {
			if (key.keyCode == 13) { // 엔터치면
				$("#searchForm").submit();
				var search_Text = $("#search_Text").val();
				location.href = "searchword.co"
			}
		});

	// filter 클릭시 색상 변함
		$(".local").css('opacity', '0.5');
		$(".local").each(function(index, item) {
			$(this).click(function() {
				var status = $(this).css('opacity');
				if (status == '0.5') {
					$(this).css('opacity', '1');
					if ($("#all_local").css('opacity') == 1) {
						$("#all_local").css('opacity', '0.5');
					}
				} else {
					$(this).css('opacity', '0.5');
				}
			});
		});

		// filter 클릭시 색상 변함
		$(".genre").css('opacity', '0.5');
		$(".genre").each(function(index, item) {
			$(this).click(function() {
				var status = $(this).css('opacity');
				if (status == '0.5') {
					$(this).css('opacity', '1');
					if ($("#all_genre").css('opacity') == 1) {
						$("#all_genre").css('opacity', '0.5');
					}
				} else {
					$(this).css('opacity', '0.5');
				}
			});
		});
		// 전체 지역 클릭시 다른 지역은 해제되게 하기
		$("#all_local").css("opacity", '0.5');
		$("#all_local").click(function() {
			var status = $(this).css('opacity');
			if (status == '0.5') {
				$(this).css('opacity', '1');
				$(".local").each(function(index, item) {
					$(".local").css('opacity', '0.5');
				});
			} else {
				$(this).css('opacity', '0.5');
			}
		});

		// 모든 장르 클릭시 다른 장르는 해제되게 하기
		$("#all_genre").css("opacity", '0.5');
		$("#all_genre").click(function() {
			var status = $(this).css('opacity');
			if (status == '0.5') {
				$(this).css('opacity', '1');
				$(".genre").each(function(index, item) {
					$(".genre").css('opacity', '0.5');
				});
			} else {
				$(this).css('opacity', '0.5');
			}
		});

		// <input type = "hidden" name = "search_local" id = "search_local">
		// <input type = "hidden" name = "search_genre" id = "search_genre">								
		$("#filter_btn").click(function() {
			var search_local = "";
			var search_genre = "";

			$(".local").each(function(index, item) {
				if ($(this).css('opacity') == 1)
					search_local += $(this).val() + ",";
			});

			$(".genre").each(function(index, item) {
				if ($(this).css('opacity') == 1)
					search_genre += $(this).val() + ",";
			});

			if ($("#all_local").css('opacity') == 1)
				search_local = $("#all_local").val() + ",";

			if ($("#all_genre").css('opacity') == 1)
				search_genre = $("#all_genre").val() + ",";

			if (search_local == null || search_local == "") {
				search_local = "전국,";
			}
			if (search_genre == null || search_genre == "") {
				search_genre = "모든 장르,";
			}
			$("#search_local").val(search_local); // text에 넣음
			$("#search_genre").val(search_genre);
		});

	});