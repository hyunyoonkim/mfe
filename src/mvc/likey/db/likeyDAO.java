package mvc.likey.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class likeyDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;

	public likeyDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("ds에러");
			e.printStackTrace();
		}
	}

	public void likeyInsert(likeyVO lo) {
		int result = 0;

		try {
			con = ds.getConnection();

			String sql = "insert into MFE_likey values(MFE_likey_seq.nextval, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, lo.getCONCERT_ID());
			pstmt.setString(2, lo.getMEMBER_ID());
			result = pstmt.executeUpdate();
			if (result == 1)
				System.out.println("데이터 삽입 완료");
		} catch (Exception e) {
			System.out.println("commentInsert에러 ");
			e.printStackTrace();
		} finally {
			close();
		}

	}
	
	public void likeyDelete(likeyVO lo) {
		result = 0;
		try {
			con=ds.getConnection();
			System.out.println("getConnection");
			
			pstmt = con.prepareStatement("delete from MFE_likey where member_id = ? and concert_id = ? ");
			pstmt.setString(1,  lo.getMEMBER_ID());
			pstmt.setInt(2,  lo.getCONCERT_ID());
			
			result = pstmt.executeUpdate();
			if(result == 1) { System.out.println("콘서트 찜 삭제 성공"); }
		} catch(SQLException e) {
			System.out.println("delete오류");
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	
	public List<Integer> getLikeyList(String member_id) {
		List<Integer> list = new ArrayList<Integer>();
		System.out.println(member_id);
		
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("select * from MFE_likey where member_id = ?");
			pstmt.setString(1, member_id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getInt("concert_id"));
			}
			return list;
		} catch(SQLException e) {
			System.out.println("getLikeyList에서 에러");
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return null;
	}
	
	private void close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (pstmt != null) {
				pstmt.close();
				pstmt = null;
			}
			if (con != null) {
				con.close();
				con = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
