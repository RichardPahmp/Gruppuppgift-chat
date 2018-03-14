package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import chat.TextMessage;
import chat.User;
import chat.UserConnectedMessage;
import chat.UserDisconnectedMessage;

/**
 * Extends Thread. Controller class for the Client.
 */
public class ClientController extends Thread {
	private User user;
	private ClientViewer viewer;
	private TextMessage textMessage;
	private ImageIcon image;
	private String text;

	private Socket socket;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private int port;
	private String ip;

	/**
	 * Constructor for the Client, requires a ClientViewer for intitation. Requests
	 * the user to enter network settings and user settings through the
	 * <code>UserInput</code> class.
	 * 
	 * @param viewer
	 *            ClientViewer
	 */
	public ClientController(ClientViewer viewer) {
		newUser();
		this.viewer = viewer;
	}

	/**
	 * Overwritten run method. Initiates network connection through a
	 * <code>ObjectOutputStream</code> and a <code>ObjectInputStream</code> to the
	 * server and sends the entered <code>User</code>. Then listens for incoming
	 * messages from the server.
	 */
	public void run() {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			oos.writeObject(user);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				Object obj = ois.readObject();

				if (obj instanceof UserConnectedMessage) {
					UserConnectedMessage mess = (UserConnectedMessage) obj;
					mess.setTimeReceived();
					viewer.addMessage(mess);
					viewer.setUserList(mess.getConnectedUsers());
				} else if (obj instanceof UserDisconnectedMessage) {
					UserDisconnectedMessage mess = (UserDisconnectedMessage) obj;
					mess.setTimeReceived();
					viewer.addMessage(mess);
					viewer.setUserList(mess.getConnectedUsers());
				} else if (obj instanceof TextMessage) {
					TextMessage mess = (TextMessage) obj;
					mess.setTimeReceived();
					viewer.addMessage(mess);
				}
			} catch (ClassNotFoundException e) {
				System.exit(0);
				e.printStackTrace();
			} catch (IOException e) {
				System.exit(0);
				e.printStackTrace();
			}
		}
	}

	/**
	 * Writes the entered <code>TextMessagge</code> to the server.
	 * 
	 * @param message
	 *            TextMessage
	 */
	private void sendMessage(TextMessage message) {
		try {
			oos.writeObject(message);
			oos.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initiates the <code>UserInput</code> class.
	 */
	public void newUser() {
		UserInput userInput = new UserInput(this);
		userInput.setVisible(true);
	}

	/**
	 * Method for opening a <code>Socket</code> to the entered server.
	 * 
	 * @param ip
	 *            IP for the server.
	 * @param port
	 *            Port for the server.
	 * @param name
	 *            User name.
	 * @param image
	 *            User image.
	 */
	public void connect(String ip, String port, String name, ImageIcon image) {
		user = new User(name, image);
		this.ip = ip;
		this.port = Integer.parseInt(port);
		start();
	}

	/**
	 * Creates and sends a <code>TextMessage</code> to the specified
	 * <code>User</code> obejcts in the <code>ArrayList</code>.
	 * 
	 * @param receivers
	 *            ArrayList
	 */
	public void newMessage(ArrayList<User> receivers) {
		text = viewer.getText();
		if (text.length() > 0 || viewer.getImage() != null) {
			receivers.add(user);
			image = viewer.getImage();
			textMessage = new TextMessage(user, receivers, text, image);
			viewer.eraseImage();
			sendMessage(textMessage);
		}
	}

	/**
	 * check if the user is the logged in user
	 * 
	 * @param u
	 *            User
	 * @return boolean
	 */
	public boolean compareUser(User u) {
		return this.user.equals(u);
	}

}