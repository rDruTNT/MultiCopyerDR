package dr.copyer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;

public class Database implements Serializable{

	private HashMap<String, DirGroup> groups = new HashMap<>();
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Database() {
		
	}
	public DirGroup getDirGroup(String id) {
		return groups.get(id);
	}
	
	public Collection<DirGroup> getDirGroups() {
		return groups.values();
	}
	
	public void addGroup(DirGroup group) {
		groups.put(group.getName(), group);
		MultiCopyer.mainWindow.refreshDirs();
	}
	
	public void removeGroup(String id) {
		groups.remove(id);
		MultiCopyer.mainWindow.refreshDirs();	
	}
	
	public void removeGroup(DirGroup group) {
		removeGroup(group.getName());
	}
	
	public static Database load() {
		Database db = new Database();
		try
		{
			File f = new File("MultiCopyer/Database.dat");
			if(!f.exists()) {
				new File("MultiCopyer").mkdirs();
				return db;
			}

			FileInputStream fileIn = new FileInputStream(f);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			db = (Database) in.readObject();
			in.close();
			fileIn.close();
		}catch(IOException | ClassNotFoundException i)
		{
			System.out.println("error occure while loading data.");
			i.printStackTrace();
		}
		return db;
	}
	
	public void save() {
		try
		{
			File f = new File("MultiCopyer/Database.dat");
			if(!f.exists()) {
				f.createNewFile();
			}

			FileOutputStream fileOut = new FileOutputStream(f);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		}catch(IOException i)
		{
			System.out.println("error occure while savinig data.");
			i.printStackTrace();
		}
	}
}
