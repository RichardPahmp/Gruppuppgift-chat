package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.User;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.JButton;

public class ClientViewerBuilder extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> listModel;
	private JTextField textField;
	private JPanel panel;
	private JLabel lblNewLabel;
	private JPanel panel_1;
	private JButton btnNewButton;
	private JButton btnUploadFile;
	private JLabel lblNewLabel_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientViewerBuilder frame = new ClientViewerBuilder();
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
	public ClientViewerBuilder() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		listModel = new DefaultListModel<String>();
		listModel.addElement("Tjena");
		listModel.addElement("Vafan");

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

		JList list_1 = new JList(listModel);
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
		
		btnNewButton = new JButton("Send Message");
		panel_1.add(btnNewButton);

	}

	public void setUserList(ArrayList<User> activeUsers) {
		for (User u : activeUsers) {
			listModel.addElement(u.getName());

		}

	}

}
