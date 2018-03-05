package chat;

import java.util.ArrayList;

public class UserDisconnectedMessage extends Message {
	private ArrayList<User> connectedUsers;
	private User disconnectedUser;
	
	public UserDisconnectedMessage(ArrayList<User> connectedUsers, User disconnectedUser) {
		super(null);
		this.connectedUsers = connectedUsers;
		this.disconnectedUser = disconnectedUser;
	}
	
	public ArrayList<User> getConnectedUsers(){
		return connectedUsers;
	}
	
	public User getDisconnectedUser() {
		return disconnectedUser;
	}
}
