package dr.copyer.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dr.copyer.DirGroup;
import dr.copyer.MultiCopyer;
import dr.copyer.utils.FileChooserRD;
import dr.copyer.utils.FileChooserRD.AcceptType;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.awt.event.ItemEvent;

public class GroupModifier extends FileDragableFrame{

	private JPanel contentPane;
	private JTextField textField;
	private JPanel mainPanel;
	private JComboBox comboBox;
	private DirGroup group;
	private String selectDir;	
//	public void setTarget(DirGroup group) {
//		this.group = group;
//		textField.setText(group.getName());
//	}

	public GroupModifier(DirGroup group) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.group = group;
		setVisible(true);
		setTitle("目的地資料群組調整");
		setBounds(100, 100, 372, 240);
		contentPane = new JPanel();
		mainPanel = contentPane;
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		setDefaultCloseOperation(JFrame.);
		textField = new JTextField(group.getName());
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("great");
				group.rename(textField.getText());
			}
		});
		textField.setToolTipText("群組ID");
		textField.setBounds(130, 36, 96, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("群組ID:");
		lblNewLabel.setBounds(86, 39, 46, 15);
		contentPane.add(lblNewLabel);
		
		JFrame frame = this;
		comboBox = new JComboBox();
		
		comboBox.setBounds(45, 89, 275, 23);
		refreahDirs();
		contentPane.add(comboBox);
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED&&!e.getItem().toString().equals("無"))
					if(e.getItem().toString().equals("新增資料夾"))
						addDir();
					else 
						selectDir = e.getItem().toString();
			}
		});
		
		JButton btnNewButton = new JButton("移除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectDir!=null) {
					group.dirs.remove(selectDir);
					selectDir = null;
					refreahDirs();
				} else if(group.dirs.size()==0) {
					MultiCopyer.database.removeGroup(group);
					close();
				}
			}
		});
		btnNewButton.setBounds(140, 148, 85, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("新增");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addDir();
			}
		});
		btnNewButton_1.setBounds(45, 148, 85, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("返回");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton_2.setBounds(235, 148, 85, 23);
		contentPane.add(btnNewButton_2);
	
	}
	
	public void refreahDirs() {
		comboBox.removeAllItems();
		comboBox.addItem("無");
		for(String dir : group.dirs) {
			comboBox.addItem(dir);
		}
		comboBox.addItem("新增資料夾");
		
	}
	
	public void addDir() {
		File f = FileChooserRD.open(AcceptType.Directory, this, "選擇");
		if(f!=null) 
			group.addDir(f.getPath());
		refreahDirs();
	}
	
	public void close() {
		setVisible(false);
		removeAll();
		try {
			finalize();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.gc();
	}

	@Override
	public void onDropped(File f) {
		// TODO Auto-generated method stub
		if(!f.isDirectory()) return;
		group.dirs.add(f.getPath());
		refreahDirs();
	}
}
