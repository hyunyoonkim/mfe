package mvc.review.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.member.action.Action;
import mvc.member.action.ActionForward;

public class ReviewFileDownAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("filename");
		
		String savePath = "reviewupload";
		
		ServletContext context = request.getServletContext();
		String sDownloadPath = context.getRealPath(savePath);
		
		String sFilePath = sDownloadPath + "/" + fileName;
		System.out.println(sFilePath);
		
		byte b[] = new byte[4096];
		
		String sMimeType = context.getMimeType(sFilePath);
		
		if(sMimeType == null)
			sMimeType = "application/octet-stream";
		
		response.setContentType(sMimeType);
		String sEncoding = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
		System.out.println(sEncoding);
		
		response.setHeader("Content-Disposition", "attachment; filename= " + sEncoding);
		
		try(BufferedOutputStream out2 = new BufferedOutputStream(response.getOutputStream());
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(sFilePath));)
		{
			int numRead;
		
			while ((numRead = in.read(b, 0, b.length)) != -1) {
				out2.write(b, 0, numRead);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
