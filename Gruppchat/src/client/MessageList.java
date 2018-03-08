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
	private JList<TextMessage> list = new JList<TextMessage>();

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
		list = new JList<>(listModel);
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
			String messageReceivers = "";
			if (message.getReceivers().toString() != "[]") {
				messageReceivers = " till " + message.getReceivers().toString().substring(1,
						message.getReceivers().toString().length() - 1);
			}
			setText("[" + message.getDateReceived() + "] " + message.getSender().getName() + messageReceivers + ": "
					+ message.getText());
			setIcon(message.getImage());

			return this;
		}
	}
}
