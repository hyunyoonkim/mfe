package mvc.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.db.Member;
import mvc.member.db.MemberDAO;

public class Member_updateAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		MemberDAO mdao = new MemberDAO();
		Member m = mdao.member_info(id);
		if(m==null) {
			System.out.println("정보 가져오기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "정보 가져오기 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		forward.setPath("member/updateForm.jsp");
		forward.setRedirect(false);
		request.setAttribute("memberinfo", m);
		return forward;
	}
}
