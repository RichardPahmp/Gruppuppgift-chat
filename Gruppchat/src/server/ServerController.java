package server;

import java.util.HashMap;

import chat.Message;
import chat.User;

public class ServerController implements ClientListener {
	HashMap<User, Client> userMap;

	@Override
	public void messageReceived(Message m) {
		//hantera inkcåmnna Messages
		
	}
}
