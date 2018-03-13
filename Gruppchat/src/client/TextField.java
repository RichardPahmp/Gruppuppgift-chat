package client;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JTextField;

/**
 * Extends JTextField. Enables grayed out text when the TextField is empty or
 * not focused.
 */
public class TextField extends JTextField {
	private String text;

	/**
	 * Constructor for the TextField, initializes the component with grayed out
	 * text.
	 * 
	 * @param text
	 *            Default text
	 */
	public TextField(String text) {
		super(text);
		this.text = text;
		setForeground(Color.LIGHT_GRAY);
		addFocusListener(new FocusListener());
	}

	/**
	 * Returns the text if the user has entered any. Otherwise returns an empty
	 * String.
	 */
	public String getText() {
		if (getForeground() != Color.LIGHT_GRAY) {
			return super.getText();
		} else
			return "";
	}

	/**
	 * Clears text in the TextField.
	 */
	public void clear() {
		setText("");
	}

	/**
	 * Private class to check if the component is focused by the user or not.
	 */
	private class FocusListener extends FocusAdapter {

		/**
		 * Clear the grayed out text from the TextField when the component gains focus.
		 */
		public void focusGained(FocusEvent e) {
			if (getForeground() == Color.LIGHT_GRAY) {
				setForeground(Color.BLACK);
				clear();
			}
		}
		
		/**
		 * Sets the text back to the grayed out text if the TextField is empty
		 */
		public void focusLost(FocusEvent e) {
			if (getText().length() == 0) {
				setForeground(Color.LIGHT_GRAY);
				setText(text);
			}
		}
	}
}
