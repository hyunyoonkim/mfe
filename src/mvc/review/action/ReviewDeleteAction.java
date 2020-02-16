package mvc.review.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.review.db.ReviewDAO;

public class ReviewDeleteAction implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   
		   request.setCharacterEncoding("UTF-8");

		   boolean usercheck = false;
		   
		   int num = Integer.parseInt(request.getParameter("num"));
		   System.out.println(num);
		   
		   ReviewDAO reviewdao = new ReviewDAO();
		   
		   usercheck = reviewdao.isReviewWriter(num, request.getParameter("review_pass"));
		
		   if(usercheck == false) {
		       response.setContentType("text/html;charset=UTF-8");
		       PrintWriter out=response.getWriter();
		       out.println("<script>");
		       out.println("alert('비밀번호가 다릅니다.');");
		       out.println("history.back();");
		       out.println("</script>");
		       out.close();
		       return null;
		   }
		   int result = reviewdao.reviewDelete(num);
		   
		   ActionForward forward = new ActionForward();
		   if(result != 1) {
			   forward.setRedirect(false);
			   request.setAttribute("message", "리뷰 삭제 실패입니다.");
			   forward.setPath("error/error.jsp");
			   return forward;
		   }
		   response.setContentType("text/html;charset=UTF-8");
		   PrintWriter out= response.getWriter();
		   out.println("<script>");
		   out.println("alert('삭제 되었습니다.');");
		   out.println("location.href='ReviewList.rv';");
		   out.println("</script>");
		   out.close();
		   return null;
	   }
}
