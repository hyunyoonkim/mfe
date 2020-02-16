package mvc.likey.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.likey.db.likeyDAO;
import mvc.likey.db.likeyVO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class likeyDeleteAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		likeyDAO ldao = new likeyDAO();
		likeyVO lo = new likeyVO();
		// ajax 이용시 한글 깨짐방지
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		lo.setMEMBER_ID((String)session.getAttribute("id"));
		lo.setCONCERT_ID(Integer.parseInt(request.getParameter("concert_id")));


		try {
			ldao.likeyDelete(lo);
		} catch (Exception e) {
			System.out.println("찜 삭제 실패");
			ActionForward forward = new ActionForward();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "찜 삭제 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}

		return null;
	}
}

