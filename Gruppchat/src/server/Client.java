package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import chat.Message;
import chat.User;

/** 
 * 
 * @author Richard
 * Client represents the serverside connection to a client.
 */
public class Client extends Thread{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private ClientListener listener;
	private User user;
	
	public Client(ObjectOutputStream oos, ObjectInputStream ois) {
		this.ois = ois;
		this.oos = oos;
	}
	
	
	/**
	 * Add a ClientListener to listen for new Messages.
	 * @param listener
	 */
	public void setClientListener(ClientListener listener) {
		this.listener = listener;
	}
	
	/**
	 * Starts the listen thread.
	 * The thread runs and listens for Message objects and notifies any listeners.
	 */
	public void run(){
		Object obj = null;
		try {
			obj = ois.readObject();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(obj instanceof User) {
			user = (User)obj;
			listener.userConnected(user, this);
		}
		
		while(true) {
			try {
				obj = ois.readObject();
				if(obj instanceof Message) {
					listener.messageReceived((Message)obj);
				}
			} catch (IOException e) {
				listener.userDisconnected(user);
				break;
			} catch (ClassNotFoundException e) {
				listener.userDisconnected(user);
				break;
			}
		}
	}
	
	/**
	 * Writes a message to the outputstream.
	 * @param message The message to send
	 */
	public void sendMessage(Message message){
		try {
			message.setTimeSent();
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			listener.userDisconnected(user);
			System.out.println("Error writing message to stream");
		}
	}
}
