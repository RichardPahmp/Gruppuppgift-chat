package server;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class ServerViewer extends JFrame {

	private JTextArea textLogg = new JTextArea();
	private JScrollPane scrollPanel = new JScrollPane();

	public ServerViewer() {

		this.setBounds(700, 100, 350, 500);
		this.setTitle("Serverlogg");
		
		textLogg.setEditable(false);
		scrollPanel.setViewportView(textLogg);
		this.add(scrollPanel);
		
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
