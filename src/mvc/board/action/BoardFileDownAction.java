package mvc.board.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class BoardFileDownAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("filename");
		System.out.println("fileName = " + fileName);
		
		String savePath = "boardupload";
		
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		//String sFilePath = sDownloadPath + "\\" + fileName;
		// "\" 추가하기 위해 "\\" 사용한다.
		String sFilePath = sDownloadPath + "/" + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		//sFilePath에 있는 파일의 MimeType을 구해온다.
		String sMimeType = context.getMimeType(sFilePath);
		System.out.println("sMimeType>>>" + sMimeType);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		
		response.setContentType(sMimeType);
		// - 이 부분이 한글 팡리명이 깨지는 것을 방지해 준다.
		String sEncoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		System.out.println(sEncoding);
		
		/* Content-Disposition : attachment : 브라우저는 해당 Content를
		 * 처리하지 않고, 다운로드하게 됩니다.
		 * */
		
		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
		
		try(
				// 웹 브라우저로의 출력 스트림을 생성한다.
				BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
				// sFilePath로 지정한 파일에 대한 입력 스트림을 생성한다.
				BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));)
		
		
		{
			int numRead;
			//read(b, 0, b.length) : 바이트 배열 b의 0번 부터 b.length
			// 크기만큼 읽어온다.
			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
