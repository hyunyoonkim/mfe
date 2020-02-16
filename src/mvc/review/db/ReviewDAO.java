package mvc.review.db;

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

import mvc.board.db.BoardBean;
 
public class ReviewDAO {
	private DataSource ds; 
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;
	public ReviewDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public int getListCount() {
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM MFE_REVIEW");
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
	
	
	public List<ReviewBean> getReviewList(int page, int limit) {
		
		String sql = "SELECT * FROM (SELECT ROWNUM RNUM, B.* "
				+ "FROM (SELECT * FROM MFE_REVIEW R INNER JOIN MFE_CONCERT C "
				+ "ON C.CONCERT_ID = R.CONCERT_ID "
				+ "ORDER BY R.REVIEW_DATE DESC) B ) "
				+ "WHERE RNUM >= ? AND RNUM <= ?";
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		
		int startrow = (page-1) * limit +1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewBean review = new ReviewBean();
				review.setReview_id(rs.getInt("REVIEW_ID"));
				review.setMember_id(rs.getString("MEMBER_ID"));
				review.setReview_title(rs.getString("REVIEW_TITLE"));
				review.setReview_content(rs.getString("REVIEW_CONTENT"));
				review.setReview_date(rs.getDate("REVIEW_DATE"));
				review.setReview_readcount(rs.getInt("REVIEW_READCOUNT"));
				review.setConcert_name(rs.getString("CONCERT_NAME"));
				review.setConcert_image(rs.getString("CONCERT_IMAGE"));
				list.add(review);
			}
			return list;
		} catch(Exception ex) {
			System.out.println("getReviewList() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	public boolean isReviewWriter(int num, String pass) {		
		try {
			con = ds.getConnection();
			System.out.println("getConnection");
			
			String sql = "SELECT * FROM MFE_REVIEW WHERE REVIEW_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("review_pass"))) {
					System.out.println(pass + "와 " + rs.getString("review_pass") + "는 일치");
					return true;
				}
			}
		} catch(SQLException e) {
			System.out.println("isReviewWriter() 에러");
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public void setReadCountUpdate(int num) {
		try {
			con = ds.getConnection();
			String sql = "UPDATE MFE_REVIEW SET REVIEW_READCOUNT = REVIEW_READCOUNT + 1 WHERE REVIEW_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 1;	  // 업데이트 성공		
			} else {
				result = -1;  // 업데이트 실패(없음)
			}
		} catch(SQLException e) {
			System.out.println("setReadCountUpdate 에러" + e);
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	public ReviewBean getDetail(int num) {
		ReviewBean review = new ReviewBean();
		
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement("SELECT * FROM MFE_REVIEW R INNER JOIN MFE_CONCERT C ON C.CONCERT_ID = R.CONCERT_ID WHERE REVIEW_ID = ?");
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			review.setReview_id(rs.getInt("REVIEW_ID"));
			review.setMember_id(rs.getString("MEMBER_ID"));
			review.setReview_title(rs.getString("REVIEW_TITLE"));
			review.setReview_content(rs.getString("REVIEW_CONTENT"));
			review.setReview_readcount(rs.getInt("REVIEW_READCOUNT"));
			review.setReview_date(rs.getDate("REVIEW_DATE"));
			review.setReview_file(rs.getString("review_file"));
			review.setConcert_image(rs.getString("CONCERT_IMAGE"));
			review.setConcert_name(rs.getString("CONCERT_NAME"));
		}
		
		} catch(SQLException e) {
			System.out.println("getDetail() 에러 : " + e);
			e.printStackTrace();
		}finally {
			close();
		}
		
		return review;
		
	}
	
	public boolean reviewModify(ReviewBean review) {
		String sql = "UPDATE MFE_REVIEW "
				+ "SET REVIEW_TITLE = ?, REVIEW_CONTENT = ?, REVIEW_FILE =? "
				+ "WHERE REVIEW_ID = ?";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  review.getReview_title());
			pstmt.setString(2,  review.getReview_content());
			pstmt.setString(3,  review.getReview_file());
			pstmt.setInt(4,  review.getReview_id());
			int result = pstmt.executeUpdate();
			if(result == 1)
				return true;
		} catch(Exception ex) {
			System.out.println("reviewModify() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	
	
	public boolean reviewInsert(ReviewBean review) {
		boolean Check = false;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("INSERT INTO MFE_REVIEW(REVIEW_ID, MEMBER_ID, REVIEW_PASS, "
					+ "REVIEW_TITLE, REVIEW_CONTENT, REVIEW_READCOUNT, REVIEW_FILE, REVIEW_DATE, CONCERT_ID) "
					+ "VALUES(MFE_REVIEW_SEQ.NEXTVAL, ?, ?, ?, ?, 0, ?, SYSDATE, ?)");
	
			pstmt.setString(1, review.getMember_id());
			pstmt.setString(2, review.getReview_pass());
			pstmt.setString(3, review.getReview_title());
			pstmt.setString(4, review.getReview_content());
			pstmt.setString(5, review.getReview_file());
			pstmt.setInt(6, review.getConcert_id());
			
			int result2 = pstmt.executeUpdate();

			if(result2 == 1) {
				Check = true;
			}else {
				Check = false;
			}
			
		} catch(SQLException e) {
			System.out.println("reviewInsert() 에러 : " + e);
			e.printStackTrace();
		}finally {
			close();
		}
		return Check;
		
	}
	public int  reviewDelete(int num) {
	      result=0;
	      String sql = "DELETE FROM MFE_REVIEW WHERE REVIEW_ID = ?";
	        
	      try {
		         con = ds.getConnection();
		         pstmt = con.prepareStatement(sql);
		         pstmt.setInt(1,  num);
		         result = pstmt.executeUpdate();
	         } catch (Exception e) {
	            System.out.println("reviewDelete() 에러 : "+ e);
	            e.printStackTrace();
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

	public List<ReviewBean> getConcertList(String id) {
		
		String sql = "SELECT * FROM MFE_BOOK B INNER JOIN MFE_CONCERT C "
				+ "ON C.CONCERT_ID = B.CONCERT_ID "
				+ "WHERE B.MEMBER_ID = ? AND C.CONCERT_DAY < SYSDATE";
		List<ReviewBean> list = new ArrayList<ReviewBean>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReviewBean review = new ReviewBean();
				review.setConcert_id(rs.getInt("concert_id"));
				review.setConcert_name(rs.getString("concert_name"));
				review.setConcert_image(rs.getString("concert_image"));
				list.add(review);
			}
			return list;
		} catch(Exception ex) {
			System.out.println("getConcertList() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public int isId(String id, String pass) {
		try {
			con = ds.getConnection();
			String sql = "SELECT MEMBER_ID, MEMBER_PASSWORD FROM MFE_MEMBER WHERE MEMBER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(pass)) {
					result = 1;	 // 비밀번호 일치
				} else {
					result = 0;  // 비밀번호 일치X
				}				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return result;
	}

}
