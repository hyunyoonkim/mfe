package mvc.chat.db;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import mvc.book.db.BookBean;

public class ChatDAO {
	private DataSource ds; 
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	int result=0;
	
	public ChatDAO(){
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			ds = (DataSource) envContext.lookup("jdbc/OracleDB");
		} catch (NamingException e) {
			System.out.println("DB연결실패");
			e.printStackTrace();
		}
	}
	public int getListCount(String id) {
		int x = 0;
		try {
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("select count(count(*)) from MFE_chat where member_id=? group by chat_log_id2");
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			
			if(rs.next()) {
				//총 count 갯수를 뽑아와서 넣어줌
				x = rs.getInt(1);
			}
			
		}catch(Exception e) {
			System.out.println("getListCount() 에러 : " + e);
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
		return x;
	}
	
	public ArrayList<ChatBean> chatLog(String id){
		
		String sql = "select chat_log_id2 " + 
				"from MFE_chat " + 
				"where MFE_chat.member_id=? " + 
				"or MFE_chat.member_id='admin@mfe.com' " + 
				"group by chat_log_id2 " + 
				"order by chat_log_id2 desc";
		
		

		ArrayList<ChatBean> list = new ArrayList<ChatBean>();
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
		
			
			while(rs.next()) {
				ChatBean chat = new ChatBean();
				chat.setChat_log_id2(rs.getInt("CHAT_LOG_ID2"));
				
				list.add(chat);
				
			}
			return list; 
		}catch(Exception e) {
			System.out.println("chatLog() 에러 : " + e);
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
		return list;
	}

	public List<ChatBean> getChatList(String id) {

		String sql = "select * "
				+   "from MFE_chat where member_id=? or member_id='admin@mfe.com'";
		
		

		List<ChatBean> list = new ArrayList<ChatBean>();
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
		
			
			while(rs.next()) {
				ChatBean chat = new ChatBean();
				chat.setChat_log_id(rs.getInt("CHAT_LOG_ID"));
				chat.setChat_log_id2(rs.getInt("CHAT_LOG_ID2"));
				chat.setChat_log_content(rs.getString("CHAT_LOG_CONTENT"));
				chat.setChat_log_date(rs.getDate("CHAT_LOG_DATE"));
				chat.setMember_id(rs.getString("MEMBER_ID"));
				
				list.add(chat);
				
			}
			return list; 
		}catch(Exception e) {
			System.out.println("getChatList() 에러 : " + e);
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

	
	public JsonArray Chatlist2(int log_id2, String id) {
		JsonArray chatlist2 = new JsonArray();
		
		try {
			String sql = "select chat_log_content, chat_log_date, member_id from MFE_chat where (member_id='admin@mfe.com' or member_id=?) and chat_log_id2=?";
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, log_id2);
			
		

			rs = pstmt.executeQuery();
		
			
			while(rs.next()) {
				JsonObject object = new JsonObject();
				object.addProperty("chat_log_content", rs.getString(1));
				object.addProperty("chat_log_date", rs.getString(2));
				object.addProperty("member_id", rs.getString(3));
				chatlist2.add(object);
			}
			return chatlist2; 
		}catch(Exception e) {
			System.out.println("Chatlist2() 에러 : " + e);
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
		return chatlist2;
	}
		
}
