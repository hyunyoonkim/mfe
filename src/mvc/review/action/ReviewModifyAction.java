package mvc.review.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.review.db.ReviewBean;
import mvc.review.db.ReviewDAO;

public class ReviewModifyAction implements Action {

	   @Override
	   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		   request.setCharacterEncoding("UTF-8");
		   ActionForward forward = new ActionForward();
		   ReviewDAO reviewdao = new ReviewDAO();
		   ReviewBean reviewbean = new ReviewBean();
		   
		   String realFolder="";
		   String saveFolder="reviewupload";
		   int fileSize = 10*1024*1024;
		   ServletContext sc = request.getServletContext();
		   realFolder = sc.getRealPath(saveFolder);
		 
		   
		   boolean result = false;
		   
		
		   try {
				MultipartRequest multi = null;
				multi = new MultipartRequest(request, realFolder,
									fileSize,
									"UTF-8",
									new DefaultFileRenamePolicy());
				int num=Integer.parseInt(multi.getParameter("review_id"));	
				String pass = multi.getParameter("review_pass");
				
				boolean usercheck = reviewdao.isReviewWriter(num, pass);
				
				if(usercheck ==false) {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호가 다릅니다.');");
					out.println("history.back();");
					out.println("</script>");
					out.close();
					return null;
				}
		   
				reviewbean.setReview_id(num);
				reviewbean.setMember_id(multi.getParameter("member_id"));
				reviewbean.setReview_pass(pass);
				reviewbean.setReview_title(multi.getParameter("review_title"));
				reviewbean.setReview_content(multi.getParameter("review_content"));
				String check = multi.getParameter("check");
				System.out.println("check=" + check);
				if(check != null) {
					reviewbean.setReview_file(check);
				} else {
					String filename=multi.getFilesystemName("review_file");
					reviewbean.setReview_file(filename);
				}
				result = reviewdao.reviewModify(reviewbean);
				if(result == false) {
					forward.setRedirect(false);
					forward.setPath("error/error.jsp");
					return forward;
				}
				forward.setRedirect(true);
				forward.setPath("ReviewDetailAction.rv?num="+reviewbean.getReview_id());
				return forward;
		   } catch(Exception e) {
			   
		   }
		   return null;
			
		   
	   }
}
