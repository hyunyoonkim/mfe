package mvc.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.comment.db.CommentDAO;
import mvc.comment.db.CommentVO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class CommentUpdate implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO dao = new CommentDAO();
		CommentVO co = new CommentVO();
		
		request.setCharacterEncoding("UTF-8");
		co.setContent(request.getParameter("content"));;
		System.out.println("content=" + co.getContent());
		co.setNum(Integer.parseInt(request.getParameter("num")));
		try {
			int ok = dao.commentsUpdate(co);
			response.getWriter().print(ok);
		} catch(Exception e) {
			System.out.println("댓글 수정 실패");
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "댓글 수정 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		return null;
	}
}
