<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>자유 게시판</title>

<jsp:include page="../mainActivity/navbar.jsp" />
<script src="js/delete.js" charset="UTF-8"></script>
<script>
  $(function(){
    
     $("#can").click(function(){
        history.back();
     });
  })
</script>
</head>
<style>
 #myModal {
    display: block 
} 
</style>


<body>
   <!-- The Modal -->
   <div class="modal" id="myModal">
      <div class="modal-dialog">
         <div class="modal-content">


            <!-- Modal body -->
            <div class="modal-body">
               <form name="deleteForm" action="BoardDeleteA.bo"
                  method="post">
                  <input type="hidden" name="num" value="${param.num}">

                  <div class="form-group">
                     <label for="pwd">비밀번호</label> 
                        <input type="password"
                        class="form-control" placeholder="Enter password"
                        name="BOARD_PASS" id="board_pass">
                  </div>
                  <button type="submit" class="btn btn-primary" >확인</button>
                   <button id = "can" type="button" class="btn btn-danger">취소</button>
               </form>
            </div>
         </div>
      </div>
   </div>
</body>
</html>