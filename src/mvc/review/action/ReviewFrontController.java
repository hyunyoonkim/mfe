package mvc.review.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;

@WebServlet("*.rv")
public class ReviewFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	    
    public ReviewFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request,
    							HttpServletResponse response)
    	throws ServletException, IOException {
 
    	String RequestURI = request.getRequestURI();
    	String command = RequestURI.substring(RequestURI.lastIndexOf("/"));
    	
    	ActionForward forward = null;
    	Action action=null;
    	
    	if(command.equals("/ReviewList.rv")) {
    		action = new ReviewListAction(); 
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("/ReviewWrite.rv")) {
    		action = new ReviewConcertAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/ReviewAddAction.rv")) {
    		action = new ReviewAddAction();
    		try {
    			forward = action.execute(request, response);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/ReviewDetailAction.rv")) {
    		action = new ReviewDetailAction();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.contentEquals("/ReviewModifyAction.rv")) {
            action = new ReviewModifyAction();
            try {
               forward=action.execute(request, response);
            } catch (Exception e) {
               e.printStackTrace();
            }
         } else if(command.contentEquals("/ReviewModifyView.rv")) {
             action = new ReviewModifyView();
             try {
                forward=action.execute(request, response);
             } catch (Exception e) {
                e.printStackTrace();
             }
          } else if(command.equals("/ReviewDelete.rv")) {
        	  forward = new ActionForward();
        	  forward.setRedirect(false);//포워딩 방식으로 주소가 바뀌지 않음.
        	  forward.setPath("review/review_delete.jsp");
          } else if(command.equals("/ReviewDeleteAction.rv")) {
        	  action = new ReviewDeleteAction();
        	  try {
        		  forward = action.execute(request, response);
        	  } catch(Exception e) {
        		  e.printStackTrace();
        	  }
          } else if (command.equals("/ReviewFileDown.rv")) {
  			action = new ReviewFileDownAction();
  			try {
  				forward = action.execute(request, response);
  			} catch (Exception e) {
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
