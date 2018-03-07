package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

public class UserInput extends JFrame implements ActionListener {
	private ClientController controller;
	private final int WIDTH = 250, HEIGHT = 180;

	private ImageIcon image = new ImageIcon("images/Granny.png");

	private TextField nameField = new TextField("Ange ditt användarnamn...");
	private TextField imageField = new TextField("Sökväg till bild...");

	private JFileChooser imageChooser = new JFileChooser();

	private JButton fileButton = new JButton("Upload file");
	private JButton doneButton = new JButton("Klar");

	public UserInput(ClientController controller) {

		this.controller = controller;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setResizable(false);
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Dimension screen = toolkit.getScreenSize();
		setLocation((int) (screen.getWidth() / 2) - (WIDTH / 2), ((int) screen.getHeight() / 2) - (HEIGHT / 2));

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

		mainPanel.add(new JLabel("Namn: "));
		mainPanel.add(nameField);

		mainPanel.add(new JLabel("Bild: "));
		imageField.setFocusable(false);
		mainPanel.add(imageField);

		mainPanel.add(fileButton);
		fileButton.addActionListener(this);

		mainPanel.add(new JPanel());
		mainPanel.add(doneButton);
		doneButton.addActionListener(this);
		
		add(mainPanel);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == fileButton) {
			if (imageChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
				;
			imageChooser.addChoosableFileFilter(new FileNameExtensionFilter("jpg", "png", "gif", "bmp"));
			if (imageChooser.getSelectedFile() != null) {
				String path = imageChooser.getSelectedFile().getAbsolutePath();
				Image tempImage = new ImageIcon(path).getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
				image = new ImageIcon(tempImage);
				imageField.setForeground(Color.BLACK);
				imageField.setText(path);
			}

		} else if (e.getSource() == doneButton) {
			String name;
			if(nameField.getText().length() > 0) {
				name = nameField.getText();
			} else {
				name = "Gäst";
			}
			nameField.clear();
			controller.setUser(name, image);
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
