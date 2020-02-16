package mvc.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.db.Member;
import mvc.member.db.MemberDAO;

public class JoinProcessAction  implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("addMember_id");
		String pass = request.getParameter("addMember_pass");
		String name = request.getParameter("addMember_name");
		String address = request.getParameter("addMember_address");
		String phone_number = request.getParameter("addMember_phone_number");
		String[] preferences = request.getParameterValues("addMember_preference");
		String preference ="";
		if(preferences != null) {
			for(int i = 0; i < preferences.length; i++) {
				preference += preferences[i];
				if(i == preferences.length-1) {
					break;
				}
				preference += ",";
			}
		}
		System.out.println(id);
		System.out.println(pass);
		System.out.println(name);
		System.out.println(address);
		System.out.println(phone_number);
		System.out.println(preference);
		
		String gender = request.getParameter("addMember_gender");
		System.out.println(gender);
		Member m = new Member();
		m.setId(id);
		m.setPassword(pass);
		m.setName(name);
		m.setAddress(address);
		m.setPhone_number(phone_number);
		m.setPreference(preference);
		m.setGender(gender);
		
		
		
		MemberDAO dao = new MemberDAO();
		int result = dao.insert(m);
		System.out.println(result + "를 반환");
		if(result==1) {
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
			forward.setPath("main.net");
			forward.setRedirect(true);
		} else if(result == -1) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요')");
			//out.println("location.href ='./join.net';");새로고침되어 이전에 입력한 데이터가 나타나지 않는다.
			out.println("history.back()");   // 비밀번호를 제외한 다른 데이터는 유지 되어 있다.
			out.println("<script>");
			out.close();
			return null;
		} 
		
		
		
		return forward;
		
	}
}
