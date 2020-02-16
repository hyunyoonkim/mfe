/* 게시판 관리 go*/
function go(page2){
   var board_search_field = $('#board_search_field').val();
   var board_search_word = $('#board_search_word').val();
   var limit2 = $('#viewcount2').val();
   var data2 = "limit2=" + limit2 + "&state=ajax&page2=" + page2+"&board_search_field="+board_search_field+"&board_search_word="+board_search_word;
   ajax(data2);
}

/* 회원 관리 go*/
function go2(page){
	   var search_field = $('#viewcount').val();
	   var search_word = $('#search_word').val();
	   var limit = $('#viewcount3').val();
	   var data = "limit=" + limit + "&state2=ajax&page=" + page+"&search_field="+search_field+"&search_word="+search_word;
	   ajax2(data);
}

function concert_go(concert_page){
	   var concert_search_field = $('#concert_search_field').val();
	   var concert_search_word = $('#concert_search_word').val();
	   var concert_limit = $('#concert_viewcount').val();
	   var concert_data = "concert_limit=" + concert_limit + "&concert_state=ajax&concert_page=" + concert_page+"&concert_search_field="+concert_search_field+"&concert_search_word="+concert_search_word;
	   concert_ajax(concert_data);
}

/* setPaging 공동 기능*/
function setPaging(href, digit){
   output += "<li class=page-item>";
   gray="";
   if(href==""){
      gray = "gray";
   }
   anchor = "<a class='page-link " + gray + "'" + href + ">" + digit + "</a></li>";
   output += anchor;
}
/* 멤버 상세조회 ajax */
function getView(member_id) {
	
	$.ajax({
		type: "post",
		url : 'member_info.net',
		data : {"MEMBER_ID" : member_id},
		dataType:"json",
		success:function(rdata) {
				$("#updateMember_id").val(rdata.id);
				$("#updateMember_pass").val(rdata.password);
				$("#updateMember_name").val(rdata.name);
				$("#updateMember_address").val(rdata.address);
				$("#updateMember_phone_number").val(rdata.phone_number);
				
				var preference = rdata.preference;
				var preferTEMP = preference.split(',');
				preferTEMP.forEach(function(item) {
					var temp = "#updateMember_preference_"+item;
					$(temp).attr('checked', 'checked');
				});
				var gender = rdata.gender;
				if(gender == 1) {
					$('#Umale').attr('checked','checked');
				} else {
					$('#Ufemale').attr('checked','checked');
				}
		}, 
		error : function() {
        	console.log('에러')
        }
	});
};

/* 멤버 정보 수정 ajax*/
function updateMember() {
	var allData = $('#updateMember_form').serialize();
	$.ajax({
		type:"post",
		url : 'updateProcess.net',
		data : allData,
		success:function() {
			$('#member_view_Modal').modal("hide");
			go2(1);
		}, error : function() {
			console.log('에러')
        }
	})
}

/* 게시판 목록 필터링 ajax */
function ajax(data) {
	console.log(data)
	output="";
	$.ajax({
            type:"POST",
            data: data,
            url : "adminPage.net",
            dataType:"json",
            cache:false,
            success:function(data){
            		$('#board_search_field').val(data.board_search_field).prop("selected", true);
            		$('#board_search_word').val(data.board_search_word);
                    $("#viewcount2").val(data.limit2);
                    $(".t2").find("font").text(data.listcount2+"개");
                    
                    if(data.listcount2>0) { //총갯수가 1개 이상인 경우
                    	$('.tb2').remove();
                    	var num = data.listcount2 - (data.page2 -1) * data.limit2;
                    	console.log(num)
                    	output = "<tbody class = 'tb2'>";
                    	$(data.boardlist).each(   //<c:forEach var ="b" items="${boardlist}">
                    			function(index, item) {
                    				output += '<tr><td>' + (num--) + '</td>'
                    				blank_count = item.BOARD_RE_LEV * 2 + 1;
                    				blank = '&nbsp;';
                    				for(var i =0; i<blank_count; i++) {
                    					blank += '&nbsp;&nbsp;'	
                    				}
                    				img="";
                    				if(item.BOARD_RE_LEV > 0) {
                    					img="<img src='images/AnswerLine.gif'>";
                    				}

                    				output += "<td><div>" + blank + img
                    				output += '<a href="./BoardDetailAction.bo?num=' + item.BOARD_NUM + '&page=' + data.page2 + '">'

                    				output += item.BOARD_SUBJECT + '</a></div></td>'
                    				output += '<td><div>' + item.BOARD_NAME + '</div></td>'
                    				output += '<td><div><a href="./AdminBoardDelete.bo?num='+ item.BOARD_NUM + '"style="color:red" >삭제</a></div></td></tr>'
                    				
                    			});
                    			output += '</tbody>'
                    			$('.t2').append(output) //table 완성
                    			$(".pa2").empty();  //페이징 처리
                    			output= "";
                    			digit = '이전&nbsp;'
                    			href="";
                    			if(data.page2 >1) {
                    				href = 'href=javascript:go(' + (data.page2 - 1) + ')';
                    			}
                    			setPaging(href, digit);

                    			for (var i = data.startpage2; i<= data.endpage2; i++) {
                    				digit = i;
                    				href= "";
                    				if(i != data.page2){
                    					href='href=javascript:go('+i+')';
                    				}
                    				setPaging(href, digit);
                    			}
                    			digit = '다음&nbsp;';
                    			href="";
                    			if(data.page2 < data.maxpage2){
                    				href='href=javascript:go('+(data.page2+1)+')';
                    			}
                    			setPaging(href, digit);

                    			$('.pa2').append(output)
                    } //if(data.listcount) end
                    else {
                    	$(".t2").remove();
                    	$(".block2").remove();
                    	$(".container2").append("<font size=5>등록된 글이 없습니다.</font>");
                    }
            }, //success end
            error : function() {
            	console.log('에러')
            }
	}); //ajax end
} //function ajax end

/* 회원 목록 필터링 ajax */
function ajax2(data) {
	console.log(data)
	output="";
	$.ajax({
            type:"POST",
            data: data,
            url : "adminPage.net",
            dataType:"json",
            cache:false,
            success:function(data){
                    $("#viewcount3").val(data.limit);
                    $('#viewcount').val(data.search_field).prop("selected", true);
                    $('#search_word').val(data.search_word);
                    $(".t1").find("font").text(data.listcount+"명");
                    
                    if(data.listcount>0) { //총갯수가 1개 이상인 경우
                    	$('.tb1').remove();
                    	var num = data.listcount - (data.page-1) * data.limit;
                    	console.log(num)
                    	output = "<tbody class = 'tb1'>";
                    	$(data.memberlist).each(   
                    			function(index, item) {
                    				output += '<tr><td>' + (num--) + '</td>';
                    				output += '<td><div><a><button type="button" class="btn memberDetail"'
                    					  + ' data-toggle="modal" data-target="#member_view_Modal">' + item.id +'</button></a></div></td>';
                    				output += "<td><div>" + item.name +"</td></div>";
                    				if(item.id != 'admin@mfe.com') {
                    					output += '<td><a href="member_delete.net?id=' + item.id +'" style="color:red" >삭제</a></td>';
                    				}
                    			}
                    	);
                    	output += '</tbody>'
                    	$('.t1').append(output) //table 완성
                    	$(".pa1").empty();  //페이징 처리
                    	output= "";
                    	digit = '이전&nbsp;'
                    	href="";
                    	if(data.page >1) {
                    		href = 'href=javascript:go2(' + (data.page - 1) + ')';
                    	}
                    	setPaging(href, digit);

                    	for (var i = data.startpage; i<= data.endpage; i++) {
                    				digit = i;
                    				href= "";
                    				if(i != data.page){
                    					href='href=javascript:go2('+i+')';
                    				}
                    				setPaging(href, digit);
                    			}
                    			digit = '다음&nbsp;';
                    			href="";
                    			if(data.page < data.maxpage){
                    				href='href=javascript:go2('+(data.page+1)+')';
                    			}
                    			setPaging(href, digit);

                    			$('.pa1').append(output)
                    } //if(data.listcount) end
                    else {
                    	$(".t1").remove();
                    	$(".block1").remove();
                    	$(".container1").append("<font size=5>등록된 글이 없습니다.</font>");
                    }
            }, //success end
            error : function() {
            	console.log('에러')
            }
	}); //ajax end
} //function ajax end

/* 콘서트 목록 필터링 ajax */
function concert_ajax(concert_data) {
	console.log(concert_data)
	output="";
	$.ajax({
            type:"POST",
            data: concert_data,
            url : "adminPage.net",
            dataType:"json",
            cache:false,
            success:function(data){
            		//viewcount가 검색, viewcount3가 필터링
                    $("#concert_viewcount").val(data.concert_limit);
                    
                    $('#concert_search_field').val(data.concert_search_field).prop("selected", true);
                    $('#concert_search_word').val(data.concert_search_word);
                    $(".t3").find("font").text(data.concert_count+"명");
                    
                    if(data.concert_count>0) { //총갯수가 1개 이상인 경우
                    	$('.tb3').remove();
                    	var num = data.concert_count - (data.concert_page-1) * data.concert_limit;
                    	console.log(num)
                    	output = "<tbody class = 'tb3'>";
                    	$(data.concert_list).each(   
                    			function(index, item) {
                    				output += '<tr><td>' + (num--) + '</td>';
                    				output += '<td><div><a><button type="button" class="btn concertDetail"'
                    					  + ' data-toggle="modal" data-target="#concert_view_Modal">' + item.concert_id +'</button></a></div></td>';
                    				output += "<td><div>" + item.concert_name +"</td></div>";
                    				
                    				output += '<td><a href="concert_delete.co?id=' + item.concert_id +'" style="color:red" >삭제</a></td>';
                    				
                    			}
                    	);
                    	output += '</tbody>'
                    	$('.t3').append(output) //table 완성
                    	$(".pa3").empty();  //페이징 처리
                    	output= "";
                    	digit = '이전&nbsp;'
                    	href="";
                    	if(data.concert_page >1) {
                    		href = 'href=javascript:concert_go(' + (data.concert_page - 1) + ')';
                    	}
                    	setPaging(href, digit);

                    	for (var i = data.concert_startpage; i<= data.concert_endpage; i++) {
                    				digit = i;
                    				href= "";
                    				if(i != data.concert_page){
                    					href='href=javascript:concert_go('+i+')';
                    				}
                    				setPaging(href, digit);
                    			}
                    			digit = '다음&nbsp;';
                    			href="";
                    			if(data.concert_page < data.concert_maxpage){
                    				href='href=javascript:concert_go('+(data.concert_page+1)+')';
                    			}
                    			setPaging(href, digit);

                    			$('.pa3').append(output)
                    } //if(data.listcount) end
                    else {
                    	$(".t3").remove();
                    	$(".block3").remove();
                    	$(".container3").append("<font size=5>등록된 글이 없습니다.</font>");
                    }
            }, //success end
            error : function() {
            	console.log('에러')
            }
	}); //ajax end
} //function ajax end



/* 파라미터 값 가져오는 것 */
$.urlParam = function(name){
    var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null){
       return null;
    }
    else{
       return results[1] || 0;
    }
}



$(function(){
	/* 페이지에 따라 탭 강제 선택 */
	var tab = ($.urlParam('page2'));
	var board_search = ($.urlParam('board_search_field'));
	if(tab != null || board_search != null) {
		$('#boardTab').trigger('click');
	}
	var concert_tab = ($.urlParam('concert_page'));
	var concert_search = ($.urlParam('concert_search_field'));
	if(concert_tab != null || concert_search != null) {
		$('#concertTab').trigger('click');
	}
	
	
	/* 게시판 */
   $("#viewcount2").change(function(){
      go(1); //보여줄 페이지를 1페이지로 설정한다.
   }) //change end
   /* 회원 */
   $("#viewcount3").change(function(){
      go2(1); 
   }) //change end
   $("#concert_viewcount").change(function(){
      concert_go(1); 
   }) //change end
   
   
   
   $('#addBoard_Button').click(function() {
		location.href ="BoardWrite.bo";
	});
   $(".del").click(function(event) {
		var answer = confirm("정말 삭제하시겠습니까?");
		if(!answer) {
			event.preventDefault();
		}
	});
	
	
	$('#search_btn').click(function() {
		if($("#search_word").val() == '') {
			alert("검색어를 입력하세요");
			return false;
		}
	});
	$('#board_search_btn').click(function() {
		if($("#board_search_word").val() == '') {
			alert("검색어를 입력하세요");
			return false;
		}
	});
	$('#concert_search_btn').click(function() {
		if($("#concert_search_word").val() == '') {
			alert("검색어를 입력하세요");
			return false;
		}
	});
	$("#viewcount").change(function() {
		selectedValue = $(this).val();
		$("#search_word").val('');
		if(selectedValue=='3') {
			$("#search_word").attr("placeholder", "여 또는 남을 입력하세요");
		}
	});
	$(".memberDetail").click(function() {
		var member_id = $(this).text();
		getView(member_id);
	});
	$("#updateMember_button").click(function() {
		updateMember();
	})
	
	$("#textMessage").keydown(function(key) {
		if (key.keyCode == 13) {
			sendMessage();
			return false;

		}
	});
	
});