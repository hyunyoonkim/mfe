package mvc.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.board.db.BoardDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class AdminBoardDeleteAction implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   request.setCharacterEncoding("UTF-8");
		   boolean result = false;
		   
		   int num = Integer.parseInt(request.getParameter("num"));
		   
		   BoardDAO boarddao = new BoardDAO();
		  
		  
		   result = boarddao.boardDelete(num);
		   
		   ActionForward forward = new ActionForward();
		   //삭제 처리 실패한 경우
		   if(result == false) {
			   System.out.println("게시판 삭제 실패");
			   forward.setRedirect(false);
			   request.setAttribute("message", "게시판 삭제 실패입니다.");
			   forward.setPath("error/error.jsp");
			   return forward;
		   }
		   //삭제 처리 성공한 경우  - 글 목록 보기 요청을 전송하는 부분입니다.
		   System.out.println("게시판 삭제 성공");
		   response.setContentType("text/html;charset=UTF-8");
		   PrintWriter out= response.getWriter();
		   out.println("<script>");
		   out.println("alert('삭제 되었습니다.');");
		   out.println("location.href='adminPage.net?page2=1';");
		   out.println("</script>");
		   out.close();
		   return null;
	   }
}
