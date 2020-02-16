package mvc.book.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import mvc.book.db.BookBean;
import mvc.book.db.BookDAO;
import mvc.chat.db.ChatBean;
import mvc.chat.db.ChatDAO;
import mvc.concert.db.ConcertBean;
import mvc.concert.db.ConcertDAO;
import mvc.member.db.Member;
import mvc.member.db.MemberDAO;


public class MyListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		
		
		//booklist
		
			BookDAO bookdao = new BookDAO();
			List<BookBean> booklist = new ArrayList<BookBean>();
						
		//likelist
			
			ConcertDAO cdao = new ConcertDAO();
			List<ConcertBean> likelist = new ArrayList<ConcertBean>();
			
			likelist = cdao.getLikeList(id);
			
			
			request.setAttribute("likelist", likelist);
			
		//------------------------------
			
			//info_update
			
			
			MemberDAO mdao = new MemberDAO();
			Member m = mdao.getDetail2(id);
			if(m==null) {
				System.out.println("정보 가져오기 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "정보 가져오기 실패입니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			request.setAttribute("memberinfo", m);
			
		//-------------------------
			
			
			ChatDAO chatdao = new ChatDAO();
			List<ChatBean> chatlist = new ArrayList<ChatBean>();
			ArrayList<ChatBean> chatloglist = new ArrayList<ChatBean>();
			
			

			int chatlistcount = chatdao.getListCount(id);
			request.setAttribute("chatlistcount", chatlistcount); 
			
			chatloglist = chatdao.chatLog(id);
			request.setAttribute("chatloglist", chatloglist);
			
			chatlist = chatdao.getChatList(id);
			request.setAttribute("chatlist", chatlist);
		
			
		//------------------------
		
			int page=1;
			int limit=10;
			
			if(request.getParameter("page")!=null) {
				page=Integer.parseInt(request.getParameter("page"));
			}
			System.out.println("넘어온 페이지 = " + page);
			
			if(request.getParameter("limit") != null) {
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			System.out.println("넘어온 limit = " + limit);
			
			int listcount = bookdao.getListCount(id);
			
			booklist = bookdao.getBookList(page, limit, id);
			
			int maxpage = (listcount+limit-1)/limit;
			System.out.println("총 페이지 수 = "+maxpage); 
			
			int startpage = ((page-1) / 10) * 10 +1;
			System.out.println("현재 페이지에 보여줄 시작 페이지 수 = " + startpage);
			
			int endpage = startpage + 10 -1;
			System.out.println("현재 페이지에 보여줄 마지막 페이지 수 = " + endpage);
			
			if(endpage > maxpage) endpage = maxpage;
			
			
			
			String state = request.getParameter("state");
			
			if(state == null) {
				System.out.println("state=null");
				request.setAttribute("page", page); 
				request.setAttribute("maxpage", maxpage); 
				
				request.setAttribute("startpage", startpage);
			
				request.setAttribute("endpage", endpage);

				request.setAttribute("listcount", listcount); 
				
				request.setAttribute("booklist", booklist);
				request.setAttribute("limit", limit);
				
				
		
				forward = new ActionForward();
				forward.setRedirect(false);
				
				forward.setPath("mypage/mypage.jsp");
			 	return forward; 
			} else if(state.equals("ajax")){
				System.out.println("state=ajax");
				JsonObject object = new JsonObject();
				object.addProperty("page", page);
				object.addProperty("maxpage",maxpage);
				object.addProperty("startpage", startpage);
				object.addProperty("endpage", endpage);
				object.addProperty("listcount",listcount);
				object.addProperty("limit", limit);


				JsonArray je = new Gson().toJsonTree(booklist).getAsJsonArray();


				System.out.println("je = " + je);
				object.add("booklist", je);


				Gson gson = new Gson();
				String json = gson.toJson(object);



				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().append(json);
				System.out.println(json);
	
				return null;
			}
			return null;
	}

}
