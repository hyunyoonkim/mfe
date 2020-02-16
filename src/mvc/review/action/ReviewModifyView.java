package mvc.review.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.review.db.ReviewBean;
import mvc.review.db.ReviewDAO;

public class ReviewModifyView implements Action{
	  @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	      ActionForward forward = new ActionForward();
	      
	      ReviewDAO reviewdao = new ReviewDAO();
	      ReviewBean reviewbean = new ReviewBean();
	      
	      int num=Integer.parseInt(request.getParameter("num"));
	      
	      reviewbean = reviewdao.getDetail(num);
	      
	      if(reviewbean==null) {
	         forward.setRedirect(false);
	         request.setAttribute("message", "리뷰 상세보기 실패입니다.");
	         forward.setPath("error/error.jsp");
	         return forward;
	      }
	      request.setAttribute("reviewbean", reviewbean);
	      forward.setRedirect(false);
	      forward.setPath("review/review_modify.jsp");
	      return forward;
	   }
	}
