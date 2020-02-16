package mvc.chat.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import mvc.chat.db.ChatBean;
import mvc.chat.db.ChatDAO;

public class ChatListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//chatlist
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward = new ActionForward();
		
		ChatDAO chatdao = new ChatDAO();
		
		JsonArray chatlist2 = new JsonArray();
		
		int log_id2 = Integer.parseInt(request.getParameter("chat_log_id2"));
		String id = request.getParameter("id");
		
		System.out.println("log_id2"+log_id2);
		chatlist2 = chatdao.Chatlist2(log_id2, id);
		
		try {
			Gson gson2 = new Gson();
			String json2 = gson2.toJson(chatlist2);
			PrintWriter out = response.getWriter();
			
			out.write(json2);
			System.out.println(json2);
			
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	
		return null;
	}

}
