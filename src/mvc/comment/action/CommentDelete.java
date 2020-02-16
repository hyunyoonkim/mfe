package mvc.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.comment.db.CommentDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class CommentDelete implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO cdao = new CommentDAO();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int num = Integer.parseInt(request.getParameter("num"));
		
		try {
			int ok = cdao.commentsDelete(num);
			response.getWriter().print(ok);
		} catch(Exception e) {
			System.out.println("댓글 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "댓글 수정 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		return null;
	}
}
