package mvc.chat.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class BookFrontController
 */
@WebServlet("*.ch")
public class ChatFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	   
    public ChatFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request,
    							HttpServletResponse response)
    	throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	String RequestURI = request.getRequestURI();
    	System.out.println("리퀘스트URI = " + RequestURI);
    
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);

    	String command = RequestURI.substring(RequestURI.lastIndexOf("/"));
    	System.out.println("command = " + command);
    	
    	ActionForward forward = null;
    	Action action=null;
    	
    	if(command.equals("/chatlist.ch")) {
    		action = new ChatListAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} 
    	
    	if(forward !=null) {
    		if(forward.isRedirect()) { //리다이렉트 된다.
    			response.sendRedirect(forward.getPath());
    		} else { //포워딩된다.
    			RequestDispatcher dispatcher =
    					request.getRequestDispatcher(forward.getPath());
    					  dispatcher.forward(request, response);
    		}
    	}
    }
    
    
//get이든 post든 dpProcess메서드를 구현하여 처리하도록 하였음.
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
