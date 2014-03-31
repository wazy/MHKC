package edu.uncfsu.csc490;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class Logon_GUI {
	
	//testing

	private JFrame frmMhkcLogon;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Logon_GUI window = new Logon_GUI();
					window.frmMhkcLogon.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Logon_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMhkcLogon = new JFrame();
		frmMhkcLogon.setTitle("MHKC Logon");
		frmMhkcLogon.setBounds(100, 100, 415, 272);
		frmMhkcLogon.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTheMerklehellmanKnapsack = new JLabel("The Merkle-Hellman Knapsack Cryptosystem");
		lblTheMerklehellmanKnapsack.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel lblUserName = new JLabel("User Name:");
		lblUserName.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnLogOn = new JButton("Log On");
		GroupLayout groupLayout = new GroupLayout(frmMhkcLogon.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(20, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblPassword)
									.addGap(12))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblUserName)
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(148))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnLogOn)
							.addGap(157))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblTheMerklehellmanKnapsack)
							.addGap(19))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(19)
					.addComponent(lblTheMerklehellmanKnapsack)
					.addGap(43)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUserName))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword))
					.addGap(18)
					.addComponent(btnLogOn)
					.addContainerGap(58, Short.MAX_VALUE))
		);
		frmMhkcLogon.getContentPane().setLayout(groupLayout);
	}
}
