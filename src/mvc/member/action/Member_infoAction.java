package mvc.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import mvc.comment.db.CommentDAO;
import mvc.member.db.Member;
import mvc.member.db.MemberDAO;

public class Member_infoAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		 response.setContentType("text/html;charset=UTF-8");
		 response.setHeader("cache-control", "no-cache,no-store");
			PrintWriter out = response.getWriter();
	      MemberDAO mdao = new MemberDAO();
	      
	      String id = request.getParameter("MEMBER_ID");
	      
	      JsonObject json = mdao.getDetail(id);
	      out.print(json);
	      
	      System.out.println("상세보기 성공");
	  	  System.out.println(json);
	  	
	  	  return null;
	   }
	
	
	
	


}
