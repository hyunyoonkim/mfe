package mvc.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import WebSocket.websocketVO2;
import mvc.board.db.BoardBean;
import mvc.board.db.BoardDAO;
import mvc.concert.db.ConcertBean;
import mvc.concert.db.ConcertDAO;
import mvc.member.db.Member;
import mvc.member.db.MemberDAO;

public class ListAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		if(session.getAttribute("id") == null || !session.getAttribute("id").equals("admin@mfe.com")) {
			forward.setPath("main.net");
			forward.setRedirect(true);
			return forward;
		}
		
		//////////////////////////////////////////////////  concert 구간
		ConcertDAO concertdao = new ConcertDAO();
		List<ConcertBean>concert_list = new ArrayList<ConcertBean>();
		int concert_page = 1;
		int concert_limit = 10;
		int concert_search_field = -1; //널값 안나게 초기화해준것(case에 없어야함)
		String concert_search_word ="";
		
		if(request.getParameter("concert_page") != null) {
			concert_page = Integer.parseInt(request.getParameter("concert_page"));
		}
		
		if(request.getParameter("concert_limit") != null) {
			concert_limit = Integer.parseInt(request.getParameter("concert_limit"));
		}
		
		if(request.getParameter("concert_search_field") != null) {
			concert_search_field = Integer.parseInt(request.getParameter("concert_search_field"));
		} 
		if(request.getParameter("concert_search_word") != null) {
			concert_search_word = request.getParameter("concert_search_word");
		}
		
		int concert_count = concertdao.getListCount(concert_search_field, concert_search_word);
		concert_list = concertdao.getConcertList(concert_search_field, concert_search_word, concert_page, concert_limit);

		int concert_maxpage = (concert_count + concert_limit-1)/concert_limit;
		int concert_startpage = ((concert_page-1)/10)*10+1;
		int concert_endpage = concert_startpage + 10 -1;
		
		if(concert_endpage > concert_maxpage) concert_endpage = concert_maxpage;
		
		String concert_state = request.getParameter("concert_state");
		
		//////////////////////////////////////////////////  board 구간
		
		BoardDAO boarddao = new BoardDAO();
		List<BoardBean> boardlist = new ArrayList<BoardBean>();
		int page2=1;
		int limit2=10;
		int board_search_field = -1; //널값 안나게 초기화해준것(case에 없어야함)
		String board_search_word ="";
		
		if(request.getParameter("page2")!=null) {
			page2=Integer.parseInt(request.getParameter("page2"));
		}
		if(request.getParameter("limit2") != null) {
			limit2 = Integer.parseInt(request.getParameter("limit2"));
		}
		if(request.getParameter("board_search_field") != null) {
			board_search_field = Integer.parseInt(request.getParameter("board_search_field"));
		} 
		if(request.getParameter("board_search_word") != null) {
			board_search_word = request.getParameter("board_search_word");
		}
		
		int listcount2 = boarddao.getListCount(board_search_field, board_search_word);
		boardlist = boarddao.getBoardList(board_search_field, board_search_word,page2, limit2);
	
		int maxpage2 = (listcount2+limit2-1)/limit2;
		int startpage2 = ((page2-1) / 10) * 10 +1;
		int endpage2 = startpage2 + 10 -1;
		if(endpage2 > maxpage2) endpage2 = maxpage2;
		String state = request.getParameter("state");
		
		/////////////////////////////////////////////////// member 구간
		MemberDAO mdao = new MemberDAO();
		List<Member> list = new ArrayList<Member>();
		String state2 = request.getParameter("state2");
		int page = 1;
		int limit = 10;
		int search_field = -1; //널값 안나게 초기화해준것(case에 없어야함)
		String search_word ="";
		
		if(request.getParameter("page")!=null) {
			page =Integer.parseInt(request.getParameter("page"));
		}
		if(request.getParameter("limit") != null) {
			limit = Integer.parseInt(request.getParameter("limit"));
		}
		if(request.getParameter("search_field") != null) {
			search_field = Integer.parseInt(request.getParameter("search_field"));
		} 
		if(request.getParameter("search_word") != null) {
			search_word = request.getParameter("search_word");
		}
		int listcount = mdao.getListCount(search_field, search_word);
		list = mdao.getList(search_field, search_word, page, limit);
		
		int maxpage = (listcount+limit-1)/limit;
		int startpage = ((page-1) /10) * 10 +1;
		int endpage = startpage + 10 -1;
		if(endpage > maxpage) endpage = maxpage;
		
		///////////////////////////////////////////////////////// 채팅 구간 
		int listcount5 = mdao.getServiceCount();
		List<websocketVO2> volist = mdao.ServiceList(page2,limit2);
		
		
		////////////////////////////////////////////////////값 보내는 구간
		if(state == null && state2 == null && concert_state == null) { //첫 시작
			System.out.println("state=null");
			////////////////////////////////////////////////////회원 초기화
			request.setAttribute("page", page);
			request.setAttribute("maxpage", maxpage);
			request.setAttribute("startpage", startpage);
			request.setAttribute("endpage", endpage);
			request.setAttribute("listcount", listcount);
			request.setAttribute("memberlist", list);
			request.setAttribute("search_word", search_word);
			request.setAttribute("limit", limit);
			if(request.getParameter("search_field") == null) {
				request.setAttribute("search_field", 0);
			} else {
				request.setAttribute("search_field", request.getParameter("search_field"));
			}
			////////////////////////////////////////////////////// 콘서트 초기화
			
			request.setAttribute("concert_page", concert_page);
			request.setAttribute("concert_maxpage", concert_maxpage);
			request.setAttribute("concert_startpage", concert_startpage);
			request.setAttribute("concert_endpage", concert_endpage);
			request.setAttribute("concert_count", concert_count);
			request.setAttribute("concert_list", concert_list);
			request.setAttribute("concert_search_word", concert_search_word);
			request.setAttribute("concert_limit", concert_limit);
			if(request.getParameter("concert_search_field") == null) {
				request.setAttribute("concert_search_field", 0);
			} else {
				request.setAttribute("concert_search_field", request.getParameter("concert_search_field"));
			}
			
			
			///////////////////////////////////////////////////// 게시판 초기화
			request.setAttribute("page2", page2); //현재 페이지 수
			request.setAttribute("maxpage2", maxpage2); //최대 페이지 수
			request.setAttribute("startpage2", startpage2);
			request.setAttribute("endpage2", endpage2);
			request.setAttribute("listcount2", listcount2); //총 글의 수
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("board_search_word", board_search_word);
			request.setAttribute("limit2", limit2);
			if(request.getParameter("board_search_field") == null) {
				request.setAttribute("board_search_field", 0);
			} else {
				request.setAttribute("board_search_field", request.getParameter("board_search_field"));
			}
			request.setAttribute("listcount5", listcount5); //상담 요청한 고객 수 카운트
			request.setAttribute("ServiceList", volist);    //상담 요청한 고객
			
			forward.setRedirect(false);
			forward.setPath("admin/adminPage.jsp");
			return forward;
		
		} else if(state2 != null){ // 회원 관리 ajax
			JsonObject object = new JsonObject();
			object.addProperty("page", page);
			object.addProperty("maxpage",maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("search_field", search_field);
			object.addProperty("search_word", search_word);
			object.addProperty("listcount",listcount);
			object.addProperty("limit", limit);
			JsonArray je = new Gson().toJsonTree(list).getAsJsonArray();
			
			object.add("memberlist", je);
			
			Gson gson = new Gson();
			String json = gson.toJson(object);
			response.setHeader("cache-control", "no-cache,no-store");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append(json);
			return null;
		} else if(state != null){	// 자유게시판 관리 ajax
			JsonObject object = new JsonObject();
			object.addProperty("page2", page2);
			object.addProperty("maxpage2",maxpage2);
			object.addProperty("startpage2", startpage2);
			object.addProperty("endpage2", endpage2);
			object.addProperty("board_search_field", board_search_field);
			object.addProperty("board_search_word", board_search_word);
			object.addProperty("listcount2",listcount2);
			object.addProperty("limit2", limit2);
			JsonArray je = new Gson().toJsonTree(boardlist).getAsJsonArray();
		
			object.add("boardlist", je);
			
			Gson gson = new Gson();
			String json = gson.toJson(object);
			response.setHeader("cache-control", "no-cache,no-store");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append(json);
			return null;
		} else if(concert_state != null) { // 콘서트 관리 ajax
			JsonObject object = new JsonObject();
			object.addProperty("concert_page", concert_page);
			object.addProperty("concert_maxpage",concert_maxpage);
			object.addProperty("concert_startpage", concert_startpage);
			object.addProperty("concert_endpage", concert_endpage);
			object.addProperty("concert_count",concert_count);
			object.addProperty("concert_limit", concert_limit);
			object.addProperty("concert_search_field", concert_search_field);
			object.addProperty("concert_search_word", concert_search_word);
			JsonArray je = new Gson().toJsonTree(concert_list).getAsJsonArray();
			
			System.out.println("je = " + je);
			object.add("concert_list", je);
			
			Gson gson = new Gson();
			String json = gson.toJson(object);
			response.setHeader("cache-control", "no-cache,no-store");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().append(json);
			return null;
		} else { // 채팅 ajax(필요시 구현)
			System.out.println("채팅 관리 들어갈 곳");
			return null;
		}
	}
}

