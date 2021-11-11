package dr.copyer.utils;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * 
 * @author Dru_TNT
 * created on 2021/11/11
 */
public class FileChooserRD {
	
	
	public enum AcceptType {
		SingleFile,
		MultiFile,
		Directory
	}
	/**
	 * open by constrator, only accpet single/directory file select
	 * @param parent the parent of Frame
	 * @param title the title of the Chooser
	 * @param onDone execute on done
	 * @param onCancel execute on cancel
	 */
	public FileChooserRD(AcceptType type, JFrame parent, String title, Consumer<File> onDone, Runnable onCancel) {
		final JFileChooser fc = new JFileChooser();
		switch(type) {
		case MultiFile:
			fc.setMultiSelectionEnabled(true);
			break;
		case Directory:
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			break;
		default:
			break;
		}
		
		//In response to a button click:
		int returnVal = fc.showDialog(parent, title);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			onDone.accept(fc.getSelectedFile());
		} else {
        	if(onCancel!=null)
        		onCancel.run();
        }
	}
		
		/**
		 * open by constrator, only accpet multi file select
		 * @param parent the parent of Frame
		 * @param title the title of the Chooser
		 * @param onDone execute on done
		 * @param onCancel execute on cancel
		 */
	public FileChooserRD(JFrame parent, String title, Consumer<Collection<File>> onDone, Runnable onCancel) {
		final JFileChooser fc = new JFileChooser();
		//In response to a button click:
		int returnVal = fc.showDialog(parent, title);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
				onDone.accept(Arrays.asList(fc.getSelectedFiles()));
		} else {
        	if(onCancel!=null)
        		onCancel.run();
        }
	}
	
	/**
	 * open by method, not accept multi file select
	 * @return the file that get select, return null if cancelled.
	 */
	public static File open(AcceptType type, JFrame parent, String title) {
		final JFileChooser fc = new JFileChooser();
		//In response to a button click:
		switch(type) {
		case MultiFile:
			fc.setMultiSelectionEnabled(true);
			break;
		case Directory:
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			break;
		default:
			break;
		}
		int returnVal = fc.showDialog(parent, title);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			return null;
        }
	}
	
	/**
	 * simply open by method, with single file only
	 * @return the file that got select, return null if cancelled.
	 */
	public static File open(JFrame parent) {
		return open(AcceptType.SingleFile, parent, "選擇");
	}
	
	/**
	 * open by method, with multi file only
	 * @return the file that got select, return null if cancelled.
	 */
	public static Collection<File> open(JFrame parent, String title) {
		final JFileChooser fc = new JFileChooser();
		//In response to a button click:
		
		int returnVal = fc.showDialog(parent, title);
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return Arrays.asList(fc.getSelectedFiles());
		} else {
			return null;
        }
	}
	
	
}
