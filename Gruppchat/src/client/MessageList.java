package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;

import chat.Message;
import chat.TextMessage;
import chat.User;
import chat.UserConnectedMessage;
import chat.UserDisconnectedMessage;

public class MessageList extends JPanel {
	private DefaultListModel<Message> listModel = new DefaultListModel<Message>();
	private JList<Message> list;

	public MessageList() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		list = new JList<Message>(listModel);
		list.setCellRenderer(new MessageRenderer());
		add(list, BorderLayout.WEST);
	}

	public synchronized void addMessage(Message message) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				listModel.addElement(message);
				list.setModel(listModel);
			}
		});
		repaint();
	}

	public void addListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	private class MessageRenderer extends JLabel implements ListCellRenderer<Message> {
		public MessageRenderer() {
			setOpaque(false);
		}

		public Component getListCellRendererComponent(JList<? extends Message> list, Message message, int index,
				boolean isSelected, boolean cellHasFocus) {
			if(message instanceof TextMessage){
				TextMessage mess = (TextMessage)message;
				String messageReceivers = "";
				if (mess.getReceivers().toString() != "[]") {
					messageReceivers = " till " + mess.getReceivers().toString().substring(1,
							mess.getReceivers().toString().length() - 1);
				}
				setText("[" + mess.getDateReceived() + "] " + mess.getSender().getName() + ": "
						+ mess.getText());
				setIcon(mess.getImage());

				return this;
			} else if(message instanceof UserConnectedMessage){
				UserConnectedMessage mess = (UserConnectedMessage)message;
				setText("[" + mess.getDateReceived() + "] " + mess.getNewUser().getName() + " has connected to the chat");
				return this;
			} else if(message instanceof UserDisconnectedMessage){
				UserDisconnectedMessage mess = (UserDisconnectedMessage)message;
				setText("[" + mess.getDateReceived() + "] " + mess.getDisconnectedUser().getName() + " has disconnected from the chat");
				return this;
			}
			
			return null;
		}
	}
}
