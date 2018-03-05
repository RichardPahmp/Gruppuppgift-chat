package server;

import chat.Message;

public interface ClientListener {
	public void messageReceived(Message m);
}
