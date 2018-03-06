package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import chat.Message;
import chat.User;

public class Client extends Thread{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private ClientListener listener;
	private User user;
	
	public Client(ObjectOutputStream oos, ObjectInputStream ois) {
		this.ois = ois;
		this.oos = oos;
	}
	
	public void setClientListener(ClientListener listener) {
		this.listener = listener;
	}
	
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
				// TODO Auto-generated catch block
				e.printStackTrace();
				listener.userDisconnected(user);
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				listener.userDisconnected(user);
				break;
			}
		}
	}
	
	public void sendMessage(Message message){
		try {
			message.setDateSent();
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			listener.userDisconnected(user);
			System.out.println("Error writing message to stream");
		}
	}
}
