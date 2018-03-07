package client;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.KeyAdapter;

import javax.swing.JButton;

import chat.User;

public class UserInput extends JFrame {
	private JPanel mainPanel = new JPanel();
	
	private TextField nameField = new TextField("Ange ditt användarnamn...");
	
	private JTextField imageField = new JTextField();
	private JFileChooser fileChooser = new JFileChooser();

	private JButton JButton = new JButton("Klar");
	private User user;
	
	public UserInput(ClientController controller) {
		GridLayout gl1 = new GridLayout(1, 3);
		mainPanel.setLayout(gl1);
		add(mainPanel);
		mainPanel.add(nameField);
	}
	
	private class KeyListener extends KeyAdapter{
		
	}
	
	public static void main(String[] args) {

	}
}
