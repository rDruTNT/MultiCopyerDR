package dr.copyer.window;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.SwingConstants;

import dr.copyer.DirGroup;
import dr.copyer.MultiCopyer;
import dr.copyer.utils.FileChooserRD;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow {

	private FileDragableFrame frame;
	private File targetFile;
	private DirGroup selectGroup;
	private JComboBox<String> comboBox = new JComboBox<String>();
	private JLabel targetInfo;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//
//	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		this.frame.setVisible(true);
	}
	


	public File getTargetFile() {
		return targetFile;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new FileDragableFrame() {
			@Override
			public void onDropped(File f) {
				if(!f.isFile()) return;
				targetFile = f;
	        	refreshTargetName();
			}
		
		};
		frame.setTitle("小鉛筆的檔案複製器");
		frame.setBounds(100, 100, 414, 205);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		targetInfo = new JLabel("<html><font color='red'>請選擇目標檔案</font></html>");
		targetInfo.setVerticalAlignment(SwingConstants.TOP);
		targetInfo.setBounds(209, 78, 182, 30);
		frame.getContentPane().add(targetInfo);
//		JFileChooser fileC = new JFileChooser();
//		fileC.;
		JButton btnNewButton = new JButton("選擇檔案");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Create a file chooser
				targetFile = FileChooserRD.open(frame);
		        if(targetFile!=null) {
		        	refreshTargetName();
		        } else
		        	System.out.println("Open command cancelled by user.");
			}
		});
		btnNewButton.setBounds(114, 78, 85, 23);
		frame.getContentPane().add(btnNewButton);
		
		comboBox.setBounds(65, 35, 196, 23);

		refreshDirs();
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange()==ItemEvent.SELECTED&&!e.getItem().toString().equals("無")) {
					System.out.println(e.getItem().toString());
					if(e.getItem().equals("新增群組")) {

						comboBox.setSelectedItem("無");
						GroupCreate.call();	
					}
					else 
						selectGroup = MultiCopyer.database.getDirGroup(e.getItem().toString());
				}
			}
		});
		frame.getContentPane().add(comboBox);
//		comboBox.getit
		
		JButton btnNewButton_1 = new JButton("執行");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(targetFile==null||selectGroup==null) return;
				 selectGroup.dirs.parallelStream().forEach(s->{
					 File to = new File(s+"/"+targetFile.getName());
					 if(to.exists())
						 to.delete();
					 try {
						Files.copy(targetFile.toPath(), to.toPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 });
				 JOptionPane.showMessageDialog(null, "複製成功！");
			}
		});
		btnNewButton_1.setBounds(155, 111, 85, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectGroup!=null)
					new GroupModifier(selectGroup);
			}
		});
		btnNewButton_2.setBounds(271, 35, 67, 23);
		frame.getContentPane().add(btnNewButton_2);
		
	}
	
	public void refreshDirs() {
		comboBox.removeAllItems();
		comboBox.addItem("無");
		for(DirGroup dir :  MultiCopyer.database.getDirGroups()) {
			comboBox.addItem(dir.getName());
		}
		comboBox.addItem("新增群組");
		if(targetFile!=null)
			comboBox.setSelectedItem(targetFile);
	}
	
	public void refreshTargetName() {
		targetInfo.setText("<html>目標檔案: <font color='green'>"+targetFile.getName()+"</font></html>");
	}
	

}
