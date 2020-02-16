function go(page) {
	var limit = $('#viewcount3').val();
	var search_select = $("#search_select").val();
	var search_text = $("#search_text").val();
	var data = "limit=" + limit + "&state=ajax&page=" + page;
	ajax(data);
}

function setPaging(href, digit) {
	output += "<li class=page-item>";
	gray = "";
	if (href == "") {
		gray = "gray";
	}
	anchor = "<a class='page-link " + gray + "'" + href + ">" + digit
			+ "</a></li>";
	output += anchor;
}

function ajax(data) {
	console.log(data)
	output = "";
	$.ajax({
		type : "POST",
		data : data,
		url : "ReviewList.rv",
		dataType : "json",
		cache : false,
		success : function(data) {
			$("#viewcount3").val(data.limit);
			$("table").find("font").text("글 개수 : " + data.listcount);

			if (data.listcount > 0) {
				$("tbody").remove();
				var num = data.listcount - (data.page - 1) * data.limit;
				output = "<tbody>";
				$(data.reviewlist).each(
						function(index, item) {
							output += '<tr><td>' + (num--) + '</td>'
							output += '<td><div>' + '<img src="concertupload/'
									+ item.concert_image + '"/></div></td>'
							output += '<td><div>'
									+ '<a href="ReviewDetailAction.rv?num='
									+ item.review_id + '&page=' + data.page
									+ '">' + item.review_title
									+ '</a></div></td>'
							output += '<td><div>' + item.member_id
									+ '</div></td>'
							output += '<td><div>' + item.review_date
									+ '</div></td>'
							output += '<td><div>' + item.review_readcount
									+ '</div></td>'
						})
				output += '</tbody>'

				$('table').append(output) // table 완성

				$(".pagination").empty(); // 페이징 처리
				output = "";

				digit = '이전&nbsp;'
				href = "";
				if (data.page > 1) {
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				setPaging(href, digit);

				for (var i = data.startpage; i <= data.endpage; i++) {
					digit = i;
					href = "";
					if (i != data.page) {
						href = 'href=javascript:go(' + i + ')';
					}
					setPaging(href, digit);
				}
				digit = '다음&nbsp;';
				href = "";
				if (data.page < data.maxpage) {
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				setPaging(href, digit);

				$('.pagination').append(output)
			} // if(data.listcount) end
			else {
				$(".container table").remove();
				$(".center-block").remove();
				$(".container").append("<font size=5>등록된 리뷰가 없습니다.</font>");
			}
		}, // success end
		error : function() {
			console.log('에러')
		}
	}); // ajax end
} // function ajax end

$(function() {
	$("#viewcount3").change(function() {
		go(1); // 보여줄 페이지를 1페이지로 설정한다.
	}) // change end
	$('#addReview_Button').click(function() {
		location.href = "ReviewWrite.rv";
	});
});