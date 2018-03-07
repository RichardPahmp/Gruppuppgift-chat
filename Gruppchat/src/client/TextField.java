package client;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

public class TextField extends JTextField {
	private boolean selected = false;
	private String text;
	
	public TextField(String text) {
		super(text);
		this.text = text;
		setForeground(Color.LIGHT_GRAY);
		addFocusListener(new FocusListener());
	}
	
	public String getText() {
		String res = null;
		if(selected) {
			res = super.getText();
			setText("");
		}
		return res;
	}
	
	private class FocusListener extends FocusAdapter{
		public void focusGained(FocusEvent e) {
			if(!selected) {
				setText("");
			}
		}
		public void focusLost(FocusEvent e) { 
			if(getText() == null) {
				setText(text);
			}
		}
	}
}
