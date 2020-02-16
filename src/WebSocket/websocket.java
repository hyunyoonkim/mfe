package WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")

public class websocket {
   private static final List<websocketVO2> sessionList = new ArrayList<websocketVO2>();
   static int   seq;
   /***
    * 웹 소켓이 연결되면 호출되는 이벤트
    */
   @OnOpen
   public void handleOpen(Session session) {
      websocketVO2 vo2 = new websocketVO2();

      System.out.println("=================입장=============================");
      System.out.println("쿼리 스트링 : " + session.getQueryString());

      String customer = "";
      String admin ="";
      if(session.getQueryString().substring(0,5).equals("admin")) {
      String getQuery = session.getQueryString();
      admin = getQuery.substring(0,13);
      customer = getQuery.substring(14);
         
      
      System.out.println("겟쿼리는 : " + getQuery);
      System.out.println("잘라 본 값(어드민):" + admin);
      System.out.println("잘라 본 값(고객):" + customer);
      System.out.println(session.getId() + " : 세션 아이디 값");
      
      if (admin.equals("admin@mfe.com")) {
         vo2.setConnected(true);
         try {
            for (int i = 0; i < sessionList.size(); ++i) {
               if (sessionList.get(i).getId().equals(customer)) {
                        sessionList.get(i).setConnected(true);

                  break;
               }
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
         
      }

      }

      String id = session.getQueryString();

      vo2.setId(id);
      vo2.setSession(session);
      // vo.setConnected(false);
      System.out.println("isConn: " + vo2.isConnected());
      sessionList.add(vo2);
      


      websocketDAO dao = new websocketDAO();
      dao.sessId(vo2);   
   
      sendAllSessionToMessage(session, "start="+customer);   //시작 메세지를 보냄 ( 트루인 사람에게)
      

   }

   /**
    * 웹 소켓으로부터 메시지가 오면 호출되는 이벤트
    * 
    * @param message
    * @return
    */
   @OnMessage
   public void handleMessage(Session session, String message) throws IOException {

      /*
       * for(int i =0; i< sessionList.size(); i++) {
       * System.out.println(sessionList.get(i) + " : 세션 값 조회"); }
       */
      websocketVO vo = new websocketVO();
      String id = session.getQueryString();
      if(id.substring(0, 5).equals("admin")) {
         vo.setMEMBER_ID("admin@mfe.com");
      } else {
         vo.setMEMBER_ID(id);
      }
      String chatContent = message;

      
      
      vo.setCHAT_LOG_CONTENT(chatContent);
        vo.setChat_log_id2(seq);
      sendAllSessionToMessage(session, message);
      websocketDAO dao = new websocketDAO();
      dao.submit(vo);
   

   }

   private void sendAllSessionToMessage(Session self, String message) {

      try {
         for (websocketVO2 vo : sessionList) {

            if (vo.isConnected()) {// 나를 제외한 사람에게 보냅니다.
               vo.getSession().getBasicRemote().sendText(message);

            }

         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   /**
    * 웹 소켓이 닫히면 호출되는 이벤트
    */
   @OnClose
   public void handleClose(Session session) {

      websocketDAO dao = new websocketDAO();
      dao.sessIdDelete(session.getQueryString());
      
      if (!session.getQueryString().equals("admin@mfe.com")) {
         seq= dao.increaseSEQ();
         System.out.println(" seq(Message) : " + seq);
         
      }

      System.out.println("client is now disconnected...");
      try {
         for (int i = 0; i < sessionList.size(); ++i) {
            if (sessionList.get(i).getSession() == session) {

               sessionList.remove(i);
               break;
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }

   /**
    * 웹 소켓이 에러가 나면 호출되는 이벤트
    * 
    */
   @OnError
   public void handleError(Throwable t) {
      System.out.println("error");
      t.printStackTrace();
   }

}