package dr.copyer;

import java.util.ArrayList;

public class DirGroup {
	String name;
	public ArrayList<String> dirs = new ArrayList<>();
	
	public DirGroup(String name) {
		// TODO Auto-generated constructor stub
	this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
