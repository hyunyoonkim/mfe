package mvc.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.net")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String RequestURI = request.getRequestURI();
    	String command = RequestURI.substring(RequestURI.lastIndexOf("/"));
    	
    	ActionForward forward = null;
    	Action action=null;
    	if(command.equals("/main.net")) {
    		action = new MainViewAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/idcheck.net")) {
    		action = new IdCheckAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/joinProcess.net")) {
    		action = new JoinProcessAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/loginProcess.net")) {
    		action = new LoginProcessAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/logOut.net")) {
    		action = new LogoutAction();
    		try {
    			forward= action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/member_update.net")) {
    		action = new Member_updateAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/updateProcess.net")) {
    		action = new UpdateProcessAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/adminPage.net")) {
    		action = new ListAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		
    	}     else if(command.equals("/member_delete.net")) {
    		action = new Member_deleteAction();
    		try {
    			forward= action.execute(request,  response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/member_info.net")) {
    		action = new Member_infoAction();
    		try {
    			forward = action.execute(request, response);
    		}catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/my_delete.net")) {
    		action = new My_deleteAction();
    		try {
    			forward= action.execute(request,  response);
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
