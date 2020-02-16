package mvc.member.action;

import java.io.PrintWriter;
import java.security.PrivateKey;

import javax.crypto.Cipher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.member.db.MemberDAO;

public class LoginProcessAction  implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String secured_id = request.getParameter("secured_id");
	    String secured_pass = request.getParameter("secured_pass");
	    
	    PrivateKey privateKey = (PrivateKey) session.getAttribute("__rsaPrivateKey__");
	    session.removeAttribute("__rsaPrivateKey__"); 
	    if (privateKey == null) {
            throw new RuntimeException("암호화 비밀키 정보를 찾을 수 없습니다.");
        }
	    String user_id ="";
	    String user_pass="";
	    try {
			user_id = decryptRsa(privateKey, secured_id);
	        user_pass = decryptRsa(privateKey, secured_pass);
	    }catch (Exception ex) {
            throw new ServletException(ex.getMessage(), ex);
        }
		MemberDAO dao = new MemberDAO();
		int result = dao.isId(user_id, user_pass);

	
		if(result==1) { //아이디 비밀번호 일치
				//로그인 성공
				session.setAttribute("id", user_id);
				forward.setRedirect(true);
				forward.setPath("main.net");
				return forward;
		
		}  else {   // 아이디,비밀번호 가 존재하지 않음
			String message = "비밀번호가 일치하지 않습니다.";
			if(result == -1)
				message = "아이디가 존재하지 않습니다.";
			
			System.out.println(message);
			PrintWriter out = response.getWriter();
			
			out.println("<script>");
			out.println("alert('"+message+"');");
			out.println("location.href ='main.net';");
			out.println("</script>");
			out.close();
			return null;
		}
	}
	private String decryptRsa(PrivateKey privateKey, String securedValue) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] encryptedBytes = hexToByteArray(securedValue);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        String decryptedValue = new String(decryptedBytes, "utf-8"); // 문자 인코딩 주의.
        return decryptedValue;
    }
	public static byte[] hexToByteArray(String hex) {
        if (hex == null || hex.length() % 2 != 0) {
            return new byte[]{};
        }

        byte[] bytes = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            byte value = (byte)Integer.parseInt(hex.substring(i, i + 2), 16);
            bytes[(int) Math.floor(i / 2)] = value;
        }
        return bytes;
    }
}
