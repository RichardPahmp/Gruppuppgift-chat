package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import chat.TextMessage;
import chat.Message;
import chat.SynchronizedHashMap;
import chat.User;
import chat.UserConnectedMessage;
import chat.UserDisconnectedMessage;

public class ServerController implements ClientListener, WindowListener, ActionListener {
	private SynchronizedHashMap<User, Client> userMap;
	private SynchronizedHashMap<User, ArrayList<TextMessage>> savedMessagesMap;

	private ServerSocket serverSocket;
	private ServerViewer viewer;
	
	private ServerLog serverLog;
	private Timer saveLogTimer;

	// testmain
	public static void main(String[] args) {
		ServerController cont = new ServerController(3280);
	}

	/**
	 * 
	 * @param port The port for the server to listen on.
	 */
	public ServerController(int port) {
		userMap = new SynchronizedHashMap<User, Client>();
		savedMessagesMap = new SynchronizedHashMap<User, ArrayList<TextMessage>>();
//		User user = new User("asd", null);
//		User user2 = new User("qwe", null);
//		ArrayList<User> list = new ArrayList<User>();
//		list.add(user);
//		TextMessage mess = new TextMessage(user2, list, "wow", null);
//		savedMessagesMap.put(user, mess);
//		savedMessagesMap.put(user, mess);
		serverLog = new ServerLog();
		saveLogTimer = new Timer();
		saveLogTimer.schedule(new saveLogTask(), 30000, 30000);
		
		viewer = new ServerViewer(this);
		viewer.addWindowListener(this);
		viewer.setText(serverLog.getFormattedList());
		try {
			serverSocket = new ServerSocket(port);
			start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * Listens for new connections and create handlers for them.
	 */
	public void start() {
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
			cm.setTimeReceived();
			sendToList(cm);
			log(cm.getSender().getName() + ": " + cm.getText());
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
		//If the only receivers is the sender then send to all users
		if(receivers.size() <= 1){
			sendToAll(message);
			return;
		}
		for (User user : receivers) {
			if (userMap.containsKey(user)) {
				// The selected user is online
				userMap.get(user).sendMessage(message);
			
			} else {
				// The selected user is offline
				// Save the message to send it later
				savedMessagesMap.get(user).add(message);
			}
		}
	}

	/**
	 * 
	 * @author Richard
	 *
	 */
	private class ConnectionHandler extends Thread {
		private Socket socket;

		public ConnectionHandler(Socket socket) {
			this.socket = socket;
		}

		/**
		 * Creates a new Client object and starts it's thread;
		 */
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

	/**
	 * Callback from a Client.
	 */
	@Override
	public void userConnected(User user, Client client) {
		userMap.put(user, client);
		UserConnectedMessage message = new UserConnectedMessage(new ArrayList<User>(userMap.keySet()), user);
		sendToAll(message);
		if(!savedMessagesMap.containsKey(user)) {
			savedMessagesMap.put(user, new ArrayList<TextMessage>());
		}
		for (User u : savedMessagesMap.keySet()) {
			if (u.equals(user)) {
				for(TextMessage mess : savedMessagesMap.get(user)) {
					userMap.get(user).sendMessage(mess);
				}
				savedMessagesMap.get(user).clear();
			}
		}
		log(user.getName() + " has connected.");
	}

	/**
	 * callback from a Client.
	 */
	@Override
	public void userDisconnected(User user) {
		userMap.remove(user);
		UserDisconnectedMessage message = new UserDisconnectedMessage(new ArrayList<User>(userMap.keySet()), user);
		sendToAll(message);
		log(user.getName() + " has disconnected.");
	}
	
	/**
	 * Logs text to the serverlog and updates the viewer.
	 * @param text
	 * 		The text to log
	 */
	private void log(String text) {
		serverLog.add(text);
		updateViewer();
	}
	
	/**
	 * Retrieves the dates to sort by from the viewer and updates the view.
	 */
	private void updateViewer() {
		if(viewer.getFromDate().length() > 0 && viewer.getToDate().length() > 0) {
			SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
				Date from = parser.parse("2018-" + viewer.getFromDate());
				Date to = parser.parse("2018-" + viewer.getToDate());
				viewer.setText(serverLog.getFormattedList(from, to));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		} else {
			viewer.setText(serverLog.getFormattedList());
		}
	}
	
	/**
	 * A TimerTask that saves the serverlog to disk.
	 * @author Richard
	 *
	 */
	private class saveLogTask extends TimerTask {
		public void run() {
			serverLog.writeToFile();
		}
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		serverLog.writeToFile();
		viewer.dispose();
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

	@Override
	public void actionPerformed(ActionEvent arg0) {
		updateViewer();
	}

}
