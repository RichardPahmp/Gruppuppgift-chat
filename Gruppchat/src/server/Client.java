package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import chat.Message;

public class Client extends Thread{
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private ClientListener listener;
	
	public Client(ObjectOutputStream oos, ObjectInputStream ois) {
		this.ois = ois;
		this.oos = oos;
	}
	
	public void setClientListener(ClientListener listener) {
		this.listener = listener;
	}
	
	public void run(){
		while(true) {
			try {
				Object obj = ois.readObject();
				if(obj instanceof Message) {
					listener.messageReceived((Message)obj);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
			System.out.println("Error writing message to stream");
		}
	}
}
