package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.db.MemberDAO;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
	 			MemberDAO dao = new MemberDAO();
	 			int result = dao.isId(request.getParameter("id"));
	 			System.out.println(result + "를 반환");
	 			response.getWriter().append(Integer.toString(result));
	 			return null;
	}
}
