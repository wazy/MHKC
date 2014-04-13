package edu.uncfsu.csc.MHKC.Client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import edu.uncfsu.csc.MHKC.EncryptDecrypt.MHKC_Decryption;
import edu.uncfsu.csc.MHKC.EncryptDecrypt.MHKC_Encryption;

public class ClientGUI {

	private JFrame frmMhkcUser;
	private static JTextArea textArea;
	private JTextField txtClient;
	private JButton btnNewButton;
	private JLabel lblTheMerkleHellman;
	
	private static BufferedReader in;
	private static BufferedWriter out;

	private static boolean running = true;

	private static String username = "Joe";

	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {

		Socket socket = SocketHandler.fetchSocket();

		in = new BufferedReader(new InputStreamReader(
									socket.getInputStream()));

		out = new BufferedWriter(new OutputStreamWriter(
									socket.getOutputStream()));

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI window = new ClientGUI();
					window.frmMhkcUser.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		out.write(username);
		out.flush();

		while (running) {
			String message = MHKC_Decryption.generatePlainText(in.readLine());
			textArea.append(message + "\n");
		};

		out.close();
		SocketHandler.closeSocket(socket);
	}

	/**
	 * Create the application.
	 */
	public ClientGUI() {
		initialize();
	}

	/**
	 * This method will attempt to send the cipher to the server.
	 * @param cipher The encrypted text.
	 * @throws IOException 
	 */
	protected void sendToServer(String cipher) throws IOException {
		out.write(cipher);
		out.flush();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMhkcUser = new JFrame();
		frmMhkcUser.getContentPane().setBackground(Color.DARK_GRAY);
		
		lblTheMerkleHellman = new JLabel("The Merkle-Hellman Knapsack Cryptosystem");
		lblTheMerkleHellman.setForeground(Color.GREEN);
		lblTheMerkleHellman.setFont(new Font("DejaVu Sans Light", Font.ITALIC, 24));

		txtClient = new JTextField();
		txtClient.setText(">>> ");
		txtClient.setFont(new Font("Arial", Font.PLAIN, 14));
		txtClient.setBackground(SystemColor.scrollbar);
		txtClient.setColumns(10);

		textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 14));
		textArea.setBackground(SystemColor.scrollbar);
		textArea.setEditable(false);

		btnNewButton = new JButton("Encrypt & Send");
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));

		ActionListener atl = new ActionListener(){
			public void actionPerformed(ActionEvent e){
				// remove the >>> from user input 
				String text = txtClient.getText().substring(4);
				String cipher = MHKC_Encryption.generateCipher(text) + "\n";

				try {
					sendToServer(cipher);
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				txtClient.setText(">>> ");
			}
		};

		txtClient.addActionListener(atl);
		btnNewButton.addActionListener(atl);

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
		frmMhkcUser.setTitle("MHKC Group Chat");
		frmMhkcUser.setBounds(100, 100, 724, 409);
		frmMhkcUser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frmMhkcUser.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        running = false;
		    }
		});
		
		running = true;
	}
}
