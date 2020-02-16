package WebSocket;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class websocketDAO {
   
private Connection conn;
   
   private DataSource ds; 

   private PreparedStatement pstmt;

   private ResultSet rs;
   
   int result;
   
   public websocketDAO() {
      try {

         Context initContext = new InitialContext();

         Context envContext = (Context) initContext.lookup("java:comp/env/");

         ds = (DataSource) envContext.lookup("jdbc/OracleDB");

      } catch (NamingException e) {

         e.printStackTrace();

      }

   }
   
   private void close() {

      try {

      if(rs != null) {rs.close(); rs=null;}

      if(pstmt != null) {pstmt.close(); pstmt=null;}

      if(conn != null) {conn.close(); conn=null;}

      } catch(SQLException e) {

      e.printStackTrace();

      }

      }

   public int submit(websocketVO vo) {
      System.out.print("채팅내용 삽입");
   
   int result = 0;
   try {
      conn = ds.getConnection();
      System.out.println("getConnection");
      pstmt = conn.prepareStatement("insert into MFE_chat (CHAT_LOG_ID, CHAT_LOG_ID2, MEMBER_ID, CHAT_LOG_CONTENT, CHAT_LOG_DATE)  "
            + "values(MFE_chat_seq.nextval, ?,?,?,sysdate)");
      pstmt.setInt(1, vo.getChat_log_id2());
      pstmt.setString(2, vo.getMEMBER_ID());
      pstmt.setString(3, vo.getCHAT_LOG_CONTENT());
      result = pstmt.executeUpdate();   
                              
   } catch (SQLException e) {
      e.printStackTrace();
   }
   finally {
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace();

         }
      }
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
   return result;


}
   
   public int sessId(websocketVO2 vo) {
      System.out.print("세션 아이디 및 아이디 삽입");
   
   int result = 0;
   try {
      conn = ds.getConnection();
      System.out.println("getConnection");
      pstmt = conn.prepareStatement("insert into MFE_sessId (id)  values(?)");
      pstmt.setString(1, vo.getId());

      result = pstmt.executeUpdate();   
                              
   } catch (SQLException e) {
      e.printStackTrace();
   }
   finally {
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace();

         }
      }
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
   return result;


}
   
   
   public int sessIdDelete(String id) {
         result = 0;
         try {
            conn = ds.getConnection();
            System.out.println("getConnection");
            
            String sql = "delete from MFE_sessId where id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();
            System.out.println("seddId 삭제 완료");
         } catch (Exception e) {
            e.printStackTrace();
         } finally {
            close();                        
         }
          return result;
   }
   
   

   public websocketVO2  sessionId(String id) {
        websocketVO2 v = null;
       try {
          conn = ds.getConnection();
          String sql = "select * from MFE_sessId where id = ?";
          pstmt = conn.prepareStatement(sql);
          pstmt.setString(1, id);
          rs = pstmt.executeQuery();
          if(rs.next()) {
             v=new websocketVO2();
             v.setId(rs.getString(1));
     
          } 
         } catch (SQLException e) {
             System.out.println("info() 실패 :" + e);
             e.printStackTrace();
          } finally {
             close();
          }
          return v;
       }
   
   public int increaseSEQ() {
      System.out.print("MFE_chat_log_id2 시퀀스 증가 성공!");
   
   int result = 0;
   try {
      conn = ds.getConnection();
      System.out.println("getConnection");
      pstmt = conn.prepareStatement("select MFE_chat_seq2.nextval from dual");

      rs = pstmt.executeQuery();   
      if(rs.next()) {
         result = rs.getInt(1);
      }
                              
   } catch (SQLException e) {
      e.printStackTrace();
   }
   finally {
      if (pstmt != null) {
         try {
            pstmt.close();
         } catch (SQLException ex) {
            ex.printStackTrace();

         }
      }
      if (conn != null) {
         try {
            conn.close();
         } catch (SQLException ex) {
            ex.printStackTrace();
         }
      }
   }
   return result;


}
   
   
   
}
