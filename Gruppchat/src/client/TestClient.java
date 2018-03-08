package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;

import chat.TextMessage;
import chat.User;
import chat.UserConnectedMessage;

public class TestClient{
	
	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private User user;
	private ClientController controller;
	
	public static void main(String[] args) {
		ClientViewer viewer = new ClientViewer();
		ClientController controller = new ClientController(viewer);
		new TestClient(controller);
	}
	
	public TestClient(ClientController controller) {
		this.controller = controller;
		try {
			socket = new Socket("127.0.0.1", 3280);
			System.out.println("Connected to server");
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			ImageIcon icon = new ImageIcon("images/Madeline.png");
			user = new User("Richard", icon);
			oos.writeObject(user);
			oos.flush();
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				try {
					Object obj = ois.readObject();
					if(obj instanceof UserConnectedMessage) {
						UserConnectedMessage num = (UserConnectedMessage)obj;
						System.out.println(num.getConnectedUsers());
					}
					if(obj instanceof TextMessage) {
//						System.out.println(((TextMessage)obj).getText());
						//controller.receivedMessage(((TextMessage)obj));
						
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				String input = sc.nextLine();
				TextMessage mess = new TextMessage(user, new ArrayList<User>(), input, icon);
				oos.writeObject(mess);
				oos.flush();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
