package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import chat.Message;
import chat.TextMessage;
import chat.User;
import chat.UserConnectedMessage;
import chat.UserDisconnectedMessage;

public class ClientController extends Thread {
	private User user = new User("Anonym", new ImageIcon("images/Granny.png"));
	private ClientViewer viewer;
	private TextMessage textMessage;
	private ImageIcon image;
	private ArrayList<User> receivers;
	private ArrayList<User> active;
	private String text;
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	
	private int port;
	private String ip;

	public ClientController(ClientViewer viewer) {
		newUser();
		this.viewer = viewer;
	}
	
	public void run(){
		try{
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(user);
			oos.flush();
		} catch (IOException e){
			e.printStackTrace();
			return;
		}
		
		while(true){
			try {
				Object obj = ois.readObject();
				
				if(obj instanceof UserConnectedMessage){
					UserConnectedMessage mess = (UserConnectedMessage)obj;
					mess.setDateReceived();
					viewer.addMessage(mess);
					viewer.setUserList(mess.getConnectedUsers());
				} else if(obj instanceof UserDisconnectedMessage){
					UserDisconnectedMessage mess = (UserDisconnectedMessage)obj;
					mess.setDateReceived();
					viewer.addMessage(mess);
					viewer.setUserList(mess.getConnectedUsers());
				} else if(obj instanceof TextMessage){
					TextMessage mess = (TextMessage)obj;
					mess.setDateReceived();
					viewer.addMessage(mess);
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void sendMessage(TextMessage message){
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void newUser() {
		UserInput userInput = new UserInput(this);
		userInput.setVisible(true);
	}

	public void connect(String ip, String port, String name, ImageIcon image) {
		user = new User(name, image);
		this.ip = ip;
		this.port = Integer.parseInt(port);
		start();
	}

	public void newMessage() {
		text = viewer.getText();
		if (text.length() > 0) {
			receivers = viewer.getSelectedActiveUsers();
			receivers.add(user);
			image = viewer.getImage();
			textMessage = new TextMessage(user, receivers, text, image);
			viewer.eraseImage();
			sendMessage(textMessage);
		}
	}

}