package websocket;

import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
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
		
		for(int i=0; i < this.matches.size(); i++) {
			//this.matches.get(i).get("match")[0]
		}

		System.out.println("Connection closed");
	}
	
	@OnMessage
	public void onMessage(Session session, String msg) {
		
		System.out.println("Message sended " + msg);
		queuePlayers(session, msg);
		
		if(this.matches.size() > 0) {
			
			this.matches.forEach(m->{
				Session[] matchSession = (Session[]) m.get("match");
				
			
					if(matchSession[0].getId().equals(session.getId())) 
					{
						 try {
								matchSession[1].getBasicRemote().sendText("From " + msg);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
					}
					
					else if(matchSession[1].getId().equals(session.getId())) 
					{
						try 
						{
							matchSession[0].getBasicRemote().sendText("From " + msg);
						} 
						catch (IOException e) 
						{
							e.printStackTrace();
						}
					}
			});
			
		}
			
	}
	
	private void queuePlayers(Session session, String cmd) {
		
			if(cmd.equals("search")) {
				
				this.queueUsers.add(session);
				
				System.out.println("Queue added " + session.getId());
				
				if(this.queueUsers.size() == 2) 
				{
			
					Map<String, Session[]> matchMap = new Hashtable<>();
					
				
					if(!this.queueUsers.get(0).getId().equals(session.getId())){
						
						Session[] userSessionStrings = {this.queueUsers.get(0),  this.queueUsers.get(1)};
						System.out.println("Second player " + this.queueUsers.get(0).getId() + " opponent " + this.queueUsers.get(1).getId());
						matchMap.put("match",  userSessionStrings);
						this.matches.add(matchMap);
						try {
							this.queueUsers.get(0).getBasicRemote().sendText("yes");
							this.queueUsers.get(1).getBasicRemote().sendText("yes");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						this.queueUsers.clear();
				}
			
					System.out.println("Players in queue : " + this.queueUsers.size());
		
				}
			}
		
	}
}
