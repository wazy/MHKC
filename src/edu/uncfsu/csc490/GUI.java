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
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.SystemColor;

public class GUI {

	private JFrame frmMhkcUser;
	private JTextField txtClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frmMhkcUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMhkcUser = new JFrame();
		frmMhkcUser.getContentPane().setBackground(Color.DARK_GRAY);
		
		JLabel lblTheMerkleHellman = new JLabel("The Merkle-Hellman Knapsack Cryptosystem");
		lblTheMerkleHellman.setForeground(Color.GREEN);
		lblTheMerkleHellman.setFont(new Font("DejaVu Sans Light", Font.ITALIC, 24));
		
		txtClient = new JTextField();
		txtClient.setText(">>>  ");
		txtClient.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 12));
		txtClient.setBackground(SystemColor.scrollbar);
		txtClient.setColumns(10);
		
		JButton btnNewButton = new JButton("Encrypt & Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 12));
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 14));
		textArea.setBackground(SystemColor.scrollbar);
		GroupLayout groupLayout = new GroupLayout(frmMhkcUser.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(txtClient, Alignment.LEADING)
								.addComponent(textArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 620, Short.MAX_VALUE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(277)
							.addComponent(btnNewButton))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(70)
							.addComponent(lblTheMerkleHellman, GroupLayout.PREFERRED_SIZE, 552, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(30)
					.addComponent(lblTheMerkleHellman)
					.addGap(18)
					.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(txtClient, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnNewButton)
					.addContainerGap(59, Short.MAX_VALUE))
		);
		frmMhkcUser.getContentPane().setLayout(groupLayout);
		frmMhkcUser.setTitle("MHKC User");
		frmMhkcUser.setBounds(100, 100, 724, 409);
		frmMhkcUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
