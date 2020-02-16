

$(document).ready(function () {
	var count = 0;
	
    $('.chat_date').on('click', function () {
    	
    	var no = $(this).next().children().val();
    	var test = $(this).next().children().next();
    	test.empty();
    	
    	var id = $('#id').val();
    	var senddata = {"chat_log_id2" : no , "id" : id};
        $.ajax({
            url : "chatlist.ch",
            type : "post",
            dataType : "json",
            data : senddata,
            success : function (data) {
            	console.log('성공');
            	var output = "";
				$(data).each(function(index, item){
					if(item.member_id == 'admin@mfe.com'){
						output += "<div class='admin_chat'>관리자 : "+item.chat_log_content + "<br>"+item.chat_log_date+"</div><br>"
					} else {
						output += "<div class='user_chat'>나 : "+item.chat_log_content+ "<br>"+item.chat_log_date+"</div><br>"
					}
				})
				if(count==0){
					test.append(output);
					count = 1;
				} else {
					test.empty();
					count = 0;
				}
				
            },
            error : function (data) {
            	console.log('에러');
            }
        });
    })
});