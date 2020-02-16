package mvc.concert.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.concert.db.ConcertBean;
import mvc.concert.db.ConcertDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class ConcertDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcertDAO cdao = new ConcertDAO();
		ConcertBean cdata = new ConcertBean();

		// 아이디 번호 파라미터 값을 con_id 변수에 저장
		int con_id = Integer.parseInt(request.getParameter("detail_concert"));
		
		cdata = cdao.getDetail(con_id);

		ActionForward forward = new ActionForward();

		// DAO에서 콘서트 내용을 읽지 못했을 경우 null을 반환한다.
		if (cdata == null) {
			System.out.println("콘서트 상세보기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "콘서트 상세보기 실패");
			forward.setPath("error/error.jsp");
			return forward;
		}

		System.out.println("상세보기 성공");

		// cdao 객체를 request 객체에 저장한다.
		request.setAttribute("cdata", cdata);
		forward.setRedirect(false);

		// 콘서트 내용 보기 페이지로 이동하기 위해 경로를 설정한다.
		forward.setPath("reserve/Reserve_Background.jsp");
		return forward;

	}

}
