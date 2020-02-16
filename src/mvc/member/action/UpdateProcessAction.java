package mvc.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.db.Member;
import mvc.member.db.MemberDAO;

public class UpdateProcessAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
	 							throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		String id = request.getParameter("updateMember_id");
		String pass = request.getParameter("updateMember_pass");
		String name = request.getParameter("updateMember_name");
		String address = request.getParameter("updateMember_address");
		String[] preferences = request.getParameterValues("updateMember_preference");
		String phone_number = request.getParameter("updateMember_phone_number");
		String preference ="";
		for(int i = 0; i < preferences.length; i++) {
			preference += preferences[i];
			if(i == preferences.length-1) {
				break;
			}
			preference += ",";
		}
		String gender = request.getParameter("updateMember_gender");
		
		Member m = new Member();
		m.setId(id);
		m.setPassword(pass);
		m.setName(name);
		m.setAddress(address);
		m.setPhone_number(phone_number);
		m.setPreference(preference);
		m.setGender(gender);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.update(m);
		out.println("<script>");
		if(result==1) {
			out.println("alert('회원 정보가 수정되었습니다.');");
			out.println("location.href='main.net';");
		} else {
			out.println("alert('회원정보 수정에 실패했습니다.');");
			out.println("history.back()");
		}
		out.println("</script>");
		out.close();
		
		return null;
	}
}
