package mvc.member.action;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvc.concert.db.ConcertBean;
import mvc.concert.db.ConcertDAO;
import mvc.likey.db.likeyDAO;
import mvc.member.db.MemberDAO;

public class MainViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcertDAO concertdao = new ConcertDAO();
		HttpSession session = request.getSession();
		
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(1024);
		KeyPair keyPair = generator.genKeyPair();
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyPair.getPublic();
		PrivateKey privateKey = keyPair.getPrivate();
		RSAPublicKeySpec publicSpec = (RSAPublicKeySpec) keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
		String publicKeyModulus = publicSpec.getModulus().toString(16);
		String publicKeyExponent = publicSpec.getPublicExponent().toString(16);
		
		
		likeyDAO likeydao = new likeyDAO();
		
		List<ConcertBean> balad_concert_list = concertdao.getConcertList(1);
		List<ConcertBean> rock_concert_list = concertdao.getConcertList(2);
		List<ConcertBean> rap_concert_list = concertdao.getConcertList(3);
		List<ConcertBean> rb_concert_list = concertdao.getConcertList(4);
		List<ConcertBean> jazz_concert_list = concertdao.getConcertList(5);
		List<ConcertBean> classic_concert_list = concertdao.getConcertList(6);
		List<ConcertBean> pop_concert_list = concertdao.getConcertList(7);
		List<ConcertBean> edm_concert_list = concertdao.getConcertList(8);
		List<ConcertBean> last_concert_list = concertdao.getLastConcertList();
		
		
		
		String id = (String)session.getAttribute("id");
		if(id != null) {
			MemberDAO mdao = new MemberDAO();
			String member_id = (String)session.getAttribute("id");
			List<Integer> likey_list = new ArrayList<Integer>();
			likey_list = likeydao.getLikeyList(member_id);
			System.out.println(likey_list);
			request.setAttribute("likey_list", likey_list);
			String prefer = mdao.Member_prefer(member_id);
			if(!prefer.equals("null")) {	// 선호장르가 있을 경우
				List<ConcertBean> custom_concert_list = concertdao.getCustomConcertList(prefer);
				request.setAttribute("custom_concert_list", custom_concert_list);
			} else { //선호장르가 없을 경우 최신 콘서트 장르를 반환해줌
				request.setAttribute("custom_concert_list", last_concert_list);
			}
		}
		
		request.setAttribute("publicKeyModulus", publicKeyModulus);
		request.setAttribute("publicKeyExponent", publicKeyExponent);
		session.setAttribute("__rsaPrivateKey__", privateKey);
		request.setAttribute("jazz_concert_list", jazz_concert_list);
		request.setAttribute("balad_concert_list", balad_concert_list);
		request.setAttribute("rap_concert_list", rap_concert_list);
		request.setAttribute("rb_concert_list", rb_concert_list);
		request.setAttribute("classic_concert_list", classic_concert_list);
		request.setAttribute("rock_concert_list", rock_concert_list);
		request.setAttribute("pop_concert_list", pop_concert_list);
		request.setAttribute("edm_concert_list", edm_concert_list);
		request.setAttribute("last_concert_list", last_concert_list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);

		forward.setPath("mainActivity/main.jsp");
		return forward;

	}
}