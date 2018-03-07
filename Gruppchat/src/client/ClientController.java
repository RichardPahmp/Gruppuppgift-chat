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


	public ClientController(ClientViewer viewer) {
		this.viewer = viewer;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void newMessage() {
		receivers=viewer.getRecievers();
		
	}

	public void uploadImage() {
		// TODO Auto-generated method stub
	}


	public void setUploadedImage() {
		icon = viewer.getUploadedImage();

	}
}
