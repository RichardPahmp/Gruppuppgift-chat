package client;

import javax.swing.Icon;

public class ClientController {

	private ClientViewer ui;
	private Icon icon;
	public ClientController(ClientViewer ui){
		this.ui=ui;
		
	}
	public void newMessage() {
		ui.getText();
	}

	public void setUploadedImage() {
		icon=ui.getUploadedImage();
		
	}

}
