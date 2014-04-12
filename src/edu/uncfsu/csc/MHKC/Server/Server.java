package edu.uncfsu.csc.MHKC.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	private static ServerSocket socket = null;
	
	private static ArrayList<Socket> socketList = new ArrayList<Socket>(10);

	public static void main(String[] args) throws IOException {
		int port = 8149;
		try {
			socket = new ServerSocket(port);

			while (true) {
				Socket connection = socket.accept();
				socketList.add(connection);
				Runnable runnable = new ClientConnection(connection);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		} 
		catch (Exception e) {
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			socket.close();
		}

	}

	public static synchronized ArrayList<Socket> getSocketList() {
		return socketList;
	}
}
