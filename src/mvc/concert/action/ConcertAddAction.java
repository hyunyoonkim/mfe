package mvc.concert.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import mvc.concert.db.ConcertBean;
import mvc.concert.db.ConcertDAO;
import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class ConcertAddAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ConcertDAO concertdao = new ConcertDAO();
		ConcertBean cdata = new ConcertBean();
		ActionForward forward = new ActionForward();
		
		String realFolder="";
		//WebContent 아래 저장될 폴더 이름
		String saveFolder="concertupload";
	
		int fileSize = 10*1024*1024;
		
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		String myFolder = "D:\\MJ\\te\\WebContent\\concertupload";
		System.out.println("realFolder = " + realFolder);
		boolean result = false;
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(request, realFolder,
								fileSize,
								"UTF-8",
								new DefaultFileRenamePolicy());
			
			cdata.setConcert_name(multi.getParameter("concert_add_name"));
			/* 날짜 */
			String textDate = multi.getParameter("concert_add_day");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date date = format.parse(textDate);
			System.out.println("날짜 : " + date);
			java.sql.Date sqlDate = new java.sql.Date(date.getTime());
			cdata.setConcert_day(sqlDate);
			
			cdata.setConcert_musician(multi.getParameter("concert_add_musician"));
			cdata.setConcert_open(multi.getParameter("concert_add_open"));
			cdata.setConcert_close(multi.getParameter("concert_add_close"));
			
			cdata.setConcert_price(multi.getParameter("concert_price"));
			cdata.setGenre_id(multi.getParameter("concert_add_genre_id"));
			cdata.setLocal_id(multi.getParameter("concert_add_local_id"));
			cdata.setConcert_image(multi.getFilesystemName("CONCERT_FILE"));
			
			
			result = concertdao.concertInsert(cdata);
			
			if(result==false) {
				System.out.println("게시판 등록 실패");
				forward.setRedirect(false);
				request.setAttribute("message","게시판 등록 실패입니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			System.out.println("게시판 등록 완료");
			
			forward.setRedirect(true);
			
			forward.setPath("adminPage.net?concert_page=1");
			return forward;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	
	private String replaceParameter(String param) {
		String result = param;
		if(param != null) {
			result = result.replaceAll("<","&lt;");
			result = result.replaceAll("<","&lt;");
			result = result.replaceAll("<","&lt;");
		}
		return result;
	}
}
