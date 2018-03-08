package server;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import chat.TextMessage;
import chat.Message;
import chat.SynchronizedHashMap;
import chat.User;
import chat.UserConnectedMessage;
import chat.UserDisconnectedMessage;

public class ServerController implements ClientListener, WindowListener {
	private SynchronizedHashMap<User, Client> userMap;
	private SynchronizedHashMap<User, TextMessage> savedMessagesMap;

	private ServerSocket serverSocket;
	
	private ServerViewer viewer;

	// testmain
	public static void main(String[] args) {
		ServerController cont = new ServerController(3280);
	}

	public ServerController(int port) {
		userMap = new SynchronizedHashMap<User, Client>();
		savedMessagesMap = new SynchronizedHashMap<User, TextMessage>();
		User user = new User("asd", null);
		User user2 = new User("qwe", null);
		ArrayList<User> list = new ArrayList<User>();
		list.add(user);
		TextMessage mess = new TextMessage(user2, list, "wow", null);
		savedMessagesMap.put(user, mess);
		savedMessagesMap.put(user, mess);
		viewer = new ServerViewer();
		viewer.addWindowListener(this);
		try {
			serverSocket = new ServerSocket(port);
			start();
		} catch (IOException e) {
			viewer.appendLine("Server failed to start.");
			e.printStackTrace();
		}
	}




	public void start() {
		viewer.appendLine("Server has started.");
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				new ConnectionHandler(socket).start();
			}
		} catch (Exception e) {
			// TODO: handle exception

		}
	}

	/**
	 * Handle messages sent to the server.
	 */
	@Override
	public void messageReceived(Message m) {
		if (m instanceof TextMessage) {
			TextMessage cm = (TextMessage) m;
			cm.setDateReceived();
			sendToList(cm);
			viewer.appendLine("Text message received from " + cm.getSender().getName() + ".");
			viewer.appendLine(cm.getDateReceived() + " " + cm.getText());
		}
	}

	/**
	 * Send a Message to every connected user
	 * 
	 * @param m
	 *            the message to send
	 */
	private void sendToAll(Message m) {
		for (Client client : userMap.values()) {
			client.sendMessage(m);
			
		}
	}

	/**
	 * Send the passed TextMessage to the users specified in the receivers list
	 * 
	 * @param message
	 *            the message to send
	 */
	private void sendToList(TextMessage message) {
		ArrayList<User> receivers = message.getReceivers();
		if(receivers.size() <= 0){
			sendToAll(message);
		}
		for (User user : receivers) {
			if (userMap.containsKey(user)) {
				// The selected user is online
				userMap.get(user).sendMessage(message);
			
			} else {
				// The selected user is offline
				// Save the message to send it later
				savedMessagesMap.put(user, message);
			}
		}
	}

	private class ConnectionHandler extends Thread {
		private Socket socket;

		public ConnectionHandler(Socket socket) {
			this.socket = socket;
		}

		public void run() {
			try {
				ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				Client client = new Client(oos, ois);
				client.setClientListener(ServerController.this);
				client.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void userConnected(User user, Client client) {
		userMap.put(user, client);
		UserConnectedMessage message = new UserConnectedMessage(new ArrayList<User>(userMap.keySet()), user);
		sendToAll(message);
		for (User u : savedMessagesMap.keySet()) {
			if (u.equals(user)) {
				userMap.get(user).sendMessage(savedMessagesMap.get(user));
				savedMessagesMap.remove(user);
			}
		}
		viewer.appendLine("New Client accepted: " + user.getName());
	}

	@Override
	public void userDisconnected(User user) {
		userMap.remove(user);
		UserDisconnectedMessage message = new UserDisconnectedMessage(new ArrayList<User>(userMap.keySet()), user);
		sendToAll(message);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		viewer.appendLine("Exiting.");
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
