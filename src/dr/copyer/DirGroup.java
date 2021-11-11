package dr.copyer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DirGroup implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	public ArrayList<String> dirs = new ArrayList<>();

	public DirGroup(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
		MultiCopyer.database.addGroup(this);
		MultiCopyer.mainWindow.refreshDirs();
	}
	
	public String getName() {
		return name;
	}
	
	public void rename(String name) {
		
		MultiCopyer.database.removeGroup(this);
		this.name = name;

		MultiCopyer.database.addGroup(this);
	}
	
	public void addDir(String dir) {
		dirs.add(dir);
	}
	
	public boolean removeDir(String dir) {
		return dirs.remove(dir);
	}
	
	public List<String> getDirs() {
		return dirs;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
}
