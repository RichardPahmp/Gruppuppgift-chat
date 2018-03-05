package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import chat.User;

public class ClientViewerBuilder extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> listActiveUsers;
	
	private JTextField textField;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JButton btnSendMessage;
	private JButton btnUploadFile;
	private JLabel lblNewLabel_1;
	private ClientController controller;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ClientController controller = new ClientController();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientViewerBuilder frame = new ClientViewerBuilder(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ClientViewerBuilder(ClientController controller) {
		this.controller=controller;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		listActiveUsers = new DefaultListModel<String>();
		listActiveUsers.addElement("Tjena");
		listActiveUsers.addElement("Vafan");

		JList list = new JList(new AbstractListModel() {
			String[] values = new String[] {"Stefan", "Göran", "Greger", "Fia", "5", "6", "7", "8"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});

		contentPane.add(list, BorderLayout.WEST);

		JList list_1 = new JList(listActiveUsers);
		contentPane.add(list_1, BorderLayout.EAST);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		contentPane.add(textField, BorderLayout.CENTER);
		textField.setColumns(10);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(0, 2, 0, 0));
		
		lblNewLabel = new JLabel("Active users");
		lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Contacts");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_1);
		
		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		btnUploadFile = new JButton("Upload File");
		panel_1.add(btnUploadFile);
		
		btnSendMessage = new JButton("Send Message");
		panel_1.add(btnSendMessage);

	}

	public void setUserList(ArrayList<User> activeUsers) {
		for (User u : activeUsers) {
			listActiveUsers.addElement(u.getName());
		}

	}
	
	private class Listener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==btnSendMessage) {
				controller.newMessage(textField.getText());
				
			}
			if(e.getSource()==btnUploadFile) {
				
			}
		}
		
	}
	
	

}
