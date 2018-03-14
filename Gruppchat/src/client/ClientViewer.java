package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import chat.Contacts;
import chat.Message;
import chat.User;

/**
 * <code> ClientViewer</code> is a UI capable of sending and receiving messages
 * containing ImageIcons and Strings. It also shows other users that or
 * connected to the same server. These users can be saved to a contactlist.
 * 
 * @author Erik Lundov
 *
 */
public class ClientViewer extends JFrame implements ActionListener {

	private JPanel pnlContent;
	private JPanel pnlUsers;
	private JPanel pnlText;
	private JPanel pnlButtons;
	private JScrollPane scrollPanelText = new JScrollPane();
	private JScrollPane scrollPanelUsers = new JScrollPane();
	private JScrollPane scrollPanelContacts = new JScrollPane();
	private JScrollBar scrollBar = scrollPanelText.getVerticalScrollBar();
	private JFileChooser fileChooser = new JFileChooser();

	private JLabel lblActiveUsers;
	private JLabel lblContacts;

	private TextField tfWrite;

	private JButton btnSendMessage;
	private JButton btnUploadImage;

	private UserList userList = new UserList(new popupListener());
	private MessageList messageList = new MessageList();

	private UserList contactList = new UserList(null);

	private ClientController controller;
	private ImageIcon image;
	private Image img;

	private JPopupMenu popupMenu;

	private Contacts contacts;

	/**
	 * <code>ClientViewer</code> is the constructor for the class. It builds the
	 * frame and load in contacts.
	 */
	public ClientViewer() {
		// Set the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlContent.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlContent);

		// Add the panel that contains active users
		scrollPanelUsers.setViewportView(userList);
		scrollPanelUsers.setPreferredSize(new Dimension(100, 300));
		pnlContent.add(scrollPanelUsers, BorderLayout.WEST);

		// Add the panel that contains contacts
		scrollPanelContacts.setViewportView(contactList);
		scrollPanelContacts.setPreferredSize(new Dimension(100, 300));
		pnlContent.add(scrollPanelContacts, BorderLayout.EAST);
		contacts = new Contacts();
		readInContacts();

		// Add the textpanels
		tfWrite = new TextField("Skriv ditt meddelande här...");
		tfWrite.setHorizontalAlignment(SwingConstants.LEFT);

		pnlText = new JPanel();
		pnlText.setLayout(new BorderLayout(0, 0));
		scrollPanelText.setViewportView(messageList);
		pnlText.add(tfWrite, BorderLayout.SOUTH);
		pnlText.add(scrollPanelText, BorderLayout.CENTER);

		pnlContent.add(pnlText, BorderLayout.CENTER);
		tfWrite.setColumns(10);

		// Labels for userlist and contactlist
		pnlUsers = new JPanel();
		pnlContent.add(pnlUsers, BorderLayout.NORTH);
		pnlUsers.setLayout(new GridLayout(0, 2, 0, 0));
		lblActiveUsers = new JLabel("Active users");
		lblActiveUsers.setHorizontalAlignment(SwingConstants.LEFT);
		lblActiveUsers.setVerticalAlignment(SwingConstants.TOP);
		pnlUsers.add(lblActiveUsers);
		lblContacts = new JLabel("Contacts");
		lblContacts.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUsers.add(lblContacts);

		// Add panel containing buttons
		pnlButtons = new JPanel();
		pnlContent.add(pnlButtons, BorderLayout.SOUTH);
		btnUploadImage = new JButton("Upload File");
		pnlButtons.add(btnUploadImage);
		btnSendMessage = new JButton("Send Message");
		pnlButtons.add(btnSendMessage);

		addListeners();
		pnlButtons.getRootPane().setDefaultButton(btnSendMessage);

		// Add popup menu
		popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Add Contact");
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);

	}

	/**
	 * Adds a User to the list of active users.
	 * 
	 * @param user
	 *            An active User
	 * @author Erik Lundov
	 */
	public void addUser(User user) {
		userList.addUser(user);
	}

	/**
	 * Gets all active users and puts them in the JList to show on the frame.
	 * 
	 * @param users
	 *            Arraylist containing active users
	 * @author Erik Lundov
	 */
	public void setUserList(ArrayList<User> users) {
		userList.setUsers(users);
	}

	/**
	 * Adds a received message to the messageList.
	 * 
	 * @param message
	 *            A received Message object
	 * @author Erik Lundov
	 */
	public void addMessage(Message message) {
		messageList.addMessage(message);
		scrollBar.setValue(scrollBar.getMaximum());
		repaint();
	}

	/**
	 * @return Returns the text that is written in the textfield.
	 * @author Erik Lundov
	 */
	public String getText() {
		return tfWrite.getText();
	}

	/**
	 * @return Returns a list of the users that has been selected.
	 * @author Erik Lundov
	 */
	public ArrayList<User> getSelectedActiveUsers() {

		return userList.getSelectedUsers();
	}

	/**
	 * Opens a <code>FileChooser</code> and lets the user upload an image. The image
	 * is then rescaled to 100x100 pixels.
	 * 
	 * @author Erik Lundov
	 */
	public void uploadImage() {
		if (fileChooser.showOpenDialog(btnUploadImage) == JFileChooser.APPROVE_OPTION)
			;

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
		if (fileChooser.getSelectedFile() != null) {
			image = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
			// Resize image to 100x100
			img = image.getImage();
			Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(newimg);
		}
	}

	/**
	 * @return Returns the image that has been uploaded.
	 * @author Erik Lundov
	 */
	public ImageIcon getImage() {
		return image;
	}

	/**
	 * Sets the local variable <code>image</code> to null
	 * 
	 * @author Erik Lundov
	 */
	public void eraseImage() {
		image = null;
	}

	public void addListeners() {
		btnSendMessage.addActionListener(this);
		btnUploadImage.addActionListener(this);
	}

	public void setController(ClientController controller) {
		this.controller = controller;
	}

	public void readInContacts() {
		contactList.clearList();
		contactList.setUsers(new ArrayList<User>(contacts.getList()));
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSendMessage) {
			ArrayList<User> receivers = getSelectedActiveUsers();
			for (User user : contacts.getList()) {
				if (receivers.contains(user)) {
					continue;
				}
				receivers.add(user);
			}
			controller.newMessage(receivers);
			tfWrite.clear();
			repaint();
		}
		if (e.getSource() == btnUploadImage) {
			uploadImage();
		}
		if (e.getSource() instanceof JMenuItem) {
			ArrayList<User> selectedUsers = userList.getSelectedUsers();
			User temp = null;
			for (User u : selectedUsers) {
				if (controller.compareUser(u)) {
					temp = u;
				}
			}
			selectedUsers.remove(temp);
			contacts.addContact(selectedUsers);
			readInContacts();
		}
	}

	/**
	 * If a user right-clicks on a active user, popup-menu will show the option to
	 * add this user to your list of contacts.
	 * 
	 * @author Erik Lundow
	 * 
	 */
	private class popupListener extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}

}
