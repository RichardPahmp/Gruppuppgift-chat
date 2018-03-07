package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class TextField extends JTextField {
	private boolean selected = false;
	
	public TextField(String string) {
		super(string);
		addMouseListener(new MouseListener());
		addKeyListener(new KeyListener());
	}
	
	private class MouseListener extends MouseAdapter{
		public void mouseClicked(MouseEvent e) {
			if(!selected) {
				setText("");
				selected = true;
			}
		}
	}
	
	private class KeyListener extends KeyAdapter{
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode() == 10) {
				setText("");
			}
		}
	}
	
	public static void main(String[] args) {
		TextField test = new TextField("Skriv här...");
		JFrame window = new JFrame();
		window.add(test);
		window.pack();
		window.setVisible(true);
	}
}
