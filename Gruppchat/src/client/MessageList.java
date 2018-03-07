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
import javax.swing.event.ListSelectionListener;

import chat.TextMessage;
import chat.User;

public class MessageList extends JPanel {
	private DefaultListModel<TextMessage> listModel = new DefaultListModel<>();
	private JList<TextMessage> list;

	public MessageList() {
		this.setLayout(new BorderLayout());
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		list = new JList<>(listModel);
		list.setCellRenderer(new MessageRenderer());
		add(list, BorderLayout.WEST);
	}

	public void addMessage(TextMessage textMessage) {
		listModel.addElement(textMessage);
//		list = new JList<>(listModel);
		repaint();
	}
	
	public void addListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	private class MessageRenderer extends JLabel implements ListCellRenderer<TextMessage> {
		public MessageRenderer() {
			setOpaque(false);
		}
		
		
	

		public Component getListCellRendererComponent(JList<? extends TextMessage> list, TextMessage message, int index,
				boolean isSelected, boolean cellHasFocus) {
			setText(message.getSender().getName()+": "+ message.getText());
			setIcon(message.getImage());
			
			return this;
		}

	}
		
		

	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("images/SmallMadeline.png");
		User user2 = new User("Birger", icon);
		User user1 = new User("Stefan",null);
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user2);
		TextMessage message1 = new TextMessage(user2,userList,"Hejsan",icon);
		TextMessage message2 = new TextMessage(user1,userList,"Tjena",null);
		
		JFrame test = new JFrame();
		MessageList messageList = new MessageList();

		
		messageList.addMessage(message1);
		messageList.addMessage(message2);
		test.add(messageList);
		test.pack();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

	}
