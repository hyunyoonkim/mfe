package mvc.concert.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.concert.db.ConcertBeanN;
import mvc.concert.db.ConcertDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class SearchwordAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");

		ConcertDAO cdao = new ConcertDAO();
		List<ConcertBeanN> list = new ArrayList<ConcertBeanN>();

		String search_word = request.getParameter("search_Text");
		System.out.println("search_word = " + search_word);

		list = cdao.getSearchList(search_word);
		System.out.println("list size = " + list.size());
		int count = cdao.getListCount(search_word);

		if (list.size() != 0) {
			request.setAttribute("list", list);
			request.setAttribute("count", count);
			request.setAttribute("search_word", search_word);
			forward.setRedirect(false);
			forward.setPath("search/search_result_form.jsp");
			return forward;
		} else {
			forward.setRedirect(false);
			request.setAttribute("none", "검색 결과가 존재하지 않습니다.");
			forward.setPath("search/search_result_form.jsp");
			return forward;
		}
	}
}
