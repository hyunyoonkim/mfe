package mvc.book.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.book.db.BookBean;
import mvc.book.db.BookDAO;
import mvc.concert.db.ConcertBeanJoin;

public class BookAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BookDAO bdao = new BookDAO();
		BookBean bdata = new BookBean();
		ActionForward forward = new ActionForward();
		String eticket = "e"+bdata.hashCode();
		
		request.setCharacterEncoding("UTF-8");
		bdata.setConcert_id(Integer.parseInt(request.getParameter("con_id")));
		bdata.setMember_id(request.getParameter("member_id"));
		bdata.setBook_eticket(eticket);
		bdata.setBook_amount(Integer.parseInt(request.getParameter("amount")));
		bdata.setCard_id(request.getParameter("card_number"));
		bdata.setBook_payment(Integer.parseInt(request.getParameter("book_payment")));
		
		int result = 0;
		
		try {
			
			result = bdao.bookInsert(bdata);
			if(result<=0) {
				System.out.println(result);
				System.out.println("예매 등록 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "예매 등록 실패!!");
				forward.setPath("error/error.jsp");
				return forward;
			}
			
			System.out.println("예매 등록 완료");
			forward.setRedirect(false);
			
			ConcertBeanJoin join = bdao.getReserve(bdata.getConcert_id(), result);
			request.setAttribute("cdata", join);
			forward.setPath("reserve/reserve_complete.jsp");
			return forward;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
