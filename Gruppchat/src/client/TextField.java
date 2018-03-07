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
		return super.getText();
	}
	
	public void clear() {
		setText("");
	}

	private class FocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			if (getForeground() == Color.LIGHT_GRAY) {
				setText("");
				setForeground(Color.BLACK);
			}
		}

		public void focusLost(FocusEvent e) {
			if (getForeground() != Color.BLACK || getText().length() == 0) {
				setForeground(Color.LIGHT_GRAY);
				setText(text);
			}
		}
	}
}
