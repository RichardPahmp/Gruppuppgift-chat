package server;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ServerViewer extends JFrame {

	private JTextArea textLogg = new JTextArea();
	private JScrollPane scrollPanel = new JScrollPane();
	private JTextField tfFromDate;
	private JTextField tfToDate;
	private JButton btnSetDate;

	public ServerViewer(ServerController controller) {

		this.setBounds(700, 100, 350, 500);
		this.setTitle("Serverlogg");
		
		textLogg.setEditable(false);
		scrollPanel.setViewportView(textLogg);
		getContentPane().add(scrollPanel);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.SOUTH);
		panel.setLayout(new GridLayout(0, 3, 0, 0));
		
		tfFromDate = new JTextField();
		tfFromDate.setToolTipText("From MM-dd HH:mm");
		panel.add(tfFromDate);
		tfFromDate.setColumns(10);
		
		tfToDate = new JTextField();
		tfToDate.setToolTipText("To MM-dd HH:mm");
		panel.add(tfToDate);
		tfToDate.setColumns(10);
		
		btnSetDate = new JButton("Set Date");
		btnSetDate.addActionListener(controller);
		panel.add(btnSetDate);
		
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
	}

	public void appendLine(String message) {
		textLogg.append(message + "\n");
	}
	
	/**
	 * Sets the text in the view.
	 * @param list
	 */
	public void setText(ArrayList<String> list) {
		String temp = "";
		for(String str : list) {
			temp += str + "\n";
		}
		textLogg.setText(temp);
	}
	
	/**
	 * returns the text in the fromDate field.
	 * @return
	 */
	public String getFromDate() {
		return tfFromDate.getText();
	}

	/**
	 * return the text in the toDate field.
	 * @return
	 */
	public String getToDate() {
		return tfToDate.getText();
	}
}
