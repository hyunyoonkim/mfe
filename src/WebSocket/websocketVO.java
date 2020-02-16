package WebSocket;

import java.sql.Date;

public class websocketVO {
   private int chat_log_id;
   private int chat_log_id2;
   private String MEMBER_ID;
   private String CHAT_LOG_CONTENT;
   private Date CHAT_LOG_DATE;

   public int getChat_log_id() {
      return chat_log_id;
   }

   public void setChat_log_id(int chat_log_id) {
      this.chat_log_id = chat_log_id;
   }

   public int getChat_log_id2() {
      return chat_log_id2;
   }

   public void setChat_log_id2(int chat_log_id2) {
      this.chat_log_id2 = chat_log_id2;
   }

   public String getMEMBER_ID() {
      return MEMBER_ID;
   }

   public void setMEMBER_ID(String mEMBER_ID) {
      MEMBER_ID = mEMBER_ID;
   }

   public String getCHAT_LOG_CONTENT() {
      return CHAT_LOG_CONTENT;
   }

   public void setCHAT_LOG_CONTENT(String cHAT_LOG_CONTENT) {
      CHAT_LOG_CONTENT = cHAT_LOG_CONTENT;
   }

   public Date getCHAT_LOG_DATE() {
      return CHAT_LOG_DATE;
   }

   public void setCHAT_LOG_DATE(Date cHAT_LOG_DATE) {
      CHAT_LOG_DATE = cHAT_LOG_DATE;
   }

}
