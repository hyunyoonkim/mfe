package mvc.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.db.MemberDAO;

public class My_deleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MemberDAO mdao = new MemberDAO();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		int result = mdao.delete(id);
		String message = id + "님 회원탈퇴가 완료되었습니다.";
		System.out.println(id + "탈퇴완료");
		if(result !=1) {
			message = "탈퇴 실패!";
		}
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='logOut.net';");
		out.println("</script>");
		out.close();
		return null;
	}

}