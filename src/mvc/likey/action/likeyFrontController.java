package mvc.likey.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;
import mvc.member.action.IdCheckAction;
import mvc.member.action.JoinProcessAction;
import mvc.member.action.ListAction;
import mvc.member.action.LoginProcessAction;
import mvc.member.action.LogoutAction;
import mvc.member.action.MainViewAction;
import mvc.member.action.Member_deleteAction;
import mvc.member.action.Member_infoAction;
import mvc.member.action.Member_updateAction;
import mvc.member.action.UpdateProcessAction;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.lo")
public class likeyFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public likeyFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request,
    							HttpServletResponse response)
    	throws ServletException, IOException {

    	String RequestURI = request.getRequestURI();
    	System.out.println("RequestURI = " + RequestURI);
    	

    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	

    	String command = RequestURI.substring(RequestURI.lastIndexOf("/"));
    	System.out.println("command = " + command);
    	
    	//초기화
    	ActionForward forward = null;
    	Action action=null;
    	if(command.equals("/likeyAdd.lo")) {
    		action = new likeyAddAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/likeyDelete.lo")) {
    		action = new likeyDeleteAction();
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
