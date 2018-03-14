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

/**
 * Extends JPanel. A JList coustomized for the <code>User</code> class to render
 * both picture and text in the same list cell.
 */
public class UserList extends JPanel {
	private DefaultListModel<User> listModel = new DefaultListModel<>();
	private ArrayList<User> activeUsers = new ArrayList<User>();
	private JList<User> list;

	/**
	 * Requires a <code>MouseListener</code> for initiation.
	 */
	public UserList(MouseListener listener) {
		list = new JList<>(listModel);
		list.setCellRenderer(new UserRenderer());
		list.setBackground(new Color(238, 238, 238));
		list.addMouseListener(listener);
		add(list);
	}

	/**
	 * Adds a <code>User</code> to the list.
	 * 
	 * @param user
	 */
	public void addUser(User user) {
		activeUsers.add(user);
		setUsers(activeUsers);
	}

	/**
	 * Clears the JList from Users.
	 */
	public void clearList() {
		activeUsers.clear();
	}

	/**
	 * Sets the JList with a ArrayList.
	 * 
	 * @param list
	 */
	public void setUsers(ArrayList<User> list) {
		listModel.clear();
		for (User u : list) {
			activeUsers.add(u);
			listModel.addElement(u);
		}
		repaint();
	}

	/**
	 * Return all the <code>User</code> classes contained in the JList.
	 * 
	 * @return ArrayList
	 */
	public ArrayList<User> getUsers() {
		return activeUsers;
	}

	/**
	 * Returns the highlighted <code>User</code> classes from the JList.
	 * 
	 * @return ArrayList
	 */
	public ArrayList<User> getSelectedUsers() {
		ArrayList<User> selectedList = new ArrayList<User>();
		int[] indexes = list.getSelectedIndices();
		for (int n : indexes) {
			selectedList.add(list.getModel().getElementAt(n));
		}
		return selectedList;
	}

	/**
	 * Adds a <code>ListSelectionListener</code>.
	 * 
	 * @param listener
	 */
	public void addListener(ListSelectionListener listener) {
		list.addListSelectionListener(listener);
	}

	/**
	 * Extends JLabel. Custom <code>ListCellRenderer</code> for the <code>User</code> class.
	 */
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
