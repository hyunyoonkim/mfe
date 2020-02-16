package mvc.review.action;

import java.util.ArrayList; 
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.review.db.ReviewBean;
import mvc.review.db.ReviewDAO;

public class ReviewConcertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ReviewDAO dao = new ReviewDAO();
		List<ReviewBean> review = new ArrayList<ReviewBean>();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		review = dao.getConcertList(id);
		
		request.setAttribute("concertlist", review);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		
		forward.setPath("review/review_write.jsp");
		return forward;
		
	}

}
