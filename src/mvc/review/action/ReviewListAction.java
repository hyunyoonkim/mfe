package mvc.review.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.review.db.ReviewBean;
import mvc.review.db.ReviewDAO;

public class ReviewListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
	 			
		ReviewDAO reviewdao = new ReviewDAO();
		List<ReviewBean> reviewlist = new ArrayList<ReviewBean>();
		
		int page=1;
		int limit=10;
		
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		int listcount = reviewdao.getListCount();
		
		reviewlist = reviewdao.getReviewList(page, limit);
		
		int maxpage = (listcount+limit-1)/limit;
		
		int startpage = ((page-1) / 10) * 10 +1;
		
		int endpage = startpage + 10 -1;
		
		if(endpage > maxpage) endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if(state == null) {
			request.setAttribute("page", page); 
			request.setAttribute("maxpage", maxpage); 
			
			request.setAttribute("startpage", startpage);

			request.setAttribute("endpage", endpage);

			request.setAttribute("listcount", listcount); 
			
			request.setAttribute("reviewlist", reviewlist);
			request.setAttribute("limit", limit);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			forward.setPath("review/review_list.jsp");
		 	return forward;
		} else {
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage",maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount",listcount);
			object.addProperty("limit", limit);
		
			JsonArray je = new Gson().toJsonTree(reviewlist).getAsJsonArray();
			
			object.add("reviewlist", je);
			
			Gson gson = new Gson();
			String json = gson.toJson(object);
			
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append(json);
			System.out.println(json);
			return null;
		}
}
}
