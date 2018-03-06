package chat;

import java.util.ArrayList;

public class UserConnectedMessage extends Message {
	
	private ArrayList<User> connectedUsers;
	private User newUser;
	
	public UserConnectedMessage(ArrayList<User> connectedUsers, User newUser) {
		super(null);
		this.connectedUsers = connectedUsers;
		this.newUser = newUser;
	}
	
	public ArrayList<User> getConnectedUsers(){
		return connectedUsers;
	}
	
	public User getNewUser() {
		return newUser;
	}
}
