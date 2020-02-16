
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
	if(tab != null) {
		$('#chatTab').trigger('click');
	}
	/* 게시판 */
   $("#viewcount").change(function(){
      go(1); //보여줄 페이지를 1페이지로 설정한다.
   }) //change end

   $(".del").click(function(event) {
		var answer = confirm("정말 삭제하시겠습니까?");
		if(!answer) {
			event.preventDefault();
		}
	});


}); 