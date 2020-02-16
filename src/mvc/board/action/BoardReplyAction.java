package mvc.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.db.BoardBean;
import mvc.board.db.BoardDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class BoardReplyAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      request.setCharacterEncoding("utf-8");
      ActionForward forward = new ActionForward();
      
      BoardDAO bdao = new BoardDAO();
      BoardBean bdata = new BoardBean();
      
      int result = 0;
      
      //파라미터로 넘어온 값들을 bdata 객체에 저장합니다.
      bdata.setBOARD_NAME(request.getParameter("BOARD_NAME"));
      bdata.setBOARD_PASS(request.getParameter("BOARD_PASS"));
      bdata.setBOARD_SUBJECT(request.getParameter("BOARD_SUBJECT"));
      bdata.setBOARD_CONTENT(request.getParameter("BOARD_CONTENT"));
      bdata.setBOARD_RE_REF(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
      bdata.setBOARD_RE_LEV(Integer.parseInt(request.getParameter("BOARD_RE_LEV")));
      bdata.setBOARD_RE_SEQ(Integer.parseInt(request.getParameter("BOARD_RE_SEQ")));
      
      //답변을 DB에 저장하기 위해 bdata 객체를 파라미터로 DAO의 메서드 boardReply를 호출 합니다.
      result=bdao.boardReply(bdata);
      
      //답변 저장에 실패한 경우
      if(result==0) {
         System.out.println("답변 실패");
         forward.setRedirect(false);
         request.setAttribute("message", "답변 작성 실패입니다.");
         forward.setPath("error/error.jsp");
         return forward;
      }
      
      //답변 저장이 제대로 된 경우
      System.out.println("답장 완료");
      forward.setRedirect(true);
      //답변 글 내용을 확인하기 위해 글 내용 보기 페이지를 겨올로 설정합니다.
      forward.setPath("BoardDetailAction.bo?num="+result);
       return forward;
   }

}