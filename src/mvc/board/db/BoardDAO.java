package mvc.board.db;

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

public class BoardDAO {
	private DataSource ds; 
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;
	public BoardDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public int getListCount(int board_search_field, String board_search_word) {
		int x = 0;
		String field ="";
		
		switch(board_search_field) {
		case 0:  //작성자
			field = " where BOARD_NAME like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 1:  //제목
			field = " where BOARD_SUBJECT like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 2:   //내용
			field = " where BOARD_CONTENT like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 3:   //제목+내용
			field = " where BOARD_SUBJECT like "
					+ "'%"+board_search_word+"%' or "
					+ " BOARD_CONTENT like "
					+ "'%"+board_search_word+"%' ";
			break;
		}
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("SELECT COUNT(*) FROM MFE_BOARD " + field);
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
	public List<BoardBean> getBoardList(int board_search_field, String board_search_word, int page, int limit) {
		String field ="";
		
		switch(board_search_field) {
		case 0:  //작성자
			field = " where BOARD_NAME like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 1:  //제목
			field = " where BOARD_SUBJECT like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 2:   //내용
			field = " where BOARD_CONTENT like ";
			field += "'%"+board_search_word+"%' ";
			break;
		case 3:   //제목+내용
			field = " where BOARD_SUBJECT like "
					+ "'%"+board_search_word+"%' or "
					+ " BOARD_CONTENT like "
					+ "'%"+board_search_word+"%' ";
			break;
		}
		
		System.out.println("Board field = " + field);
		
		String board_list_sql = 
				"select * "
			+   "from (select rownum rnum, b.* "
			+          "from (select * from MFE_BOARD " + field
			+          " order by BOARD_RE_REF desc,"
			+          " BOARD_RE_SEQ asc) b "
			+        ")"
			+   " where rnum >= ? and rnum <= ? ";
		List<BoardBean> list = new ArrayList<BoardBean>();
		int startrow = (page-1) * limit +1;
		int endrow = startrow + limit -1;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담는다.
			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				//board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				//board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				//board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		} catch(Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public int getListCount(String search_select, String search_text) {

		int x = 0;
		String diff = "";
		
		try {
			con = ds.getConnection();
			
			if(search_text != null && !search_text.equals("")) {
				diff = " where " + search_select + " = ? ";
			}
			
			String sql = "select count(*) from MFE_BOARD" + diff;
			
			System.out.println("getListCount sql = " + sql);
			
			pstmt = con.prepareStatement(sql);
			
			if(!diff.equals("")) {
				pstmt.setString(1, search_text);
			}
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
	
	
	public List<BoardBean> getBoardList(int page, int limit, String search_select, String search_text) {
		//page : 페이지
		//limit : 페이지 당 목록의 수
		// BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문이다.
		/*
		 * String sql = "select * "
				+ "from (select rownum rnum, b.* "
				+ "from (select * from board "
				+ " order by BOARD_RE_REF desc,"
				+ " BOARD_RE_SEQ asc) b)"
				+ " where " + search_select + " = ? "
				+ "and rnum >= ? and rnum <= ? ";
		 */
		String diff = "";
		if(search_text != null && !search_text.equals("")) {
			diff = " where " + search_select + " = ? ";
		}
		String board_list_sql = 
				"select * "
			+   "from (select rownum rnum, b.* "
			+          "from (select * from MFE_BOARD "
			+ diff
			+          " order by BOARD_RE_REF desc,"
			+          " BOARD_RE_SEQ asc) b "
			+        ")"
			+   " where rnum >= ? and rnum <= ? ";
			
			System.out.println(board_list_sql);
		List<BoardBean> list = new ArrayList<BoardBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page-1) * limit +1;
				//읽기 시작할 row번호 (1 11 21 31 ...)
		int endrow = startrow + limit -1;
				//읽을 마지막 row 번호 (10 20 30 40 ....)
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_list_sql);
			if(diff.equals("")){
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);}
			else {
				pstmt.setString(1, search_text);
				pstmt.setInt(2, startrow);
				pstmt.setInt(3, endrow);
			}
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담는다.
			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				//board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				//board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				//board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		} catch(Exception ex) {
			System.out.println("getBoardList() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	public boolean isBoardWriter(int num, String pass) {		
		try {
			con = ds.getConnection();
			System.out.println("getConnection");
			
			String board_sql = "select * from MFE_BOARD where BOARD_NUM=? ";
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("BOARD_PASS"))) {
					System.out.println(pass + "와 " + rs.getString("BOARD_PASS") + "는 일치");
					return true;
				}
			}
		} catch(SQLException e) {
			System.out.println("isBoardWriter() 에러");
			e.printStackTrace();
		}finally {
			close();
		}
		return false;
	}
	
	public void setReadCountUpdate(int num) {
		try {
			con = ds.getConnection();
			System.out.println("getConnection");
			
			String sql = "update MFE_BOARD set BOARD_READCOUNT= BOARD_READCOUNT + 1 where BOARD_NUM = ? ";
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
		System.out.println("읽음 수 업뎃 결과 = " + result);
	}
	
	public BoardBean getDetail(int id) {
		BoardBean board = new BoardBean();
		
		try {
		con = ds.getConnection();
		System.out.println("getConnection");
		
		pstmt = con.prepareStatement("select * from MFE_BOARD where board_num = ?");
		pstmt.setInt(1, id);
		rs = pstmt.executeQuery();
		
		if (rs.next()) {
			board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
			board.setBOARD_NAME(rs.getString("BOARD_NAME"));
			board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
			board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
			board.setBOARD_FILE(rs.getString("BOARD_FILE"));
			board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
			board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
			board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
			board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
			board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
		}
		
		} catch(SQLException e) {
			System.out.println("getDetail() 에러 : " + e);
			e.printStackTrace();
		}finally {
			close();
		}
		
		return board;
		
	}
	public int boardReply(BoardBean board) {
		// board 테이블 board_num 필드의 최대값을 구해와서 글을 등록할 때
		// 글 번호를 순차적으로 지정하기 위함입니다.
		// 또한 DB에 저장한 후 다시 보여주기 위해 board_num 필드의 값을 리턴합니다.
		String board_max_sql = "select max(board_num) from MFE_BOARD";
		String sql = "";
		int num = 0;
		/* 답변을 할 원문 글 그룹 번호이다.
		 * 답변을 달게 되면 답변 글은 이 번호와 같은 관련글 번호를 ㄱ자게 철되면서
		 * 같은 그룹에 속하게 된다. 글 목록에서 보여줄 때 하나의 그룹으로 묶여서 출력된다.
		 * */
		int re_ref = board.getBOARD_RE_REF();
		System.out.println("re_Ref =" + re_ref);
		
		int re_lev = board.getBOARD_RE_LEV();
		// 같은 관련 글 중에서 해당 글이 출력되는 순서입니다.
		int re_seq = board.getBOARD_RE_SEQ();
		
		try {
			con = ds.getConnection();
			//트랜잭션을 이용하기 위해 setAutoCommit을 false로 설정한다.
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			
			if(rs.next())
				num = rs.getInt(1) + 1;
			
			sql = "update MFE_BOARD "
				+ " set BOARD_RE_SEQ = BOARD_RE_SEQ + 1 "
				+ " where BOARD_RE_REF = ? "
				+ " and BOARD_RE_SEQ > ? ";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2,  re_seq);
			int result1 = pstmt.executeUpdate();
			// 등록 답변 글의 BOARD_RE_LEV, BOARD_RE_SEQ 값을 원문 글보다 1씩 증가시킨다.
			re_seq = re_seq + 1;
			re_lev = re_lev + 1;
			
			sql="insert into MFE_BOARD "
			         + "(BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,"
			         + " BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF,"
			         + " BOARD_RE_LEV, BOARD_RE_SEQ,"
			         + " BOARD_READCOUNT,BOARD_DATE) "
			         + " values(MFE_BOARD_SEQ.nextval,?,?,?,?,?,?,?,?,?,sysdate)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  board.getBOARD_NAME());
			pstmt.setString(2, board.getBOARD_PASS());
			pstmt.setString(3,  board.getBOARD_SUBJECT());
			pstmt.setString(4,  board.getBOARD_CONTENT());
			pstmt.setString(5, ""); //답변에는 파일을 업로드하지 않음.
			pstmt.setInt(6, re_ref);
			pstmt.setInt(7, re_lev);
			pstmt.setInt(8, re_seq);
			pstmt.setInt(9, 0);
			/*
			pstmt.setInt(1, num);
			pstmt.setString(2,  board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4,  board.getBOARD_SUBJECT());
			pstmt.setString(5,  board.getBOARD_CONTENT());
			pstmt.setString(6, ""); //답변에는 파일을 업로드하지 않음.
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);  // 조회수는 0
			*/
			int result2 = pstmt.executeUpdate();
			if(result1 >= 0 && result2 == 1) {
				con.commit();
				con.setAutoCommit(true);
			} else {
				con.rollback();
				System.out.println("rollback()");
			}
		} catch(SQLException e) {
			
			System.out.println("boardReply에서 오류");
			
			e.printStackTrace();
			
		} finally {
			close();
		}
		return  num;
	}
	public boolean boardModify(BoardBean modifyboard) {
		String sql = "update MFE_BOARD "
				   + "set    BOARD_SUBJECT = ?, BOARD_CONTENT = ?, BOARD_FILE =? "
				   + "where  BOARD_NUM = ? ";
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,  modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2,  modifyboard.getBOARD_CONTENT());
			pstmt.setString(3,  modifyboard.getBOARD_FILE());
			pstmt.setInt(4,  modifyboard.getBOARD_NUM());
			int result = pstmt.executeUpdate();
			if(result == 1)
				return true;
		} catch(Exception ex) {
			System.out.println("boardModify() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	public boolean boardInsert(BoardBean b) {
		try {
			con = ds.getConnection();
			//원문의 경우 lev, seq 필드값은 0이다.
			String sql = "INSERT INTO MFE_BOARD"
					+ "(board_num, board_name, board_pass, "
					+ "board_subject, board_content, board_file, "
					+ "board_re_ref, board_re_lev, board_re_seq, "
					+ "board_readcount, board_date)"
					+ " VALUES (MFE_BOARD_SEQ.nextval, ?, ?, "
					+ "?, ?, ?, "
					+ "MFE_BOARD_SEQ.nextval, 0, 0, "
					+ "0 , sysdate)";
			pstmt = con.prepareStatement(sql);
	
			pstmt.setString(1, b.getBOARD_NAME());
			pstmt.setString(2, b.getBOARD_PASS());
			pstmt.setString(3, b.getBOARD_SUBJECT());
			pstmt.setString(4, b.getBOARD_CONTENT());
			pstmt.setString(5, b.getBOARD_FILE());
			
			
			result = pstmt.executeUpdate();
			System.out.println("게시판 삽입 결과 = " + result);
		} catch(SQLException e) {
			System.out.println("boardInsert() 에러 : " + e);
			e.printStackTrace();
		}finally {
			close();
		}
		return (result==1) ? true:false;
		
	}
	public boolean  boardDelete(int num) {
	      
	      String board_sql = "select BOARD_RE_REF, BOARD_RE_LEV, BOARD_RE_SEQ from MFE_BOARD where BOARD_NUM = ?";
	      String board_delete_sql =
	            "delete from MFE_BOARD "
	            + "where BOARD_RE_REF = ? "
	            + "and BOARD_RE_LEV >= ? "
	            + "and BOARD_RE_SEQ >= ? "
	            + "and BOARD_RE_SEQ < (" + 
	                                       "nvl((SELECT min(board_re_seq) from MFE_BOARD "
	                                       + "where BOARD_RE_REF = ? "
	                                       + "and BOARD_RE_LEV = ? "
	                                       + "and BOARD_RE_SEQ > ?)," + 
	                                        "(select max(BOARD_RE_SEQ)+1 from MFE_BOARD "
	                                        + "where BOARD_RE_REF = ?)"
	                                         + ")"
	                                    + ")";
	        
	      try {
	         con = ds.getConnection();
	         pstmt = con.prepareStatement(board_sql);
	         pstmt.setInt(1,  num);
	            rs = pstmt.executeQuery();
	           
	            int BOARD_RE_REF=0, BOARD_RE_LEV=0, BOARD_RE_SEQ=0;

	         System.out.println("연결 성공");         
	         if(rs.next()) {
	            BOARD_RE_REF=rs.getInt("BOARD_RE_REF");
	            BOARD_RE_LEV=rs.getInt("BOARD_RE_LEV");
	            BOARD_RE_SEQ=rs.getInt("BOARD_RE_SEQ");
	            
	            pstmt = con.prepareStatement(board_delete_sql);
	            pstmt.setInt(1, BOARD_RE_REF);
	            pstmt.setInt(2, BOARD_RE_LEV);
	            pstmt.setInt(3, BOARD_RE_SEQ);
	            
	            pstmt.setInt(4, BOARD_RE_REF);
	            pstmt.setInt(5, BOARD_RE_LEV);
	            pstmt.setInt(6, BOARD_RE_SEQ);
	            
	            pstmt.setInt(7, BOARD_RE_REF);
	            
	            //쿼리 실행후 삭제된 로우(레코드) 갯수가 반환됩니다.
	            int result = pstmt.executeUpdate();
	            System.out.println(result + "개 삭제되었습니다.");
	            if(result>=1)
	               return true;
	         }               
	         } catch (Exception e) {
	            System.out.println("boardDelete() 에러 : "+ e);
	            e.printStackTrace();
	         } finally {
	          close();                        
	      }            
	      return false;      
	   } //boardDelete() end
	private void close() {
		try {
			if(rs != null) {rs.close(); rs=null;}
			if(pstmt != null) {pstmt.close(); pstmt=null;}
			if(con != null) {con.close(); con=null;}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public List<BoardBean> getSearchList(int page, int limit, String search_select, String search_text) {
		//page : 페이지
		//limit : 페이지 당 목록의 수
		// BOARD_RE_REF desc, BOARD_RE_SEQ asc에 의해 정렬한 것을
		// 조건절에 맞는 rnum의 범위 만큼 가져오는 쿼리문이다.
		
		
		String sql = "select * "
				+ "from (select rownum rnum, b.* "
				+ "from (select * from MFE_BOARD "
				+ " order by BOARD_RE_REF desc,"
				+ " BOARD_RE_SEQ asc) b)"
				+ " where "
				+ search_select + " = ? "
				+ "and rnum >= ? and rnum <= ? ";
		
		System.out.println(sql);
				
		List<BoardBean> list = new ArrayList<BoardBean>();
		// 한 페이지당 10개씩 목록인 경우 1페이지, 2페이지, 3페이지, 4페이지...
		int startrow = (page-1) * limit +1;
				//읽기 시작할 row번호 (1 11 21 31 ...)
		int endrow = startrow + limit -1;
				//읽을 마지막 row 번호 (10 20 30 40 ....)
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search_text);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			//DB에서 가져온 데이터를 VO 객체에 담는다.
			while (rs.next()) {
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				//board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				//board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				//board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}
			
			return list;
		} catch(Exception ex) {
			System.out.println("getSearchList() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
}
