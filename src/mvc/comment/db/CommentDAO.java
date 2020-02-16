package mvc.comment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class CommentDAO {
	private DataSource ds; 
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;
	public CommentDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	public int getListCount(int BOARD_RE_REF) {
		int x = 0;
		String sql = "select count(*) "
				+ " from MFE_reply where BOARD_RE_REF = ?";
		
		try { 
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, BOARD_RE_REF);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러 : " + ex);
		} finally {
			close();
		}
		
		return x;
	}
	public JsonArray getCommentList(int BOARD_RE_REF) {
		String sql = "select reply_id, member_id, reply_content, reg_date "
				+ "   from MFE_reply where board_re_ref = ? "
				+ "   order by reg_date desc";
		JsonArray array = new JsonArray();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, BOARD_RE_REF);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("reply_id", rs.getInt(1));
				object.addProperty("member_id", rs.getString(2));
				object.addProperty("reply_content", rs.getString(3));
				object.addProperty("reg_date", rs.getString(4));
				array.add(object);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("getCommentList에서 에러");
		} finally {
			close();
		}
		return array;
	}
	
	public int commentsInsert(CommentVO co) {
		int result = 0;
		
		try {
			con = ds.getConnection();
			
			String sql = "insert into MFE_reply "
					+ " values(MFE_reply_seq.nextval, ?, ?, sysdate, ?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getId());
			pstmt.setString(2, co.getContent());
			pstmt.setInt(3, co.getBoard_re_ref());
			result = pstmt.executeUpdate();
			if(result ==1)
				System.out.println("데이터 삽입 완료");
		} catch(Exception e) {
			System.out.println("commentInsert에러 ");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	public int commentsUpdate(CommentVO co) {
		int result =0;
		
		try {
			con=ds.getConnection();
			String sql = "update MFE_reply set reply_content = ? where reply_id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, co.getContent());
			pstmt.setInt(2,  co.getNum());
			
			result = pstmt.executeUpdate();
			if(result ==1)
				System.out.println("데이터 수정 되었습니다.");
		} catch(Exception e) {
			System.out.println("댓글 업데이트 에러");
		} finally {
			close();
		}
		
		return result;
	}
	
	public int commentsDelete(int num) {
		int result = 0;
		try {
			con = ds.getConnection();
			
			String sql = "delete MFE_reply where reply_id = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if(result == 1)
				System.out.println("데이터 삭제 되었습니다.");
		} catch(Exception e) {
			System.out.println("댓글 삭제 에러");
		} finally {
			close();
		}
		return result;
	}
	private void close() {
		try {
			if(rs != null) {rs.close(); rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(con != null) {con.close(); con=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
