package mvc.concert.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.concert.db.ConcertDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class ConcertDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		ConcertDAO cdao = new ConcertDAO();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		int result = cdao.delete(id);
		String message = id + "님 콘서트 정보가 삭제 되었습니다.";
		if(result !=1) {
			message = "콘서트 삭제가 되지 않았습니다.";
		}
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='adminPage.net?concert_page=1'");
		out.println("</script>");
		out.close();
		return null;
	}
}
