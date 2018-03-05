package server;

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

public class ServerViewer extends JFrame {

	private JPanel contentPane;
	private DefaultListModel<String> listModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerViewer frame = new ServerViewer();
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
	public ServerViewer() {
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
			String[] values = new String[] { "1", "2", "3", "4", "5", "6", "7", "8" };

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

	}

	public void setUserList(ArrayList<User> activeUsers) {
		for (User u : activeUsers) {
			listModel.addElement(u.getName());

		}

	}

}
