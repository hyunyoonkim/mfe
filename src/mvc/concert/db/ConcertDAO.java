package mvc.concert.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import mvc.member.db.Member;
import mvc.book.db.BookBean;


public class ConcertDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;

	public ConcertDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public List<ConcertBean> getConcertList(int genre) {
		List<ConcertBean> concert_list = new ArrayList<ConcertBean>();
		
		try {
			con = ds.getConnection();
			String sql = "select * from MFE_CONCERT " 
					+ "where genre_id = ? ";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, genre);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBean c = new ConcertBean();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_id(rs.getString(8));
				c.setLocal_id(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				concert_list.add(c);
			}
			return concert_list;
		} catch (SQLException e) {
			System.out.println("getSearchList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	public List<ConcertBean> getCustomConcertList(String prefers) {
		List<ConcertBean> custom_concert_list = new ArrayList<ConcertBean>();
		System.out.println(prefers);
		String[] prefersArray = prefers.split(",");
		String add =" where ";
		for(int i =0; i < prefersArray.length; i++) {
			if(i == 0) {
				add += " genre_name = '" + prefersArray[i]+"' "; 
			} else {
				add += " or genre_name = '" +prefersArray[i]+"' ";
			}
			
		}
		System.out.println("add = " + add);
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from MFE_concert inner join MFE_genre using(genre_id) " + add + " order by concert_day desc ");
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			while(rs.next()) {
				index++;
				ConcertBean c = new ConcertBean();
				c.setConcert_id(rs.getInt(2));
				c.setConcert_name(rs.getString(3));
				c.setConcert_day(rs.getDate(4));
				c.setConcert_musician(rs.getString(5));
				c.setConcert_open(rs.getString(6));
				c.setConcert_close(rs.getString(7));
				c.setConcert_image(rs.getString(8));
				c.setGenre_id(rs.getString(1));
				c.setLocal_id(rs.getString(9));
				c.setConcert_price(rs.getString(10));
				
				custom_concert_list.add(c);
				if(index == 20) { break;}  // 20개만 최신순으로 뽑아옴
			}
			return custom_concert_list;
		} catch (SQLException e) {
			System.out.println("getCustomConcertList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	public List<ConcertBean> getLastConcertList() {
		List<ConcertBean> last_concert_list = new ArrayList<ConcertBean>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from MFE_concert order by concert_day desc");
			
			rs = pstmt.executeQuery();
			
			int index = 0;
			while(rs.next()) {
				index++;
				ConcertBean c = new ConcertBean();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_id(rs.getString(8));
				c.setLocal_id(rs.getString(9));
				c.setConcert_price(rs.getString(10));
				
				last_concert_list.add(c);
				if(index == 20) { break;}  // 20개만 최신순으로 뽑아옴
			}
			return last_concert_list;
		} catch (SQLException e) {
			System.out.println("getSearchList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	/* 콘서트 등록 */
	public boolean concertInsert(ConcertBean co) {
		result = 0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection");
			
			pstmt = con.prepareStatement("INSERT INTO MFE_concert VALUES (MFE_concert_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setString(1, co.getConcert_name());
			pstmt.setDate(2, co.getConcert_day());
			pstmt.setString(3, co.getConcert_musician());
			pstmt.setString(4, co.getConcert_open());
			pstmt.setString(5, co.getConcert_close());
			pstmt.setString(6, co.getConcert_image());
			pstmt.setString(7, co.getGenre_id());
			pstmt.setString(8, co.getLocal_id());
			pstmt.setString(9, co.getConcert_price());
			
			result = pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result==1;
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

	/* 콘서트 삭제 */
	public int delete(String id) {
		result = 0;
		try {
			con=ds.getConnection();
			System.out.println("getConnection");
			
			pstmt = con.prepareStatement("delete from MFE_concert where concert_id = ?");
			pstmt.setString(1,  id);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("delete오류");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return result;
	}
	/* 콘서트 검색 */


	// 검색어 입력시 리스트 개수
	public int getListCount(String search_word) {
		int x = 0;
		try {
			con = ds.getConnection();

			String sql = "select count(*) from MFE_CONCERT " + "where (CONCERT_NAME like ? "
					+ "or CONCERT_MUSICIAN like ? ) " + "and CONCERT_DAY >= sysdate ";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search_word + "%");
			pstmt.setString(2, "%" + search_word + "%");

			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getListCount() 에러 : " + ex);
		} finally {
			close();
		}
		return x;
	}

	// 검색어 입력시 리스트

	public List<ConcertBeanN> getSearchList(String search_word) {
		List<ConcertBeanN> list = new ArrayList<ConcertBeanN>();

		try {
			con = ds.getConnection();
			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE " 
					+ "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " + "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where (C.CONCERT_NAME like ? "
					+ "or C.CONCERT_MUSICIAN like ? ) " 
					+ "and C.CONCERT_DAY >= sysdate " 
					+ "order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + search_word + "%");
			pstmt.setString(2, "%" + search_word + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				list.add(c);
			}
			return list;
		} catch (SQLException e) {
			System.out.println("getSearchList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}
	
	/* 관리자 페이지 콘서트 숫자*/
	public int getListCount(int concert_search_field, String concert_search_word) {
		int x = 0;
		String field = "";
		
		switch(concert_search_field) {
		case 0:  //ID
			field = " where concert_id like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 1:  //이름
			field = " where concert_name like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 2:   // 가수
			field = " where concert_musician like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 3:   // 장르
			field = " where genre_name like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		}
		System.out.println(" getListCount field = " + field);
		try { 
			con = ds.getConnection();
			pstmt= con.prepareStatement("select count(*) from MFE_concert inner join mfe_genre using(genre_id) "+field);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("concert에 getListCount 에러" + e);
		} finally {
			close();
		}
		return x;
	}
	
	/* 콘서트 리스트 */
	public List<ConcertBean> getConcertList(int concert_search_field, String concert_search_word, int concert_page, int concert_limit) {
		List<ConcertBean> list = new ArrayList<ConcertBean>();
		String field = "";

		
		switch(concert_search_field) {
		case 0:
			field = " where concert_id like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 1:
			field = " where concert_name like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 2:
			field = " where concert_musician like ";
			field += "'%"+concert_search_word+"%' ";
			break;
		case 3:
			field = " where genre_name like ";
			field += "'%"+concert_search_word+"%' ";
			
			break;
		}
		System.out.println("field = " + field);
		
		try {
			con = ds.getConnection();
			
			String sql = "select concert_id, concert_name, concert_day, concert_musician, concert_open, concert_close, concert_image, genre_name, local_name, concert_price from (select b.*, rownum rnum "
					+                 " from (select * from MFE_concert inner join MFE_genre using(genre_id) inner join MFE_local using(local_id)  "
					+                   field
					+ "                 order by concert_id) b "
					+ 				   ") "
					+ "         where rnum >= ? and rnum<=? ";
			pstmt = con.prepareStatement(sql);
			int startrow = (concert_page-1) * concert_limit +1;
			int endrow = startrow + concert_limit -1;
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			
			
			
			while(rs.next()) {
				ConcertBean c = new ConcertBean();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_id(rs.getString(8));
				c.setLocal_id(rs.getString(9));
				c.setConcert_price(rs.getString(10));
				list.add(c);
			}
			return list;
		} catch(SQLException e) {
			System.out.println("콘서트 getList에서 에러");
			e.printStackTrace();
		} finally {
			close();
		}
		
		return null;
		
		
	}
	public ConcertBean getDetail(int con_id) {
		ConcertBean concert = null;
		try {
			con = ds.getConnection();
			pstmt =con.prepareStatement("select * from MFE_concert where Concert_id=?");
			pstmt.setInt(1, con_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				concert = new ConcertBean();
				concert.setConcert_id(rs.getInt("concert_id"));
				concert.setConcert_name(rs.getString("concert_name"));
				concert.setConcert_day(rs.getDate("concert_day"));
				concert.setConcert_musician(rs.getString("concert_musician"));
				concert.setConcert_open(rs.getString("concert_open"));
				concert.setConcert_close(rs.getString("concert_close"));
				concert.setConcert_image(rs.getString("concert_image"));
				concert.setGenre_id(rs.getString("genre_id"));
				concert.setLocal_id(rs.getString("local_id"));
				concert.setConcert_price(rs.getString("concert_price"));
			}
			return concert;
		} catch(SQLException e) {
			System.out.println("Concert getDetail() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	} //getDetail() 메서드 end

	// 필터에서 date 선택시
	public List<ConcertBeanN> getDateList(Date search_date) {
		List<ConcertBeanN> list = new ArrayList<ConcertBeanN>();
		try {
			con = ds.getConnection();
			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE " + "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " 
					+ "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where C.CONCERT_DAY = ?" 
					+ " and C.CONCERT_DAY >= sysdate"
					+ " order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);
			pstmt.setDate(1, (java.sql.Date) search_date);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				list.add(c);
			}

			return list;
		} catch (SQLException e) {
			System.out.println("getDateList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 필터에서 local
	public List<ConcertBeanN> getLocalList(String[] search_local) {
		List<ConcertBeanN> list = new ArrayList<ConcertBeanN>();

		try {
			con = ds.getConnection();
			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE " 
					+ "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " 
					+ "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where L.LOCAL_NAME = ";

			for (int i = 0; i < search_local.length; i++) {
				if (i == search_local.length - 1)
					sql += " ? ";
				else
					sql += " ? or L.LOCAL_NAME = ";
			}

			sql += " and C.CONCERT_DAY >= sysdate";
			sql += " order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < search_local.length; i++) {
				pstmt.setString(i + 1, search_local[i]);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				list.add(c);
			}

			return list;
		} catch (SQLException e) {
			System.out.println("getLocalList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 필터에서 genre
	public List<ConcertBeanN> getGenreList(String[] search_genre) {
		List<ConcertBeanN> list = new ArrayList<ConcertBeanN>();

		try {
			con = ds.getConnection();
			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE  " 
					+ "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " 
					+ "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where G.GENRE_NAME = ";

			for (int i = 0; i < search_genre.length; i++) {
				if (i == search_genre.length - 1)
					sql += " ? ";
				else
					sql += " ? or G.GENRE_NAME = ";
			}

			sql += " and C.CONCERT_DAY >= sysdate";
			sql += " order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < search_genre.length; i++) {
				pstmt.setString(i + 1, search_genre[i]);
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				list.add(c);
			}

			return list;
		} catch (SQLException e) {
			System.out.println("getGenreList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 필터 date + local + genre
	public List<ConcertBeanN> getFilterList(List<Integer> list) {
		List<ConcertBeanN> flist = new ArrayList<ConcertBeanN>();

		try {
			con = ds.getConnection();

			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE " 
					+ "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " 
					+ "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where C.CONCERT_ID = ";

			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1)
					sql += " ? ";
				else
					sql += " ? or C.CONCERT_ID = ";
			}
			sql += "order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);
			for (int i = 0; i < list.size(); i++) {
				pstmt.setInt(i + 1, list.get(i));
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				flist.add(c);
			}
			return flist;
		} catch (SQLException e) {
			System.out.println("getFilterList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	// 모든 날짜, 전국, 전체 장르 선택시
	public List<ConcertBeanN> getAllList() {
		List<ConcertBeanN> flist = new ArrayList<ConcertBeanN>();
		try {
			con = ds.getConnection();
			String sql = "select C.CONCERT_ID, C.CONCERT_NAME, C.CONCERT_DAY, "
					+ "C.CONCERT_MUSICIAN, C.CONCERT_OPEN, C.CONCERT_CLOSE, "
					+ "C.CONCERT_IMAGE, G.GENRE_NAME, L.LOCAL_NAME, C.CONCERT_PRICE " 
					+ "from MFE_CONCERT C "
					+ "inner join MFE_GENRE G " 
					+ "on C.GENRE_ID = G.GENRE_ID " 
					+ "inner join MFE_LOCAL L "
					+ "on C.LOCAL_ID = L.LOCAL_ID " 
					+ "where C.CONCERT_DAY >= sysdate " 
					+ "order by C.CONCERT_DAY";

			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				ConcertBeanN c = new ConcertBeanN();
				c.setConcert_id(rs.getInt(1));
				c.setConcert_name(rs.getString(2));
				c.setConcert_day(rs.getDate(3));
				c.setConcert_musician(rs.getString(4));
				c.setConcert_open(rs.getString(5));
				c.setConcert_close(rs.getString(6));
				c.setConcert_image(rs.getString(7));
				c.setGenre_name(rs.getString(8));
				c.setLocal_name(rs.getString(9));
				c.setConcert_price(rs.getString(10));

				flist.add(c);
			}
			return flist;
		} catch (SQLException e) {
			System.out.println("getAllList() 에러 : " + e);
			e.printStackTrace();
		} finally {
			close();
		}
		return null;
	}

	public List<ConcertBean> getLikeList(String id) {

		String sql = "select * from MFE_concert inner join MFE_likey on MFE_concert.concert_id = MFE_likey.concert_id "
				+ " and sysdate<MFE_concert.concert_day "
				+ "where MFE_likey.member_id=? order by MFE_likey.likey_id desc";
		List<ConcertBean> list = new ArrayList<ConcertBean>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				ConcertBean c = new ConcertBean();
				c.setConcert_image(rs.getString(7));
				c.setConcert_name(rs.getString("CONCERT_NAME"));
				c.setConcert_id(rs.getInt("CONCERT_ID"));
				list.add(c);
				
			}
			return list; // 값을 담은 객체를 저장한 리스틀 호출한 곳으로 가져갑니다.
		}catch(Exception e) {
			System.out.println("getLikeList() 에러 : " + e);
			e.printStackTrace();
		}finally {
	         if(pstmt != null) {
		            try {
		               pstmt.close();
		            } catch(SQLException ex) {
		               ex.printStackTrace();
		            }
		      }
		     if(con!=null) {
		            try {
		               con.close();
		            }catch(SQLException ex) {
		               ex.printStackTrace();
		            }
		     }
		}
		return null;
	}

}
