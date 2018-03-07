package client;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chat.User;

public class UserInput extends JFrame implements ActionListener{
	private final int WIDTH = 300, HEIGHT = 200;
	
	private User newUser;

	private TextField nameField = new TextField("Ange ditt användarnamn...");
	private TextField imageField = new TextField("Sökväg till bild...");

	private JFileChooser imageChooser = new JFileChooser();

	private JButton fileButton = new JButton("Upload file");
	private JButton doneButton = new JButton("Klar");


	public UserInput(ClientController controller) {
		

		setTitle("New User");
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();

		setLocation((int) (screen.getWidth() / 2) - (WIDTH / 2), ((int) screen.getHeight() / 2) - (HEIGHT / 2));

		JPanel mainPanel = new JPanel();
		GridLayout gl1 = new GridLayout(7, 1);
		mainPanel.setLayout(gl1);

		mainPanel.add(new JLabel("Namn: "));
		mainPanel.add(nameField);

		mainPanel.add(new JLabel("Bild: "));
		imageField.setFocusable(false);
		mainPanel.add(imageField);

		mainPanel.add(fileButton);

		mainPanel.add(new JPanel());
		mainPanel.add(doneButton);
		add(mainPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == fileButton) {
			
		} else if (e.getSource() == doneButton) {
			
		}
	}
	
	private class KeyListener extends KeyAdapter{
		public void keyReleased(KeyEvent e) {
			if(e.getKeyCode() == 10) {
				
			}
		}
	}
	
	public static void main(String[] args) {
		ClientViewer viewer = new ClientViewer();
		ClientController controller = new ClientController(viewer);
		UserInput test = new UserInput(controller);
		test.pack();
		test.setVisible(true);
	}
}
