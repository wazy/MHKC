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

public class Encryption_GUI {

	private JFrame frmMhkcEncryption;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Encryption_GUI window = new Encryption_GUI();
					window.frmMhkcEncryption.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Encryption_GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMhkcEncryption = new JFrame();
		frmMhkcEncryption.setTitle("MHKC Encryption");
		frmMhkcEncryption.setBounds(100, 100, 627, 384);
		frmMhkcEncryption.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblTheMerklehellmanKnapsack = new JLabel("The Merkle-Hellman Knapsack Cryptosystem");
		lblTheMerklehellmanKnapsack.setFont(new Font("Arial", Font.PLAIN, 18));
		
		JLabel lblEnterMessage = new JLabel("Enter Message:");
		lblEnterMessage.setFont(new Font("Arial", Font.PLAIN, 12));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnEncryptSend = new JButton("Encrypt & Send");
		GroupLayout groupLayout = new GroupLayout(frmMhkcEncryption.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(135, Short.MAX_VALUE)
					.addComponent(lblTheMerklehellmanKnapsack)
					.addGap(126))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addComponent(lblEnterMessage)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, 447, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(35, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(271, Short.MAX_VALUE)
					.addComponent(btnEncryptSend)
					.addGap(261))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(36)
					.addComponent(lblTheMerklehellmanKnapsack)
					.addGap(73)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEnterMessage)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnEncryptSend)
					.addContainerGap(137, Short.MAX_VALUE))
		);
		frmMhkcEncryption.getContentPane().setLayout(groupLayout);
	}

}
