package client;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

import chat.User;

public class UserList extends JPanel {
	private DefaultListModel<User> listModel = new DefaultListModel<>();
	private ArrayList<User> activeUsers = new ArrayList<User>();
	private JList<User> list;


	public UserList(MouseListener listener) {
		list = new JList<>(listModel);
		list.setCellRenderer(new UserRenderer());
		list.setBackground(new Color(238, 238, 238));
		list.addMouseListener(listener);
		add(list);
	}

	public void addUser(User user) {
		activeUsers.add(user);
		setUsers(activeUsers);
	}

	public void setUsers(ArrayList<User> list) {
		listModel.clear();
		for (User u : list) {
			listModel.addElement(u);
		}
		repaint();
	}

	public ArrayList<User> getUsers() {
		return activeUsers;
	}

	public ArrayList<User> getSelectedUsers() {
		ArrayList<User> selectedList = new ArrayList<User>();
		
		for (int i = 0; i < listModel.size(); i++) {
			if (list.isSelectedIndex(i)) {
				selectedList.add(activeUsers.get(i));
			}
		}
		return selectedList;
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

			setIcon(user.getImage());
			setText(user.getName() + " ");

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
}
