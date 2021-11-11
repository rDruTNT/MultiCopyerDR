package dr.copyer;

import java.io.File;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dr.copyer.window.MainWindow;

public class MultiCopyer {
	File target;
	public static Database database;
	public static MainWindow mainWindow;
	public static void main(String[] args) {
		database = Database.load();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("unable to set fitting apperance style");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mainWindow = new MainWindow();
		
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
		    @Override
		    public void run()
		    {
				MultiCopyer.database.save();
		    }
		});
	}
	
}