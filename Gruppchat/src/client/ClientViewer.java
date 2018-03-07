package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import chat.TextMessage;
import chat.User;

public class ClientViewer extends JFrame implements ActionListener {

	private JPanel pnlContent;
	private JPanel pnlUsers;
	private JPanel pnlText;
	private JPanel pnlButtons;
	
	private JScrollPane scrollPanelText=new JScrollPane();
	private JScrollPane scrollPanelUsers = new JScrollPane();
	private JScrollPane scrollPanelContacts = new JScrollPane();
	
	private JFileChooser fileChooser = new JFileChooser();

	private JLabel lblActiveUsers;
	private JLabel lblNewLabel_1;

	private TextField tfWrite = new TextField("Skriv ditt meddelande här...");

	private JButton btnSendMessage;
	private JButton btnUploadImage;

	private UserList userList = new UserList();
	private MessageList messageList = new MessageList();
	
	private DefaultListModel<User> contactsListModel = new DefaultListModel<User>();
	private JList<User> contactsList = new JList<User>();

	private ClientController controller;

	/**
	 * Create the frame.
	 */
	public ClientViewer(ClientController controller) {
		this.controller = controller;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		pnlContent = new JPanel();
		pnlContent.setBorder(new EmptyBorder(5, 5, 5, 5));
		pnlContent.setLayout(new BorderLayout(0, 0));
		setContentPane(pnlContent);
		
		scrollPanelUsers.setViewportView(userList);
		pnlContent.add(scrollPanelUsers, BorderLayout.WEST);

		scrollPanelContacts.setViewportView(contactsList);
		contactsList.setModel(contactsListModel);
		pnlContent.add(scrollPanelContacts, BorderLayout.EAST);
		
		tfWrite.setHorizontalAlignment(SwingConstants.LEFT);
		
		pnlText= new JPanel();
		pnlText.setLayout(new BorderLayout(0,0));
		scrollPanelText.setViewportView(messageList);
		pnlText.add(tfWrite, BorderLayout.NORTH);
		pnlText.add(scrollPanelText, BorderLayout.CENTER);
		
		pnlContent.add(pnlText,BorderLayout.CENTER);
		tfWrite.setColumns(10);
		
		pnlUsers = new JPanel();
		pnlContent.add(pnlUsers, BorderLayout.NORTH);
		pnlUsers.setLayout(new GridLayout(0, 2, 0, 0));

		lblActiveUsers = new JLabel("Active users");
		lblActiveUsers.setHorizontalAlignment(SwingConstants.LEFT);
		lblActiveUsers.setVerticalAlignment(SwingConstants.TOP);
		pnlUsers.add(lblActiveUsers);

		lblNewLabel_1 = new JLabel("Contacts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		pnlUsers.add(lblNewLabel_1);

		pnlButtons = new JPanel();
		pnlContent.add(pnlButtons, BorderLayout.SOUTH);

		btnUploadImage = new JButton("Upload File");
		pnlButtons.add(btnUploadImage);

		btnSendMessage = new JButton("Send Message");
		pnlButtons.add(btnSendMessage);
		addListeners();
	}

	public void addUserToList(User user) {
		userList.addUser(user);

	}
	
	public void addMessageToList(TextMessage message) {
		messageList.addMessage(message);
	}

	public void addContactToList(User user) {
		contactsListModel.addElement(user);
	}
	

	public String getText() {
		return tfWrite.getText();
	}
	
	public ArrayList<User> getRecievers() {
		return null;

	}

	public void addListeners() {
		btnSendMessage.addActionListener(this);
		btnUploadImage.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSendMessage) {
			controller.newMessage();
		}
		if (e.getSource() == btnUploadImage) {
			controller.uploadImage();			
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientController controller = new ClientController();
		ImageIcon icon1 = new ImageIcon("images/SmallMadeline.png");
		ImageIcon icon2 = new ImageIcon("images/Granny.png");
		User user1 = new User("Birger", icon2);
		User user2 = new User("Stefan", icon1);
		
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user2);
		TextMessage message1 = new TextMessage(user2,userList,"Hejsan",icon1);
		TextMessage message2 = new TextMessage(user1,userList,"Tjena",null);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientViewer frame = new ClientViewer(controller);
					frame.addUserToList(user1);
					frame.addUserToList(user2);
					frame.addContactToList(user2);
					frame.setVisible(true);
					frame.addMessageToList(message1);
					frame.addMessageToList(message2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
