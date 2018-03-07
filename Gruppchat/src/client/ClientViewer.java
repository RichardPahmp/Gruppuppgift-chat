package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
	private JScrollPane scrollPanelText = new JScrollPane();
	private JScrollPane scrollPanelUsers = new JScrollPane();

	private JFileChooser fc = new JFileChooser();

	private JLabel lblActiveUsers;
	private JLabel lblNewLabel_1;

	private TextField tfWrite;

	private JButton btnSendMessage;
	private JButton btnUploadImage;

	private UserList userList = new UserList();
	
	private MessageList messageList = new MessageList();
	private DefaultListModel<User> listContacts;
	private ClientController controller;
	private Icon icon;

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

		tfWrite = new TextField("Skriv här..");
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

	public void setContacts(User user) {
		// contactList.addUser(user);
	}

	public String getText() {
		return tfWrite.getText();
	}

	public LinkedList<User> getRecievers() {
		
		return null;
	}

	public Icon getUploadedImage() {

		if (fc.showOpenDialog(btnUploadImage) == JFileChooser.APPROVE_OPTION);

		icon = new ImageIcon (fc.getSelectedFile().getAbsolutePath());;
	return icon;
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
			controller.setUploadedImage();

		}

	}
	
	public void setController(ClientController controller) {
		this.controller=controller;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		ImageIcon icon = new ImageIcon("images/SmallMadeline.png");
		User user1 = new User("Birger", icon);
		User user2 = new User("Stefan", icon);

		ArrayList<User> userList = new ArrayList<User>();
		userList.add(user2);
		TextMessage message1 = new TextMessage(user2, userList, "Hejsan", icon);
		TextMessage message2 = new TextMessage(user1, userList, "Tjena", null);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientViewer frame = new ClientViewer();
					ClientController controller = new ClientController(frame);
					frame.setController(controller);
					frame.addUserToList(user1);
					frame.addUserToList(user2);
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
