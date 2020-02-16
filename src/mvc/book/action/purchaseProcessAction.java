package mvc.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.book.db.BookDAO;
import mvc.concert.db.ConcertBeanJoin;

public class purchaseProcessAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO bdao = new BookDAO();
		ConcertBeanJoin join = new ConcertBeanJoin();
		
		
		String con_id_string = request.getParameter("con_id");
		HttpSession session = request.getSession();
		if(request.getParameter("amount1")!=null)
		  session.setAttribute("amount", request.getParameter("amount1"));
		
		session.setAttribute("total", request.getParameter("total1"));
		
		int con_id=-10;
		if(con_id_string !=null) {
			session.setAttribute("con_id_string", con_id_string);
			con_id = Integer.parseInt(con_id_string);
		} else {
			
			con_id = Integer.parseInt((String)session.getAttribute("con_id_string"));
		}
		
		System.out.println(con_id +"= con id");
		
		join = bdao.getReserve(con_id);
		ActionForward forward = new ActionForward();

		if(join==null) {
			System.out.println("예매 정보 불러 오기 실패");
			forward.setRedirect(false);
			request.setAttribute("message", "실패다 닝겐아");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		System.out.println("예매정보 불러오기 성공");
		request.setAttribute("cdata", join);
		forward.setRedirect(false);
		forward.setPath("reserve/purchase.jsp");
		return forward;

	}
}
