package client;

import java.awt.EventQueue;


import javax.swing.ImageIcon;


import chat.User;

public class ClientMain {

	public static void main(String[] args) {
//Test--------------------------------------
		ImageIcon icon = new ImageIcon("images/SmallMadeline.png");
		User user1 = new User("Birger", icon);
		User user2 = new User("Stefan", icon);
		User me = new User("Erik",icon);
//------------------------------------------
		 
		ClientViewer frame = new ClientViewer();
		ClientController controller = new ClientController(frame);
//		TestClient client = new TestClient(controller);
		UserInput userInput= new UserInput(controller);
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					controller.setUser(me);
					
					frame.setController(controller);
					frame.addUserToList(user1);
					frame.addUserToList(user2);
					frame.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

		

