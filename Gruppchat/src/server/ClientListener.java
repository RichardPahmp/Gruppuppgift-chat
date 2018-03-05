package server;

import chat.Message;
import chat.User;

public interface ClientListener {
	public void messageReceived(Message m);
	public void userConnected(User user, Client client);
	public void userDisconnected(User user);
}
