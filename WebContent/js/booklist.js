function go(page){
   var limit = $('#viewcount').val();
   var data = "limit=" + limit + "&state=ajax&page=" + page;
   ajax(data);
}

function setPaging(href, digit){
   output += "<li class=page-item>";
   gray="";
   if(href==""){
      gray = "gray";
   }
   anchor = "<a class='page-link " + gray + "'" + href + ">" + digit + "</a></li>";
   output += anchor;
}

function ajax(data) {
console.log(data)
output="";
$.ajax({
            type:"POST",
            data: data,
            url : "mypage.bk",
            dataType:"json",
            cache:false,
            success:function(data){
                    $("#viewcount").val(data.limit);
                    $("table").find("font").text("예매목록 개수 : " + data.listcount);

if(data.listcount>0) { //총갯수가 1개 이상인 경우
$("tbody").remove();
var num = data.listcount - (data.page -1) * data.limit;
console.log(num)
output = "<tbody>";
$(data.booklist).each(   
function(index, item) {
output += '<tr><td>' + (num--) + '</td>'
output += '<td><div>' + item.book_date + '</div></td>'
output += '<td><div>' + '<a href="./ConcertDetailAction.co?num=' + item.book_id + '&page=' + data.page + '">' + item.concert_name + '</a></div></td>'
output += '<td><div>' + item.concert_day  + '</div></td>'
output += '<td><div>' + item.book_amount + '</div></td>'
output += '<td><div>' + item.book_eticket  + '</div></td>'
output += '<td><div>' + '<a href="BookDeleteAction.bk?book_id='+item.book_id+'"'+ 'class="book-cancel">예매취소</a>'  + '</div></td></tr>'
})
output += '</tbody>'
   
   $('table').append(output) //table 완성
   
   $(".pagination").empty();  //페이징 처리
    output= "";
    
    digit = '이전&nbsp;'
    href="";
    if(data.page >1) {
       href = 'href=javascript:go(' + (data.page - 1) + ')';
    }
    setPaging(href, digit);

for (var i = data.startpage; i<= data.endpage; i++) {
   digit = i;
   href= "";
   if(i != data.page){
      href='href=javascript:go('+i+')';
   }
   setPaging(href, digit);
}
digit = '다음&nbsp;';
href="";
if(data.page < data.maxpage){
   href='href=javascript:go('+(data.page+1)+')';
}
	setPaging(href, digit);

$('.pagination').append(output)
            } //if(data.listcount) end
else {
   $(".container table").remove();
   $(".center-block").remove();
   $(".container").append("<font size=5>예매내역이 없습니다.</font>");
}
}, //success end
error : function() {
   console.log('에러')
}
}); //ajax end
} //function ajax end
$(function(){
   $("#viewcount").change(function(){
      go(1); //보여줄 페이지를 1페이지로 설정한다.
   }) //change end
  
});