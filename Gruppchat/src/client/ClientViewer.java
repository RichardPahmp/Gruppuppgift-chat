package client;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import chat.Contacts;
import chat.Message;
import chat.TextMessage;
import chat.User;

public class ClientViewer extends JFrame implements ActionListener {

	private JPanel pnlContent;
	private JPanel pnlUsers;
	private JPanel pnlText;
	private JPanel pnlButtons;
	private JScrollPane scrollPanelText = new JScrollPane();
	private JScrollPane scrollPanelUsers = new JScrollPane();

	private JFileChooser fileChooser = new JFileChooser();

	private JLabel lblActiveUsers;
	private JLabel lblContacts;
	private JLabel lblImagePath;

	private TextField tfWrite;

	private JButton btnSendMessage;
	private JButton btnUploadImage;
	//TODO
	private JButton btnAddToContacts;
	private JButton editUser;

	private UserList userList = new UserList(new popupListener());
	
	private MessageList messageList = new MessageList();
	//TODO
	private DefaultListModel<User> listContacts;

	private ClientController controller;
	private ImageIcon image;
	private Image img;
	
	private JPopupMenu popupMenu;
	
	private Contacts contacts;

	/**
	 * Create the frame.
	 */
	public ClientViewer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlContent.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlContent);
		scrollPanelUsers.setViewportView(userList);
		pnlContent.add(scrollPanelUsers, BorderLayout.WEST);

		tfWrite = new TextField("Skriv ditt meddelande här...");
		tfWrite.setHorizontalAlignment(SwingConstants.LEFT);

		pnlText = new JPanel();
		pnlText.setLayout(new BorderLayout(0, 0));
		scrollPanelText.setViewportView(messageList);
		pnlText.add(tfWrite, BorderLayout.NORTH);
		pnlText.add(scrollPanelText, BorderLayout.CENTER);

		pnlContent.add(pnlText, BorderLayout.CENTER);
		tfWrite.setColumns(10);

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

		pnlButtons = new JPanel();
		pnlContent.add(pnlButtons, BorderLayout.SOUTH);

		btnUploadImage = new JButton("Upload File");
		pnlButtons.add(btnUploadImage);

		btnSendMessage = new JButton("Send Message");
		pnlButtons.add(btnSendMessage);
		addListeners();
		pnlButtons.getRootPane().setDefaultButton(btnSendMessage);

		popupMenu = new JPopupMenu();
		JMenuItem menuItem = new JMenuItem("Add Contact");
		menuItem.addActionListener(this);
		popupMenu.add(menuItem);
		
		contacts = new Contacts();
	}

	public void addUser(User user) {
		userList.addUser(user);
	}
	
	public void setUserList(ArrayList<User> users){
		userList.setUsers(users);
	}

	public void addMessage(Message message) {
		messageList.addMessage(message);
	}

	public String getText() {
		return tfWrite.getText();
	}

	public ArrayList<User> getSelectedActiveUsers() {
		return userList.getSelectedUsers();
	}

	public void uploadImage() {
		if (fileChooser.showOpenDialog(btnUploadImage) == JFileChooser.APPROVE_OPTION);

		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg", "png", "gif", "bmp"));
		if (fileChooser.getSelectedFile() != null) {
			image = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());
			//Resize image to 100x100
			img = image.getImage();
			Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
			image = new ImageIcon(newimg);
		}
	}

	public ImageIcon getImage() {
		return image;
	}

	public void eraseImage() {
		image = null;
	}

	public void addListeners() {
		btnSendMessage.addActionListener(this);
		btnUploadImage.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSendMessage) {
			controller.newMessage();
			tfWrite.clear();
		}
		if (e.getSource() == btnUploadImage) {
			uploadImage();
		}
		if(e.getSource() instanceof JMenuItem){
			contacts.addContact(userList.getSelectedUsers());
			System.out.println("Uppdatera kontakterna för fan");
		}
	}

	public void setController(ClientController controller) {
		this.controller = controller;
	}
	
//	private class popupMenu extends JPopupMenu{
//		JMenuItem menuItem;
//		
//		public popupMenu(){
//			menuItem = new JMenuItem("Add contact.");
//			add(menuItem);
//		}
//	}
	
	private class popupListener extends MouseAdapter{
		public void mousePressed(MouseEvent e){
			if(e.getButton() == MouseEvent.BUTTON3){
				popupMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}
	}
	
}
