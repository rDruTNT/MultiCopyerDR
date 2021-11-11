package dr.copyer.window;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class GroupCreate extends JPanel {
	private JTextField txtid;

	/**
	 * Create the panel.
	 */
	public GroupCreate() {
		setLayout(null);
		setVisible(true);

		txtid = new JTextField();
		txtid.setToolTipText("請輸入群組名稱");
		txtid.setBounds(65, 54, 180, 21);
		add(txtid);
		txtid.setColumns(10);
		
		
		
		JButton btnNewButton_1 = new JButton("取消");
		btnNewButton_1.setBounds(160, 91, 85, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("請輸入群組ID");
		lblNewLabel.setBounds(127, 29, 72, 15);
		add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("新增");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtid.getText().length()>0) {
					new GroupModifier();
					removeAll();
					setVisible(false);
					System.gc();
				} else {
					lblNewLabel.setText(lblNewLabel+"!");
				}
			}
		});
		btnNewButton.setBounds(65, 91, 85, 23);
		add(btnNewButton);
		
		}

}
