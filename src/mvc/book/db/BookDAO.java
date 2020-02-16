package mvc.book.db;

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

import mvc.concert.db.ConcertBeanJoin;

public class BookDAO {
	private DataSource ds;
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result = 0;

	public BookDAO() {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
	}
	public List<BookBean> getBookList(int page, int limit, String id) {

		String sql = "select * from (select rownum rnum, b.* "
				+ "from (select * from MFE_book inner join MFE_concert "
				+ "on MFE_concert.concert_id=MFE_book.concert_id "
				+ "where MFE_book.member_id=? order by MFE_book.book_date desc) b ) "
				+ "where rnum >= ? and rnum <= ?";
		List<BookBean> list = new ArrayList<BookBean>();
		
		int startrow = (page-1) * limit +1;
		int endrow = startrow + limit -1;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			// DB에서 가져온 데이터를 VO객체에 담습니다.
			while(rs.next()) {
				BookBean book = new BookBean();
				book.setBook_id(rs.getInt("BOOK_ID"));
				book.setBook_amount(rs.getInt("BOOK_AMOUNT"));
				book.setBook_date(rs.getDate("BOOK_DATE"));
				book.setBook_eticket(rs.getString("BOOK_ETICKET"));
				book.setMember_id(rs.getString("MEMBER_ID"));
				book.setConcert_day(rs.getDate("CONCERT_DAY"));
				book.setConcert_id(rs.getInt("CONCERT_ID"));
				book.setConcert_name(rs.getString("CONCERT_NAME"));
				list.add(book);
				
			}
			System.out.println("id=" + id);
			return list; // 값을 담은 객체를 저장한 리스틀 호출한 곳으로 가져갑니다.
		}catch(Exception e) {
			System.out.println("getBookList() 에러 : " + e);
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
	public int getListCount(String id) {
		int x = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select count(*) from MFE_book where MFE_book.member_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			System.out.println("getListCount() 에러 : " + e);
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return x;
	}


	public boolean bookDelete(int num) {
		String sql = "delete from MFE_book where BOOK_ID = ?";

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			int result = pstmt.executeUpdate();
			System.out.println(result + "개 삭제되었습니다.");
			if (result >= 1)
				return true;
		} catch (Exception e) {
			System.out.println("boardDelete() 에러 : " + e);
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		return false;
	}

	public int bookInsert(BookBean bdata) {
		int num = 0;
		try {
			con = ds.getConnection();
			String sql = "select MFE_book_seq.nextval from dual";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				num = rs.getInt(1);
			}

			sql = "insert into MFE_Book(BOOK_ID, CONCERT_ID, MEMBER_ID, BOOK_ETICKET, BOOK_AMOUNT, CARD_ID, BOOK_DATE, BOOK_PAYMENT) "
					+ "values(?, ?, ?, ?, ?, ?, sysdate, ?)";

			// 새로운 book 등록하는 부분.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2, bdata.getConcert_id());
			pstmt.setString(3, bdata.getMember_id());
			pstmt.setString(4, bdata.getBook_eticket());
			pstmt.setInt(5, bdata.getBook_amount());
			pstmt.setString(6, bdata.getCard_id());
			pstmt.setInt(7, bdata.getBook_payment());
			int result2 = pstmt.executeUpdate();
			if (result2 == 1) {
				System.out.println("마이페이지 예약내역에 데이터 삽입완료되었습니다.");
			}
		} catch (Exception e) {
			num = -1;
			e.printStackTrace();
		}

		finally {

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

		return num;
	}

	public int cardInsert(CardBean cdata) {
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "insert into MFE_card values(?, ?, ?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cdata.getCard_id());
			pstmt.setString(2, cdata.getMember_id());
			pstmt.setInt(3, cdata.getCard_mmyy());
			pstmt.setInt(4, cdata.getCard_cvv());

			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("카드 번호 데이터 삽입완료");
			}
		} catch (java.sql.SQLIntegrityConstraintViolationException e) {
			result = 2;
		} catch (Exception e) {
			result = 3;
			e.printStackTrace();
		} finally {
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
		return result;
	}

	
	public ConcertBeanJoin getReserve(int concert_id, int num) {
		ConcertBeanJoin join = null;
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select * from MFE_book, MFE_CONCERT " + "where MFE_book.concert_id = MFE_concert.concert_id "
					+ "and MFE_book.concert_id = ? and MFE_book.book_id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, concert_id);
			pstmt.setInt(2, num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				join = new ConcertBeanJoin();
				join.setBook_amount(rs.getInt("book_amount"));
				join.setBook_date(rs.getString("book_date"));
				join.setBook_eticket(rs.getString("book_eticket"));
				join.setCard_id(rs.getString("card_id"));
				join.setConcert_close(rs.getString("concert_close"));
				join.setConcert_day(rs.getDate("concert_day"));
				join.setConcert_image(rs.getString("concert_image"));
				join.setConcert_name(rs.getString("concert_name"));
				join.setConcert_open(rs.getString("concert_open"));
				join.setConcert_price(rs.getString("concert_price"));
				join.setConcert_id(rs.getInt("concert_id"));
				join.setBook_payment(rs.getInt("book_payment"));
			}
			return join;
		} catch (SQLException e) {
			System.out.println("getReserve() 에러" + e);
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return null;
	}

	public ConcertBeanJoin getReserve(int concert_id) {
		ConcertBeanJoin join = null;
		try {
			con = ds.getConnection();
			System.out.println("getConnection");

			String sql = "select * from MFE_CONCERT " + "where concert_id = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, concert_id);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				join = new ConcertBeanJoin();

				join.setConcert_close(rs.getString("concert_close"));
				join.setConcert_day(rs.getDate("concert_day"));
				join.setConcert_image(rs.getString("concert_image"));
				join.setConcert_name(rs.getString("concert_name"));
				join.setConcert_open(rs.getString("concert_open"));
				join.setConcert_price(rs.getString("concert_price"));
				join.setConcert_id(rs.getInt("concert_id"));
				join.setTotal(join.getBook_amount() * Integer.parseInt(join.getConcert_price()) + 4000);
			}
			return join;
		} catch (SQLException e) {
			System.out.println("getReserve() 에러" + e);
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}

		return null;
	}

	
	
	
	

	

	public boolean isBook(int num, String pass) {		
		try {
			con = ds.getConnection();
			System.out.println("getConnection");
			
			String sql = "select * from MFE_book where BOOK_ID=?";
			String sql1 = "select MFE_member.member_password from MFE_member inner join MFE_book on MFE_member.member_id=MFE_book.member_id";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt = con.prepareStatement(sql1);
				rs = pstmt.executeQuery();
				if(pass.equals(rs.getString(1))) {
					System.out.println(pass + "와 " + rs.getString(1) + "는 일치");
					return true;
				}
			}
		} catch(SQLException e) {
			System.out.println("isBook() 에러");
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
		return false;
	}

	public int bookdelete(String id, int book_id) {
		result = 0;
		try {
			con=ds.getConnection();
			System.out.println("getConnection");

			pstmt = con.prepareStatement("delete from MFE_book where member_id=? and book_id=?");
			pstmt.setString(1,  id);
			pstmt.setInt(2,  book_id);
			result = pstmt.executeUpdate();
		} catch(SQLException e) {
			System.out.println("bookdelete오류");
			e.printStackTrace();
		} finally {
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
	      return result; 
	}
}
