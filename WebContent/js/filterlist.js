$(function() {
			$(".concert_day").each(function(index, item){
				// d-day 계산
				var concert_day = $(this).text();
				console.log("콘서트 날짜 = " + concert_day);
				var arr = concert_day.split("-");
				
				var today = new Date();	// 오늘 날짜
				var date = new Date(arr[0], arr[1]-1, arr[2]);	// 콘서트 날짜
				
				var diff = date - today;	// 콘서트 날짜 - 오늘 날짜 = d-day 계산
				var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨

				var dd = parseInt(diff/currDay);	// 일수 차이
				
				$(this).prev().html("(D-" + dd + ")");
			});
			
			$(".deadline").each(function(index, item){
				var concert_day = $(this).next().next().next().children().children().find(".concert_day").text();
				var arr = concert_day.split("-");
				
				var today = new Date();	// 오늘 날짜
				var date = new Date(arr[0], arr[1]-1, arr[2]);	// 콘서트 날짜
				console.log("오늘 날짜 = " + today);
				console.log("콘서트 날짜 = " + date);
				
				var diff = date - today;	// 콘서트 날짜 - 오늘 날짜 = d-day 계산
				var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨

				var dd = parseInt(diff/currDay);	// 일수 차이
				
				if(dd <= 5){
					$(this).html("마감임박");
				}else{
					$(this).css('display', 'none');
				}
			});
			
			// 필터 삭제시
			$(".filter_btn").click(function() {
				$(this).parent().css('display', 'none');

				// date
				var date = "";
					if ($("div.button.special.date").css('display') != 'none') {
							date += $("div.button.special.date").text();
					}else{
						date = "";
					}
				
				// local
				var local = "";
					$("div.button.special.local").each(function() {
						a = $(this).text().trim();
						if ($(this).css('display') != 'none') {
							local += a + ",";
						}
					});
				if(local == null || local == "")
					local += "전국,";
				console.log(local);

				// genre
				var genre = "";
				$("div.button.special.genre").each(function() {
						a = $(this).text().trim();
						if ($(this).css('display') != 'none') {
							genre += a + ",";
						}
					});
				if(genre == null || genre == "")
					genre += "모든 장르,";
				console.log(genre);

				$.ajax({
					type : "post",
					url : "filterdelete.co",
					data : {
						"search_date" : date,
						"search_local" : local,
						"search_genre" : genre
					},
					dataType : "json",
					success : function(data) {
						console.log(data.flistsize);
						if(data.flistsize > 0){
						$("#count").html("총 <strong>" + data.flistsize + "</strong>개");
						
						$(".box").remove();
						output = "";
						
						$(data.flist).each(function(index, item){
							<!-- Date 형태 yyyy-mm-dd -->
							var concert_day = item.concert_day;
							console.log("concert_day 처음 형태 : " + concert_day);
							
							var month_index = concert_day.indexOf("월");
							var month = concert_day.substring(0, month_index);
							
							var date_index = concert_day.indexOf(",");
							var date = concert_day.substring(month_index+2, date_index);
							
							var year = concert_day.substring(date_index+1, concert_day.length);
							
							month = month.length == 1 ? "0" + month : month;
							date = date.length == 1? "0" + date : date;
							
							var d = year + "-" + month + "-" + date;
							console.log("d = " + d);
							
							<!-- D-Day 구하기 -->
							var today = new Date();	// 오늘 날짜
							
							var arr = d.split("-");
							var date = new Date(arr[0], arr[1]-1, arr[2]);	// 콘서트
																			// 날짜
							
							var diff = date - today;	// 콘서트 날짜-오늘 날짜 = d-day
														// 계산
							var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 *
																// 밀리세컨

							var dd = parseInt(diff/currDay);	// 일수 차이
							
							output += "<div class='box'>";
							output += "<div class='table-wrapper'>";
							output += "<input type='hidden' name='concert_id' value='" + item.concert_id + "'>";
							output += "<img src='images/" + item.concert_image + "' alt='' />";
							
							if(dd <= 5){
								output += "<div class='deadline'>마감임박</div>";
							}else{
								output += "<div class='deadline' style = 'display : none'></div>";
							}
							
							output += "<h4>" + item.concert_name + "</h4>";
							output += "<p>" + item.concert_musician + "</p>";
							output += "<table>";
							output += "<tr><th>공연 일시</th>";
							output += "<td class='d_day'>(D-" + dd + ")</td>";
							output += "<td class='concert_day'>" + d + "</td>";
							output += "<td class='concert_time'>";
							output += item.concert_open + "-" + item.concert_close;
							output += "</td></tr>";
							output += "<tr><th>공연 장소</th>";
							output += "<td colspan=3>" + item.local_name + "</td></tr>";
							output += "<tr><th>공연 가격</th>";
							output += "<td colspan=3>" + item.concert_price + "</td></tr>";
							output += "<tr><th>장르</th>";
							output += "<td colspan=3>" + item.genre_name + "</td></tr></table>"
							output += "</div></div>";
						}); // each end
					 	$(".out").append(output);
						}else{
							$("#count").remove();
							$(".box").remove();
							$("body").append("<div style='margin: 100px; text-align: center; font-size: 50px'>검색 결과가 존재하지 않습니다.</div>");
						}
					} // success end
				}); // ajax end
			}); // $(".filter_btn").click end
		});