package edu.uncfsu.csc.MHKC.Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientConnection implements Runnable {

	private Socket socket = null;
	private String clientName = "client";

	private BufferedReader in;

	public ClientConnection(Socket connection) {
		this.socket = connection;
	}

	public void run() {
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			this.clientName = in.readLine();

			String nextLine;
			while ((nextLine = in.readLine()) != null) {
				String message = this.clientName + ": " + nextLine + "\n";

				sendMessageToClients(message);

				System.out.println(message);
			}
			removeClientSocket(socket);
			System.out.println("\n" + this.clientName + " disconnected.\n");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method writes the message to each of the connected clients.
	 * @param message The string to be sent.
	 * @throws IOException
	 */
	private void sendMessageToClients(String message) throws IOException {
		ArrayList<Socket> clients = Server.getSocketList();

		for(Socket client : clients) {
			BufferedWriter brw = new BufferedWriter(
									new OutputStreamWriter(
									client.getOutputStream()));
			brw.write(message);
			brw.flush();
		}
		
	}

	/**
	 * This method removes the client from the list of all clients.
	 * @param socket The socket to remove from the list.
	 */
	private void removeClientSocket(Socket socket) {
		Server.getSocketList().remove(socket);
	}

}
