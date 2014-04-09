package edu.uncfsu.csc490;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class EncryptionGUI {

	private JFrame frmMhkcEncryptionClient;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionGUI window = new EncryptionGUI();
					window.frmMhkcEncryptionClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EncryptionGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMhkcEncryptionClient = new JFrame();
		frmMhkcEncryptionClient.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblTheMerklehellmanKnapsack = new JLabel("The Merkle-Hellman Knapsack Cryptosystem");
		lblTheMerklehellmanKnapsack.setForeground(Color.GREEN);
		lblTheMerklehellmanKnapsack.setFont(new Font("Neuropol", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblNewLabel = new JLabel("Please enter a message below:");
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("Neuropol", Font.PLAIN, 14));
		
		textField = new JTextField();
		textField.setColumns(10);
		
		JButton btnEncryptAndSend = new JButton("Encrypt and Send");
		btnEncryptAndSend.setFont(new Font("Neuropol", Font.PLAIN, 12));
		btnEncryptAndSend.setBackground(Color.GREEN);
		GroupLayout groupLayout = new GroupLayout(frmMhkcEncryptionClient.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(189)
					.addComponent(lblNewLabel)
					.addContainerGap(414, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(55, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 536, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblTheMerklehellmanKnapsack, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
							.addGap(50))))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(245)
					.addComponent(btnEncryptAndSend)
					.addContainerGap(513, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblTheMerklehellmanKnapsack)
					.addGap(90)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(btnEncryptAndSend)
					.addContainerGap(151, Short.MAX_VALUE))
		);
		frmMhkcEncryptionClient.getContentPane().setLayout(groupLayout);
		frmMhkcEncryptionClient.setTitle("MHKC Encryption Client");
		frmMhkcEncryptionClient.setBounds(100, 100, 665, 400);
		frmMhkcEncryptionClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
