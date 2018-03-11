package client;

import java.awt.EventQueue;


import javax.swing.ImageIcon;


import chat.User;

public class ClientMain {

	
	public static void main(String[] args) {
		 
		ClientViewer frame = new ClientViewer();
		ClientController controller = new ClientController(frame);
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					frame.setController(controller);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

		

