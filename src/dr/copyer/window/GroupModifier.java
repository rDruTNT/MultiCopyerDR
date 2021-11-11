package dr.copyer.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dr.copyer.DirGroup;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GroupModifier extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	DirGroup group;
	
	public void setTarget(DirGroup group) {
		this.group = group;
		textField.setText(group.getName());
	}

	public GroupModifier() {
		setVisible(true);
		setTitle("目的地資料群組調整");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 372, 240);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setToolTipText("群組ID");
		textField.setBounds(130, 36, 96, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("群組ID:");
		lblNewLabel.setBounds(86, 39, 46, 15);
		contentPane.add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(45, 89, 275, 23);
		contentPane.add(comboBox);
		
		JButton btnNewButton = new JButton("移除");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(140, 148, 85, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("新增");
		btnNewButton_1.setBounds(45, 148, 85, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("保存");
		btnNewButton_2.setBounds(235, 148, 85, 23);
		contentPane.add(btnNewButton_2);
	
	}
	
}
