package mvc.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.db.MemberDAO;

public class Member_deleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		MemberDAO mdao = new MemberDAO();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		int result = mdao.delete(id);
		String message = id + "님 회원 정보가 삭제 되었습니다.";
		if(result !=1) {
			message = "삭제가 되지 않았습니다.";
		}
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='adminPage.net'");
		out.println("</script>");
		out.close();
		return null;
	}
}
