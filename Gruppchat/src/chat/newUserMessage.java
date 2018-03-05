package chat;

import java.util.ArrayList;

public class newUserMessage extends Message {
	
	private ArrayList<User> connectedUsers;
	private User newUser;
	
	public newUserMessage(User sender, ArrayList<User> connectedUsers, User newUser) {
		super(sender);
		this.connectedUsers = connectedUsers;
		this.newUser = newUser;
	}
	
	public ArrayList<User> getConnectedUsers(){
		return connectedUsers;
	}
}
