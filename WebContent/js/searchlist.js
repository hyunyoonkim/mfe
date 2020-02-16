$(function() {
				$(".concert_day").each(function(index, item){
					// d-day 계산
					var concert_day = $(this).text();
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
					console.log("날짜 : " + concert_day);
					var arr = concert_day.split("-");
					
					var today = new Date();	// 오늘 날짜
					var date = new Date(arr[0], arr[1]-1, arr[2]);	// 콘서트 날짜
					console.log("콘서트 날짜 = " + date);
					
					var diff = date - today;	// 콘서트 날짜-오늘 날짜 = d-day 계산
					var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨

					var dd = parseInt(diff/currDay);	// 일수 차이
					
					if(dd <= 5){
						$(this).html("마감임박");
					}else{
						$(this).css('display', 'none');
					}
				});
			});