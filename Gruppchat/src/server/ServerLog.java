package server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

import chat.User;

public class ServerLog {
	
	private ArrayList<LogPair> list;
	
	public ServerLog() {
		list = new ArrayList<LogPair>();
		loadFromFile();
	}
	
	public void add(String text) {
		LogPair pair = new LogPair(text, new Date());
		list.add(pair);
	}
	
	public void writeToFile() {
		try {
			FileOutputStream fos = new FileOutputStream("serverlog.ser", false);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	private void loadFromFile() {
		try {
			FileInputStream fis = new FileInputStream("serverlog.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);			
			this.list = (ArrayList<LogPair>)ois.readObject();
			ois.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getFormattedList() {
		ArrayList<String> temp = new ArrayList<String>();
		SimpleDateFormat parser = new SimpleDateFormat("MM-dd HH:mm:ss");
		for(LogPair pair : list) {
			temp.add("[" + parser.format(pair.getTime()) + "] " + pair.getText());
		}
		return temp;
	}
	
	public ArrayList<String> getFormattedList(Date from, Date to){
		SimpleDateFormat parser = new SimpleDateFormat("MM-dd HH:mm:ss");
		ArrayList<String> temp = new ArrayList<String>();
		for(LogPair pair : list) {
			if(pair.getTime().compareTo(from) >= 0 && pair.getTime().compareTo(to) <= 0) {
				temp.add("[" + parser.format(pair.getTime()) + "] " + pair.getText());
			}
		}
		return temp;
	}
	
	
	
}
