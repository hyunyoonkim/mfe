package mvc.comment.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.comment.db.CommentDAO;
import mvc.comment.db.CommentVO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class CommentAdd implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO dao = new CommentDAO();
		CommentVO co = new CommentVO();
		//ajax 이용시 한글 깨짐방지
		request.setCharacterEncoding("UTF-8");
		co.setId(request.getParameter("id"));
		co.setContent(request.getParameter("content"));
		System.out.println("content = " + co.getContent());
		co.setBoard_re_ref(Integer.parseInt(request.getParameter("BOARD_RE_REF")));
		try {
			int ok = dao.commentsInsert(co);
			response.getWriter().print(ok);
		} catch(Exception e) {
			System.out.println("댓글 등록 실패");
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "댓글 등록 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
		
		return null;
	}
}
