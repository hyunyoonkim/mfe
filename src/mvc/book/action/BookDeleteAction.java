package mvc.book.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.book.db.BookDAO;

public class BookDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		BookDAO dao = new BookDAO();

		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		int book_id = Integer.parseInt(request.getParameter("book_id"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();

		int result = dao.bookdelete(id, book_id);

		String message = "예매가 취소되었습니다.";
		if(result !=1) {
			message = "예매 취소에 실패하였습니다.";
		}
		out.println("<script>");
		out.println("alert('"+message+"');");
		out.println("location.href='mypage.bk';");
		out.println("</script>");
		out.close();
		return null;
	}

}
