package dr.copyer.window;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import dr.copyer.DirGroup;

public class GroupCreate extends JFrame {

	private JPanel contentPane;
	private JTextField txtid;
	/**
	 * Launch the application.
	 */
	public static void call() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GroupCreate frame = new GroupCreate();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public GroupCreate() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 338, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		txtid = new JTextField();
		txtid.setToolTipText("請輸入群組名稱");
		txtid.setBounds(65, 54, 180, 21);
		getContentPane().add(txtid);
		txtid.setColumns(10);
		
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton_1.setBounds(160, 91, 85, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("請輸入群組ID");
		lblNewLabel.setBounds(127, 29, 72, 15);
		contentPane.add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtid.getText().length()>0) {
					DirGroup dir = new DirGroup(txtid.getText());
					new GroupModifier(dir);
					removeAll();
					setVisible(false);
					System.gc();
				} else {
					lblNewLabel.setText(lblNewLabel+"!");
				}
			}
		});
		btnNewButton.setBounds(65, 91, 85, 23);
		contentPane.add(btnNewButton);
		
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
}
