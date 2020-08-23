package websocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.SendHandler;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/server")
public class GoldenHordeServer {

	 private Session session; 
	 private static List<Session>  sessions  = new LinkedList<>();
	 private static List<Session> queueUsers = new LinkedList<>();
	 private static List<Map> matches = new LinkedList<>();
	
	@OnOpen
	public void onOpen(Session session) {
		
		System.out.println("Connected");
		this.session = session;
		sessions.add(session);
		
	}
	
	@OnError
	public void onError(Session session, Throwable throwable) {
		throwable.printStackTrace();
	}
	
	@OnClose
	public void  onClose(Session session) {
		sessions.remove(session);
		System.out.println("Connection closed");
	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		
		System.out.println("Message sended " + msg);
		queuePlayers(session);
		
		if(this.matches.size() > 0) {
			
			this.matches.forEach(m->{
				
					String[] matchSession = (String[]) m.get("match");
					
					if(matchSession[0].equals(session.getId()) || matchSession[1].equals(session.getId()) && session != this.session ) {
						try {
							session.getBasicRemote().sendText("From " + msg);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
			});
		}
		
//		sessions.forEach(s->{
//				if( s != this.session ) {
//					try {
//						s.getBasicRemote().sendText(msg);
//						
//					} catch (IOException   e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//				
//		});
	}
	
	private void queuePlayers(Session session) {
		
		if(this.queueUsers.size() <= 0) {
			
			this.queueUsers.add(session);
			
		}else {
		
			Map<String, String[]> matchMap = new Hashtable<String, String[]>();
			String[] userSessionStrings = {session.getId(),  this.queueUsers.get(0).getId()};
			matchMap.put("match",  userSessionStrings);
			this.matches.add(matchMap);
		}
	}
}
