package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

import chat.User;

public class UserList extends JPanel {
	private DefaultListModel<User> listModel = new DefaultListModel<>();
	private JList<User> list = new JList<>();
	private int[] selectedUserIndex;
	private LinkedList<User> selectedList = new LinkedList<User>();

	public UserList() {
		list = new JList<>(listModel);
		list.setCellRenderer(new UserRenderer());
		list.setBackground(new Color(238, 238, 238));
		add(list);
	}

	public void addUser(User user) {
		listModel.addElement(user);
		list = new JList<>(listModel);
		repaint();
	}

	public void addListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	private class UserRenderer extends JLabel implements ListCellRenderer<User> {
		public UserRenderer() {
			setOpaque(true);
		}

		@Override
		public Component getListCellRendererComponent(JList<? extends User> list, User user, int index,
				boolean isSelected, boolean cellHasFocus) {

			setIcon(user.getIcon());
			setText(user.getName() + "  ");

			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}
			return this;
		}
	}

	public LinkedList<User> getReceivers() {
		int[] selectedIx = list.getSelectedIndices();
		System.out.println("hejsan111");

		for (int i = 0; i < selectedIx.length; i++) {
			System.out.println("hej");
			selectedList.add(list.getModel().getElementAt(selectedIx[i]));
			System.out.println(selectedList.get(i).getName());
		}

		return null;
	}

	

}
