package client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserInput extends JFrame {
	private final int width = 300, height = 200;

	private TextField nameField = new TextField("Ange ditt användarnamn...");
	private TextField imageField = new TextField("Sökväg till bild...");

	private JFileChooser imageChooser = new JFileChooser();

	private JButton fileButton = new JButton("Upload file");
	private JButton doneButton = new JButton("Klar");

	public UserInput() {

		setTitle("New User");
		setPreferredSize(new Dimension(width, height));
		setResizable(false);

		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();

		setLocation((int) (screen.getWidth() / 2) - (width / 2), ((int) screen.getHeight() / 2) - (height / 2));

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

	public static void main(String[] args) {
		UserInput test = new UserInput();
		test.pack();
		test.setVisible(true);
	}
}
