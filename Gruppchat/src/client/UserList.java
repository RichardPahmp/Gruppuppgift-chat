package client;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionListener;

import chat.User;

public class UserList extends JPanel {
	private DefaultListModel<User> listModel = new DefaultListModel<>();
	private JList<User> list ;
	private ArrayList<User> selectedList = new ArrayList<User>();

	public UserList() {
		list = new JList<>(listModel);
		list.setCellRenderer(new UserRenderer());
		list.setBackground(new Color(238, 238, 238));
		add(list);
	}

	public void addUser(User user) {
		listModel.addElement(user);
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

			setIcon(user.getImage());
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

	public ArrayList<User> getReceivers() {
		selectedList.clear();
		for(int i = 0; i<listModel.size();i++) {
			
			if(list.isSelectedIndex(i)) {
				selectedList.add(listModel.getElementAt(i));
			}
		}

		return selectedList;
	}

	

}
