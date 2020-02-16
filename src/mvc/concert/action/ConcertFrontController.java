package mvc.concert.action;

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

/**
 * Servlet implementation class BoardFrontController
 */
@WebServlet("*.co")
public class ConcertFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
	   
    public ConcertFrontController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request,
    							HttpServletResponse response)
    	throws ServletException, IOException {
    	/* 요청된 전체 URL 중에서 포트 번호 다음 부터 마지막 문자열까지 반환된다.
    	 * 예) http://localhost:8088/JspProject/login.net인 경우
    	 * "/JspProject/login.net" 반환됩니다.
    	 * */
    	String RequestURI = request.getRequestURI();
    	System.out.println("리퀘스트URI = " + RequestURI);
    	
    	// getContextPath() : 컨텍스트 경로가 반환됩니다.
    	// contextPath는 "/JspProject"가 반환됩니다.
    	
    	String contextPath = request.getContextPath();
    	System.out.println("contextPath = " + contextPath);
    	
    	// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자로부터
    	// 마지막 위치 문자까지 추출합니다.
    	// command는 "/login.net" 반환됩니다.
    	String command = RequestURI.substring(RequestURI.lastIndexOf("/"));
    	//String command = RequestURI.substring(contextPath.length());
    	System.out.println("command = " + command);
    	
    	//초기화
    	ActionForward forward = null;
    	Action action=null;
    	
    	if(command.equals("/searchword.co")) {
    		action = new SearchwordAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/concert_delete.co")) {
    		action = new ConcertDeleteAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if(command.equals("/ConcertAddAction.co")) {
    		action = new ConcertAddAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	}  else if(command.equals("/ConcertDetailAction.co")) {
    		action = new ConcertDetailAction();
    		try {
    			forward=action.execute(request, response);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    	} else if (command.equals("/searchfilter.co")) {
			action = new SearchfilterAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/filterdelete.co")) {
			action = new filterDeleteAction();
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
