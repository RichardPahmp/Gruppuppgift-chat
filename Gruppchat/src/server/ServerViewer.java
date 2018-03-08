package server;

import javax.swing.JFrame;
import javax.swing.JTextArea;


public class ServerViewer extends JFrame {

	private JTextArea textLogg = new JTextArea();

	public ServerViewer() {

		this.setBounds(100, 100, 350, 500);
		this.setTitle("Serverlogg");
		
		textLogg.setEditable(false);
		this.add(textLogg);
		
		this.setVisible(true);
	}

	public void appendLine(String message) {
		textLogg.append(message + "\n");
	}
		
	
	
	public static void main(String[] args) {
		ServerViewer frame = new ServerViewer();
		frame.setVisible(true);
	}
}
