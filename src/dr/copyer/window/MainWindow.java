package dr.copyer.window;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JTextArea;
import javax.swing.JList;

public class MainWindow {

	private JFrame frame;

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

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 414, 205);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("目標檔案");
		lblNewLabel.setBounds(206, 82, 119, 15);
		frame.getContentPane().add(lblNewLabel);
		
		JFileChooser fileC = new JFileChooser();
//		fileC.;

		JButton btnNewButton = new JButton("選擇檔案");
		btnNewButton.setBounds(114, 78, 85, 23);
		frame.getContentPane().add(btnNewButton);
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(65, 35, 196, 23);
		comboBox.addItem("無");
		comboBox.addItem("新增群組");
		comboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().toString().equals("新增群組"))
					new GroupCreate();
			}
		});
		frame.getContentPane().add(comboBox);
//		comboBox.getit
		
		JButton btnNewButton_1 = new JButton("執行");
		btnNewButton_1.setBounds(155, 111, 85, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.setBounds(271, 35, 67, 23);
		frame.getContentPane().add(btnNewButton_2);
		
	}
}
