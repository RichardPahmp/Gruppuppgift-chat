
package chat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class Contacts {
	private LinkedList<User> list = new LinkedList<User>();

	public Contacts() {
		
	}
	
	public LinkedList<User> getList() {
		return list;
	}
	
	public void addContact(User user) {
		if(!list.contains(user)) {
		list.add(user);
		}
	}
	
	public void addContact(ArrayList<User> users){
		for(User user : users){
			addContact(user);
		}
	}
	
	public void writeFile() {
		try {
			FileOutputStream fos = new FileOutputStream("contacts.ser, true");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(list);
			oos.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public LinkedList<User> readFile() {
		try {
			FileInputStream fis = new FileInputStream("contacts.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);			
			LinkedList<User> readList = (LinkedList<User>)ois.readObject();
			ois.close();
			
			return readList;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
