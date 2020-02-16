package mvc.comment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;

import mvc.comment.db.CommentDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class commentList implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		CommentDAO dao = new CommentDAO();
		int BOARD_RE_REF = Integer.parseInt(request.getParameter("BOARD_RE_REF"));
		System.out.println(BOARD_RE_REF);
		
		JsonArray json = dao.getCommentList(BOARD_RE_REF);
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader("cache-control", "no-cache,no-store");
		PrintWriter out = response.getWriter();
		out.print(json);
		System.out.println(json);
		
		return null;
	}
}
