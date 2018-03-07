package client;

import java.util.LinkedList;

import javax.swing.Icon;

import chat.Message;
import chat.User;

public class ClientController {
	private User user;
	private ClientViewer viewer;
	private Message message;
	private Icon icon;
	private LinkedList <User> receivers;
	private UserList userList;


	public ClientController(ClientViewer viewer) {
		this.viewer = viewer;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void newMessage() {
		userList=viewer.getuserList();
		receivers=userList.getReceivers();
		System.out.println(receivers);
		
	}

	public void uploadImage() {
		// TODO Auto-generated method stub
	}


	public void setUploadedImage() {
		icon = viewer.getUploadedImage();

	}
}
