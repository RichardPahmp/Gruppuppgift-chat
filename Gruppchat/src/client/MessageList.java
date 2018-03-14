package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
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

/**
 * Extends JPanel. A JList customized for the <code>Message</code> class to render
 * both picture and text in the same list cell.
 * @author Erik Lundov
 *
 */
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

	/**
	 * Adds an incoming message to the <code>JList</code>
	 * @Param message
	 * A received <code>Message</code> 
	 */
	public synchronized void addMessage(Message message) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				listModel.addElement(message);
				list.setModel(listModel);
			}
		});
		repaint();
	}

	/**
	 * Adds a <code>ListSelectionListener</code>.
	 * @param listener
	 */
	public void addListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	
	/**
	 * extends JPanel. Custom <code>ListCellRenderer</code> for the <code>Message</code> class.
	 */
	private class MessageRenderer extends JPanel implements ListCellRenderer<Message> {
		private JLabel label;
		public MessageRenderer() {
			setOpaque(false);
		}

		public Component getListCellRendererComponent(JList<? extends Message> list, Message message, int index,
				boolean isSelected, boolean cellHasFocus) {
			label = new JLabel();
			label.setOpaque(false);
			if(message instanceof TextMessage){
				TextMessage mess = (TextMessage)message;
				String messageReceivers = "";
				if (mess.getReceivers().toString() != "[]") {
					messageReceivers = " till " + mess.getReceivers().toString().substring(1,
							mess.getReceivers().toString().length() - 1);
				}
				label.setText("[" + mess.getTimeSent().format(DateTimeFormatter.ofPattern("HH:mm")) + "] " + mess.getSender().getName() + ": "
						+ mess.getText());
				this.add(label, BorderLayout.WEST);
				if(mess.getImage() != null){
					label.setIcon(mess.getImage());
				}

				return label;
			} else if(message instanceof UserConnectedMessage){
				UserConnectedMessage mess = (UserConnectedMessage)message;
				label.setForeground(new Color(93, 128, 92));
				label.setText("[" + mess.getTimeSent().format(DateTimeFormatter.ofPattern("HH:mm")) + "] " + mess.getNewUser().getName() + " has connected to the chat");
				return label;
			} else if(message instanceof UserDisconnectedMessage){
				UserDisconnectedMessage mess = (UserDisconnectedMessage)message;
				label.setForeground(new Color(167, 69, 69));
				label.setText("[" + mess.getTimeSent().format(DateTimeFormatter.ofPattern("HH:mm")) + "] " + mess.getDisconnectedUser().getName() + " has disconnected from the chat");
				return label;
			}
			
			return null;
		}
	}
}
