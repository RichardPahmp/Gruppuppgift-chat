package chat;

import java.io.Serializable;

import javax.swing.ImageIcon;

public class User implements Serializable {
	private String name;
	private ImageIcon icon;
	
	public User(String name, ImageIcon icon) {
		this.name = name;
		this.icon = icon;
	}
	
	public String getName() {
		return name;
	}
	
	public ImageIcon getImage() {
		return icon;
	}
	
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof User){
			return name.equals(((User) obj).getName());
		}
		return false;
	}
	
	public String toString() {
		return name;
	}
	
}
