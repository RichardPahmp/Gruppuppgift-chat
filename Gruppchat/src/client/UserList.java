package client;

import java.awt.Component;

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

	public UserList() {
		list = new JList<>(listModel);
		list.setCellRenderer(new UserRenderer());
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
			setText(user.getName()+"  ");
			
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

	public static void main(String[] args) {
		ImageIcon icon = new ImageIcon("images/SmallMadeline.png");
		User user1 = new User("Test1", icon);
		User user2 = new User("Test2", icon);

		JFrame test = new JFrame();
		UserList userList = new UserList();

		userList.addUser(user1);
		userList.addUser(user2);

		test.add(userList);
		test.pack();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}
}
